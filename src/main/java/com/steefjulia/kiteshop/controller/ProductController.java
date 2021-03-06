/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.steefjulia.kiteshop.model.*;
import com.steefjulia.kiteshop.model.data.ProductDao;
import com.steefjulia.kiteshop.model.service.AccountService;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author julia
 */
@Controller
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "productList", method = RequestMethod.GET)
    public String showAllProducts(Model model, HttpServletRequest request) {
        
        model.addAttribute("account", accountService.checkUsername());
        model.addAttribute(new Product());
        model.addAttribute("products", productDao.findAll());
        return "products/productList";
    }

    @RequestMapping(value = "productList", method = RequestMethod.POST)
    public String processAddProductForm(@ModelAttribute @Valid Product choosenProduct,
            Errors errors, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        //Om code regels te beaparen heb ik vanuit html alleen het product id gevuld van het product /
        //dat ik in de product pagina wil zien, dus ik moet daar nu nog even het hele proudct bij zoeken
        Product fullProduct = productDao.findOne(choosenProduct.getProductID());

        session.setAttribute("product", fullProduct);
        return "redirect:/products/productPagina";
    }

    @RequestMapping(value = "productPagina", method = RequestMethod.GET)
    public String showProduct(Model model, HttpServletRequest request) {
        model.addAttribute(new Product());
        model.addAttribute(new BestelRegel());

        HttpSession session = request.getSession();
        Product productVoorProductpagina = (Product) session.getAttribute("product");
        
        model.addAttribute("product", productVoorProductpagina);
        
        model.addAttribute("account", accountService.checkUsername());
        return "products/productPagina";
    }

    @RequestMapping(value = "productPagina", method = RequestMethod.POST)
    public String makeBestelRegel(@ModelAttribute @Valid BestelRegel bestelregel,
            Errors errors, Model model, HttpServletRequest request) {
        //Het kan zijn dat er al eerder een product is gekozen an er dus een beslling in de sessie zit
        //zo nee wordt er nu een bestelling voor de sessie aangemaakt, 
        HttpSession session = request.getSession();
        Bestelling bestelling = (Bestelling) session.getAttribute("bestelling");
        if (bestelling == null) {
            bestelling = new Bestelling();
        }
        //en de bestelregel hier aan toegevoegd
        bestelling.addBestelRegel(bestelregel);

        //en het totaal toegevoegd want dit kan niet in thymeleaf
        BigDecimal total = new BigDecimal(0);

        for (BestelRegel regel : bestelling.getBestelling()) {
            BigDecimal subtotal = regel.getProduct().getPrijs().multiply(new BigDecimal(regel.getAantal()));
            total = total.add(subtotal);
        }

        bestelling.setTotaalprijs(total);

        System.out.println(bestelling);
        session.setAttribute("bestelling", bestelling);

        return "redirect:/bestelling/winkelmand";
    }

}

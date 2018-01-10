/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.controller;

import com.steefjulia.kiteshop.model.Account;
import com.steefjulia.kiteshop.model.Product;
import com.steefjulia.kiteshop.model.data.ProductDao;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author julia
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private ProductDao productdao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showHomeAdmin(Model model) {
        return "admin/homeAdmin";
    }

    @RequestMapping(value = "productsForAdmin", method = RequestMethod.GET)
    public String showProducts(Model model) {
        model.addAttribute("products", productdao.findAll());
        return "admin/productsForAdmin";
    }

    @RequestMapping(value = "productsForAdmin", method = RequestMethod.POST)
    public String askProductDetails(@ModelAttribute @Valid Product choosenProduct, Errors errors, Model model, HttpServletRequest request) {
        Product fullProduct = productdao.findOne(choosenProduct.getProductID());
        HttpSession session = request.getSession();
        session.setAttribute("product", fullProduct);
        return "redirect:/admin/productDetails";
    }

    @RequestMapping(value = "addProduct", method = RequestMethod.GET)
    public String showAddProductsForm(Model model) {
        model.addAttribute("title", "Add a new product");
        model.addAttribute(new Product());
        return "admin/addProduct";
    }

    @RequestMapping(value = "addProduct", method = RequestMethod.POST)
    public String processAddProducsForm(@ModelAttribute @Valid Product newProduct, Errors errors, Model model, HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute(errors);
            model.addAttribute("title", "Add a new product");
            return "admin/addProduct";
        }
        HttpSession session = request.getSession();
        session.setAttribute("product", newProduct);
        productdao.save(newProduct);
        return "redirect:/admin/productsForAdmin";
    }

    @RequestMapping(value = "productDetails", method = RequestMethod.GET)
    public String showChangeProductsForm(Model model, HttpServletRequest request) {
        // model.addAttribute(new Product());
        model.addAttribute("title", "Change the product");
        HttpSession session = request.getSession();
        Product product = (Product) session.getAttribute("product");

        model.addAttribute("product", product);
        return "admin/productDetails";
    }

    @RequestMapping(value = "productDetails", method = RequestMethod.POST)
    public String processChangeProductsForm(@ModelAttribute @Valid Product changedProduct, Errors errors, Model model, HttpServletRequest request) {
        if (errors.hasErrors()) {
            model.addAttribute(errors);
            model.addAttribute("title", "Add a new product");
            return "admin/productDetails";
        }

        productdao.save(changedProduct);
        return "redirect:/admin/productsForAdmin";
    }

    @RequestMapping(value = "deleteProduct", method = RequestMethod.GET)
    public String showdeleteProductForm(Model model, HttpServletRequest request) {
        model.addAttribute("title", "Deleting product");
        HttpSession session = request.getSession();
        Product product = (Product) session.getAttribute("product");
        model.addAttribute("product", product);
        return "admin/deleteProduct";
    }

    @RequestMapping(value = "deleteProduct", method = RequestMethod.POST)
    public String processDeleteProductForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Product product = (Product) session.getAttribute("product");
        productdao.delete(product);
        return "redirect:/admin/productsForAdmin";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/home/index";
    }
}

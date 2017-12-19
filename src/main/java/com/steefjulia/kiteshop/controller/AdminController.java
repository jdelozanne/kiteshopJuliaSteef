/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.controller;

import com.steefjulia.kiteshop.model.Product;
import com.steefjulia.kiteshop.model.data.ProductDao;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String askProductDetails(Model model) {
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
    public String showChangeProductsForm(Model model) {
        model.addAttribute("title", "Change the product");
        model.addAttribute(new Product());
        return "admin/productDetails";
    }
//    @RequestMapping(value = "productDetails", method = RequestMethod.POST)
//    public String processChangeProductsForm(Model model) {
//        model.addAttribute("title", "Change the product");
//        model.addAttribute(new Product());
//        return "admin/productDetails";
//    }
    

}

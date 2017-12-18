/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.steefjulia.kiteshop.model.Klant;
import com.steefjulia.kiteshop.model.Product;
import com.steefjulia.kiteshop.model.data.ProductDao;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ProductDao productdao;

    @RequestMapping(value = "productList", method = RequestMethod.GET)
    public String showAllProducts(Model model) {
        model.addAttribute(new Product());
        model.addAttribute("products", productdao.findAll());
        return "products/productList";
    }
    
    @RequestMapping(value = "productList", method = RequestMethod.POST)
    public String processAddProductForm(@ModelAttribute @Valid Product newProduct,
			Errors errors, Model model, HttpServletRequest request) {
       
    	System.out.println(newProduct);
    	HttpSession session = request.getSession();
		session.setAttribute("product", newProduct);
    	
	
        

      return "redirect:/products/productPagina";
    }
    
    @RequestMapping(value = "productPagina", method = RequestMethod.GET)
    public String showProduct(Model model) {
        model.addAttribute(new Product());
        model.addAttribute("products", productdao.findAll());
        return "products/productPagina";
    }
}

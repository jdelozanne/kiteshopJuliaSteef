/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.controller;

import java.util.ArrayList;
import java.util.List;
import com.steefjulia.kiteshop.model.Product;
import com.steefjulia.kiteshop.model.data.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}

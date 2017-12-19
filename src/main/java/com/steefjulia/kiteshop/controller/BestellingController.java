package com.steefjulia.kiteshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.steefjulia.kiteshop.model.Product;

@Controller
@RequestMapping("bestelling")
public class BestellingController {
	
	 @RequestMapping(value = "winkelmand", method = RequestMethod.GET)
	    public String showAllProducts(Model model) {
	   
	        return "bestelling/winkelmand";
	    }

}

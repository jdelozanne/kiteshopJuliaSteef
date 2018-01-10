/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.controller;

import com.steefjulia.kiteshop.model.service.AccountService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
@RequestMapping("")
public class HomeController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String directToHomepage(Model model, HttpServletRequest request) {
        
        model.addAttribute("account", accountService.checkUsername());

        return "redirect:/home/index";
    }

    @RequestMapping(value = "home/index", method = RequestMethod.GET)
    public String giveToHomepage(Model model, HttpServletRequest request) {

        model.addAttribute("account", accountService.checkUsername());

        return "home/index";
    }

}

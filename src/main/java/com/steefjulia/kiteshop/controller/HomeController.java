/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.controller;

import com.steefjulia.kiteshop.model.Account;
import javax.servlet.http.HttpSession;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String directToHomepage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("fullAccount");
        if (account == null) {
            account = new Account();
            account.setUsername("dummy");
        }

        model.addAttribute("account", account);

        return "redirect:/home/index";
    }

    @RequestMapping(value = "home/index", method = RequestMethod.GET)
    public String giveHomepage(Model model, HttpServletRequest request) {
        model.addAttribute("welcome", "Welcome to the Kiteshop!");
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("fullAccount");
        if (account == null) {
            account = new Account();
            account.setUsername("dummy");
        }

        model.addAttribute("account", account);

        return "home/index";
    }
}

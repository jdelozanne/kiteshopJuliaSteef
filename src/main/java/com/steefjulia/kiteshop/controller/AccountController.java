/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.controller;

import com.steefjulia.kiteshop.model.Account;

import com.steefjulia.kiteshop.model.data.AccountDao;
import com.steefjulia.kiteshop.model.service.AccountService;
import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("login")
public class AccountController {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private AccountService loginService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        model.addAttribute("title", "Login here");
        model.addAttribute(new Account());
        return "login/beforelogin";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processLoginForm(@ModelAttribute @Valid Account loginAccount, Errors errors, Model model, HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute(errors);
            model.addAttribute("title", "Login here");

            return "login/beforelogin";
        }

        return "redirect:/home/index";
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.controller;

import com.steefjulia.kiteshop.model.Account;

import com.steefjulia.kiteshop.model.data.AccountDao;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showLoginForm(Model model) {
        model.addAttribute("title", "Login here");
        model.addAttribute(new Account());
        return "login/beforelogin";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processLoginForm(@ModelAttribute @Valid Account newAccount, Errors errors, Model model, HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute(errors);
            model.addAttribute("title", "Login here");
            
            return "login/beforelogin";
        }

        HttpSession session = request.getSession();
        session.setAttribute("account", newAccount);

        Account account = (Account) session.getAttribute("account");
        //hier gebruikmaken van servicelayer om hash te maken van password en te checken
        Account login = accountDao.findByUsername(account.getUsername());
        if (login.getPassword().equals(account.getPassword())) {
            return "redirect:login/afterlogin";
        }
        return "redirect:/login/beforelogin";
    }

    @RequestMapping(value = "/afterlogin", method = RequestMethod.GET)
    public String showLoginCompleet(Model model, HttpServletRequest request
    ) {
        model.addAttribute("title", "You are logged in");
        model.addAttribute(new Account());
        HttpSession session = request.getSession();
        model.addAttribute("account", session.getAttribute("account"));
        return "login/afterlogin";
    }
}

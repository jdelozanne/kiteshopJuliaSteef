package com.steefjulia.kiteshop.controller;

import java.util.*;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.steefjulia.kiteshop.model.data.AdresDao;
import com.steefjulia.kiteshop.model.data.KlantenDao;
import com.steefjulia.kiteshop.model.*;
import com.steefjulia.kiteshop.model.data.AccountDao;


@Controller
@RequestMapping("klanten")
public class KlantenController {

    @Autowired
    private KlantenDao klantenDao;
    @Autowired
    private AdresDao adresDao;
    @Autowired
    private AccountDao accountDao;
    

    @RequestMapping(value = "addAccount", method = RequestMethod.GET)
    public String displayAddAccountForm(Model model) {

        model.addAttribute("title", "Register");
        model.addAttribute(new Account());
        return "klanten/addAccount";
    }

    @RequestMapping(value = "addAccount", method = RequestMethod.POST)
    public String processAddAccountForm(@ModelAttribute @Valid Account newAccount,
            Errors errors, Model model, HttpServletRequest request) {
        //hier wordt de invoer gevalideerd op juiste aantal tekens etc
        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            model.addAttribute(errors);
            return "klanten/addAccount";
        }

        
        HttpSession session = request.getSession();
        session.setAttribute("account", newAccount);
        return "redirect:/klanten/addCustomer";
    }

    @RequestMapping(value = "addCustomer", method = RequestMethod.GET)
    public String displayAadCustomerForm(Model model) {

        model.addAttribute("title", "Please enter the form");
        model.addAttribute(new Klant());
        return "klanten/addCustomer";
    }

    @RequestMapping(value = "addCustomer", method = RequestMethod.POST)
    public String processKlantToevoegenForm(@ModelAttribute @Valid Klant newKlant,
            Errors errors, Model model, HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Registreer u nu");
            model.addAttribute(errors);
            return "klanten/addCustomer";
        }

        HttpSession session = request.getSession();
        session.setAttribute("klant", newKlant);
        Klant klant = (Klant) session.getAttribute("klant");

        return "redirect:/klanten/addAddress";
    }

    @RequestMapping(value = "addAddress", method = RequestMethod.GET)
    public String displayAdresToevoegenForm(Model model) {

        model.addAttribute("title", "Adres");
        model.addAttribute(new Adres());

        return "klanten/addAddress";
    }

    @RequestMapping(value = "addAddress", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Adres newAdres,
            Errors errors, Model model, HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Registreer u nu");
            model.addAttribute(new Klant());
            model.addAttribute(new Adres());

            return "klanten/addCustomer";
        }

        HttpSession session = request.getSession();
        Klant klant = (Klant) session.getAttribute("klant");
        Account account = (Account) session.getAttribute("account");
        klant.setBezoekAdres(newAdres);
        account.setKlant(klant);
        adresDao.save(newAdres);
        klantenDao.save(klant);
        accountDao.save(account);
        return "redirect:/login/afterlogin";
    }

}

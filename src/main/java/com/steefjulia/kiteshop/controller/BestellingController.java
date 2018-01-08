package com.steefjulia.kiteshop.controller;

import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.steefjulia.kiteshop.model.*;
import com.steefjulia.kiteshop.model.data.BestelRegelDao;
import com.steefjulia.kiteshop.model.data.BestellingDao;
import com.steefjulia.kiteshop.model.data.ProductDao;

@Controller
@RequestMapping("bestelling")
public class BestellingController {

    @Autowired
    private BestellingDao bestellingDao;
    @Autowired
    private BestelRegelDao bestelRegelDao;

    @RequestMapping(value = "winkelmand", method = RequestMethod.GET)
    public String showBestelling(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Bestelling bestelling = (Bestelling) session.getAttribute("bestelling");
        if (bestelling == null) {
            bestelling = new Bestelling();
        }
        model.addAttribute("bestelling", bestelling);
        model.addAttribute("bestelregels", bestelling.getBestelling());
   
        return "bestelling/winkelmand";
    }

    @RequestMapping(value = "winkelmand", method = RequestMethod.POST)
    public String gaNaarBetalen(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session.getAttribute("account") != null) {
            return "redirect:/bestelling/afrekenen";
        } else {
            return "redirect:/bestelling/kiesInloggenOfNieuwAccoount";
        }

    }

    @RequestMapping(value = "kiesInloggenOfNieuwAccoount", method = RequestMethod.GET)
    public String showKiesInloggenOfAccountAanmaken(Model model, HttpServletRequest request) {

        return "bestelling/kiesInloggenOfNieuwAccoount";
    }

    @RequestMapping(value = "afrekenen", method = RequestMethod.GET)
    public String showAfrekenen(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        model.addAttribute(session.getAttribute("bestelling"));

        Account account = (Account) session.getAttribute("fullAccount");
        System.out.println(account);

        Klant klant = account.getKlant();
        System.out.println(klant);
        model.addAttribute(klant);

        return "bestelling/afrekenen";
    }

    @RequestMapping(value = "afrekenen", method = RequestMethod.POST)
    public String gaNaarDankvoorUwAankoop(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Bestelling bestelling = (Bestelling) session.getAttribute("bestelling");
        Klant klant = (Klant) session.getAttribute("klant");
        bestelling.setKlant(klant);

        for (BestelRegel regel : bestelling.getBestelling()) {
            bestelRegelDao.save(regel);
        }

        bestellingDao.save(bestelling);

        return "redirect:/bestelling/bedanktVoorUwAankoop";
    }

    @RequestMapping(value = "bedanktVoorUwAankoop", method = RequestMethod.GET)
    public String showBedankVoorUwAankoop(Model model, HttpServletRequest request) {
        model.addAttribute("welcome", "Welcome to the Kiteshop!");

        return "bestelling/bedanktVoorUwAankoop";
    }

}

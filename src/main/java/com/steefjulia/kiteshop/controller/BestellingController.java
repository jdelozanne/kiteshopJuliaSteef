package com.steefjulia.kiteshop.controller;

import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.steefjulia.kiteshop.model.*;
import com.steefjulia.kiteshop.model.data.BestellingDao;
import com.steefjulia.kiteshop.model.data.ProductDao;


@Controller
@RequestMapping("bestelling")
public class BestellingController {
	
	
	@Autowired
    private BestellingDao bestellingDao;

	@RequestMapping(value = "winkelmand", method = RequestMethod.GET)
	public String showBestelling(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		Bestelling bestelling = (Bestelling) session.getAttribute("bestelling");

		model.addAttribute("bestelling", bestelling);
		model.addAttribute("bestelregels", bestelling.getBestelling());
		//
		return "bestelling/winkelmand";
	}

	@RequestMapping(value = "winkelmand", method = RequestMethod.POST)
	public String gaNaarBetalen(HttpServletRequest request) {

		HttpSession session = request.getSession();



		if(session.getAttribute("account")!=null){
			return "redirect:/bestelling/afrekenen";
		} else {
			return "redirect:/klanten/addAccount";
		}


	}

	@RequestMapping(value = "afrekenen", method = RequestMethod.GET)
	public String showAfrekenen(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		model.addAttribute(session.getAttribute("bestelling"));

		Account account = (Account) session.getAttribute("account");
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
		Klant klant =  (Klant) session.getAttribute("klant");
		bestelling.setKlant(klant);
		bestellingDao.save(bestelling);
		
		if(session.getAttribute("account")!=null){
			return "redirect:/bestelling/afrekenen";
		} else {
			return "redirect:/klanten/addAccount";
		}


	}
}

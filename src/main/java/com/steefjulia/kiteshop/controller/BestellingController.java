package com.steefjulia.kiteshop.controller;

import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.steefjulia.kiteshop.model.*;


@Controller
@RequestMapping("bestelling")
public class BestellingController {

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
	public String gaNaarBetalen(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		

		
		Bestelling bestelling = (Bestelling) session.getAttribute("bestelling");

		model.addAttribute("bestelling",bestelling);
		model.addAttribute("bestelregels", bestelling.getBestelling());
		//
		return "bestelling/winkelmand";
	}

}

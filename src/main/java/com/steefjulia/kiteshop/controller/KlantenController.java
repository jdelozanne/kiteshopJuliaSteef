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




@Controller
@RequestMapping("klanten")
public class KlantenController {

	@Autowired private KlantenDao klantenDao;
	@Autowired private AdresDao adresDao;

	
	@RequestMapping(value = "registreer", method = RequestMethod.GET)
	public String displayKlantToevoegenForm(Model model){

		model.addAttribute("title", "Registreer u nu");
		model.addAttribute(new Klant());
	
		
		return "klanten/registreer";
	}

	@RequestMapping(value = "registreer", method = RequestMethod.POST)
	public String processKlantToevoegenForm(@ModelAttribute @Valid Klant newKlant,
			Errors errors, Model model, HttpServletRequest request){  
		
		
		
		
		if (errors.hasErrors()) {
			model.addAttribute("title", "Registreer u nu");
			model.addAttribute(new Klant());
			model.addAttribute(new Adres());
			
			return "klanten/registreer";
		}
		

		HttpSession session = request.getSession();
		session.setAttribute("klant", newKlant);
		
		Klant klant = (Klant) session.getAttribute("klant");
		System.out.println("Post klant"+klant);
		
		return "redirect:/klanten/voegAdresToe";
	}
	
	@RequestMapping(value = "voegAdresToe", method = RequestMethod.GET)
	public String displayAdresToevoegenForm(Model model){

		
		model.addAttribute("title", "Adres");
		model.addAttribute(new Adres());
		
		
		return "klanten/voegAdresToe";
	}

	
	@RequestMapping(value = "voegAdresToe", method = RequestMethod.POST)
	public String processAddCheeseForm(@ModelAttribute @Valid Adres newAdres,
			Errors errors, Model model, HttpServletRequest request){  
		
		
		
		
		if (errors.hasErrors()) {
			model.addAttribute("title", "Registreer u nu");
			model.addAttribute(new Klant());
			model.addAttribute(new Adres());
			
			return "klanten/registreer";
		}

		HttpSession session = request.getSession();
		Klant klant = (Klant) session.getAttribute("klant");
		
		adresDao.save(newAdres);
		klant.setBezoekAdres(newAdres);
		klantenDao.save(klant);
		System.out.println(klant);
		return "redirect:/";
	}
	
	/*
	 
	@RequestMapping(value = "remove", method = RequestMethod.GET)
	public String displayRemoveCheeseForm(Model model) {
		model.addAttribute("cheeses", klantenDao.findAll());
		model.addAttribute("title", "Remove Cheese");
		return "cheese/remove";
	}

	@RequestMapping(value = "remove", method = RequestMethod.POST)
	public String processRemoveCheeseForm(@RequestParam int[] cheeseIds) {

		for(int cheeseId: cheeseIds){
			klantenDao.delete(cheeseId);
		}



		return "redirect:";
	}
	
	@RequestMapping(value= "category", method = RequestMethod.GET)
	public String category(Model model, @RequestParam int id){  //haalt de id van category?id=1 op
		Category cat = categoryDao.findOne(id);
		List<Cheese> cheeses = cat.getCheeses();
		model.addAttribute("cheeses",cheeses);
		model.addAttribute("title","Cheeses in Category "+ cat.getName());
		
		return "cheese/index";
	}
*/

}

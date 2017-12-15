package com.steefjulia.kiteshop.controller;

import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.steefjulia.kiteshop.model.data.KlantenDao;
import com.steefjulia.kiteshop.model.*;




@Controller
@RequestMapping("klanten")
public class KlantenController {

	@Autowired private KlantenDao klantenDao;

	
	@RequestMapping(value = "registreer", method = RequestMethod.GET)
	public String displayAddCheeseForm(Model model){

		model.addAttribute("title", "Registreer u nu");
		model.addAttribute(new Klant());
		model.addAttribute(new Adres());
		
		return "klanten/registreer";
	}
/*
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
			Errors errors,@RequestParam int categoryId ,Model model){  
		
		if (errors.hasErrors()) {
			model.addAttribute("title", "Add Cheese");
			model.addAttribute("categories", categoryDao.findAll());
			return "cheese/add";
		}

		Category cat = categoryDao.findOne(categoryId);
		newCheese.setCategory(cat);
		klantenDao.save(newCheese); 


		return "redirect:";
	}

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

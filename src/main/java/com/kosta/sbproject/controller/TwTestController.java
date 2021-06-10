package com.kosta.sbproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.sbproject.service.TwTestService;

@Controller
public class TwTestController {

	@Autowired
	TwTestService twservice;
	
	@RequestMapping("/twProject/test")
	public void twTestSelectAll(Model model) {
		model.addAttribute("index", twservice.selectAll());
	}
	
	
	
}

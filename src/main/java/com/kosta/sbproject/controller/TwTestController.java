package com.kosta.sbproject.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.sbproject.model.PageMaker;
import com.kosta.sbproject.model.PageVO;
import com.kosta.sbproject.model.TwTestVO;
import com.kosta.sbproject.service.TwTestService;

@Controller
public class TwTestController {

	@Autowired
	TwTestService twservice;
	
	@RequestMapping("/twProject/test")
	public void twTestSelectAll(Model model) {
		model.addAttribute("index", twservice.selectAll());
	}
	
	@RequestMapping("/twProject/list")
	public void companySelectAll(Model model, PageVO pagevo, HttpServletRequest request ) {
		Page<TwTestVO> result = twservice.selectAll(pagevo);
		List<TwTestVO> companylist = result.getContent();
		
		model.addAttribute("companylist", result);
		System.out.println("--------"+result);
		model.addAttribute("pagevo",pagevo);
		model.addAttribute("result",new PageMaker<>(result));
	}
	
	
}

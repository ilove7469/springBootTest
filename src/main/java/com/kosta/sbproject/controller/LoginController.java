package com.kosta.sbproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kosta.sbproject.model.MemberDTO;
import com.kosta.sbproject.security.MemberService;

@Controller
public class LoginController {

	@GetMapping("/auth/login")
	public void login() {
		
	}
	
	@GetMapping("/auth/joinForm")
	public void joinuser() {
		
	}
	
	@Autowired
	MemberService service;
	
	@PostMapping("/auth/joinProc") //@ModelAttribute 모델로 만들어져서 들어온다
	public String joinProc(@ModelAttribute MemberDTO member) {
		System.out.println("회원가입 : " + member);
		service.joinUser(member);
		return "redirect:/auth/login";
	}
	
	@GetMapping("/admin")
	public void adminMethod() {
		
	}
	@GetMapping("/manager")
	public void managerMethod() {
		
	}
	
	@GetMapping("/loginSuccess")
	public void loginSuccess() {
		
	}
	
	@GetMapping("/logout")
	public void logout() {
		
	}
	
	@GetMapping("/accessDenied")
	public void accessDenied() {
		
	}
	
}

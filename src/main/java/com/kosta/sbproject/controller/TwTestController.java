package com.kosta.sbproject.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwTestController {

	
	@RequestMapping("/twProject/test")
	public String getSample1() {
		return " 테스트 완료 ";
	}
}

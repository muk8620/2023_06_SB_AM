package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrApiController {
	
	@RequestMapping("/usr/api/APITest")
	public String showMain() {
		return "usr/api/APITest";
	}
	
}
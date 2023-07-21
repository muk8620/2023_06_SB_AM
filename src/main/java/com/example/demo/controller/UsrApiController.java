package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrApiController {
	
	@RequestMapping("/usr/api/APITest")
	public String APITest() {
		return "usr/api/APITest";
	}
	
	@RequestMapping("/usr/api/APITest2")
	public String APITest2() {
		return "usr/api/APITest2";
	}
	
}
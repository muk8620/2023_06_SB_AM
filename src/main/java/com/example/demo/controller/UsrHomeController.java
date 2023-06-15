package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	
	int cnt = 0;
	
	UsrHomeController() {
		this.cnt = 20;
	}
	
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "안녕fdsafsdafsdafsad";
	}
	
	@RequestMapping("/usr/home/test1")
	@ResponseBody
	public int showMain2() {
		return cnt++;
	}
	
	@RequestMapping("/usr/home/test2")
	@ResponseBody
	public Map<String, Object> showMain3() {
		 
		Map<String, Object> listMap = new HashMap<>();
		
		listMap.put("fdsafasd", "fdsaf");
		return listMap; 
	}
}


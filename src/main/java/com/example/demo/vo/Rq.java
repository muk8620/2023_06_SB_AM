package com.example.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.demo.util.Util;

import lombok.Getter;

public class Rq {
	
	@Getter
	private int loginedMemberId;
	
//	private HttpServletRequest req;
	private HttpServletResponse resp;
	private Member member;
	
	public Rq(HttpServletRequest req, HttpServletResponse resp, Member member) {
		
//		this.req = req;
		this.resp = resp;
		this.member = member;
		
		HttpSession session = req.getSession();
		
		int loginedMemberId = 0;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		this.loginedMemberId = loginedMemberId;
		
	}

	public void jsPrintHistoryBack(String msg) {
		this.resp.setContentType("text/html; charset=UTF-8;");
		
		print(Util.jsHistoryBack(msg));
		
	}
	
	private void print(String str) {
		
		try {
			this.resp.getWriter().append(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void login(Member member) {
		
	}
}
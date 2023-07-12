package com.example.demo.vo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.example.demo.util.Util;

import lombok.Getter;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Rq {
	
	@Getter
	private int loginedMemberId;
	@Getter
	private Member loginedMember;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		
		this.req = req;
		this.resp = resp;
		
		this.session = req.getSession();
		
		int loginedMemberId = 0;
		Member loginedMember = null;
		
		if(session.getAttribute("loginedMemberId") != null) {
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			loginedMember = (Member) session.getAttribute("loginedMember");
		}
		
		this.loginedMemberId = loginedMemberId;
		this.loginedMember = loginedMember;
		
		this.req.setAttribute("rq", this);
		
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
		this.session.setAttribute("loginedMemberId", member.getId());
		this.session.setAttribute("loginedMember", member);
	}

	public void logout() {
		this.session.removeAttribute("loginedMemberId");
		this.session.removeAttribute("loginedMember");
	}

	public String jsReturnOnView(String msg) {
		this.req.setAttribute("msg", msg);
		return "usr/common/js";
	}

	public void init() {
		
	}
}
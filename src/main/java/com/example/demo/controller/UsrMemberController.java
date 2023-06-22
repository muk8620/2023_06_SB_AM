package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Controller
public class UsrMemberController {
	
	private MemberService memberService;
	
	@Autowired
	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 액션 메서드
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		if (Util.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요.");
		}
		if (Util.empty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요.");
		}
		if (Util.empty(name)) {
			return ResultData.from("F-3", "이름을 입력해주세요.");
		}
		if (Util.empty(nickname)) {
			return ResultData.from("F-4", "닉네임을 입력해주세요.");
		}
		if (Util.empty(cellphoneNum)) {
			return ResultData.from("F-5", "전화번호를 입력해주세요.");
		}
		if (Util.empty(email)) {
			return ResultData.from("F-6", "이메일을 입력해주세요.");
		}
		
		ResultData<Member> doJoinRd = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		return doJoinRd;
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpSession session, String loginId, String loginPw) {
		
		if (Util.empty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요.");
		}
		if (Util.empty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요.");
		}
		
		Member existsMember = memberService.getMemberByLoginId(loginId);
		
		if (existsMember == null) {
			return ResultData.from("F-3", Util.f("존재하지 않는 아이디(%s)입니다.", loginId));
		}
		
		if (loginPw.equals(existsMember.getLoginPw()) == false) {
			return ResultData.from("F-4", "비밀번호를 확인해주세요.");
		}
		
		session.setAttribute("loginedMemberId", existsMember.getId());
		return ResultData.from("S-1", Util.f("%s님 환영합니다.", existsMember.getNickname()));
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession session) {
		if (session != null) {
			return ResultData.from("F-1", "이미 로그아웃 되어있습니다.");
		}
		
		session.removeAttribute("loginedMemberId");
		return ResultData.from("S-1", "로그아웃 되었습니다.");
	}

}
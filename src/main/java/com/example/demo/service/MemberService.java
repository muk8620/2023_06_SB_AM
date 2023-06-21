package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.vo.Member;

@Service
public class MemberService {

	private MemberDao memberDao;

	@Autowired
	MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public boolean doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		if (this.loginIdDupCheck(loginId)) {
			return false;
		}
		
		memberDao.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		return true;
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}
	
	public int getLastInsertId() {
		return memberDao.getLastInsertId();
	}
	
	public boolean loginIdDupCheck(String loginId) {
		if (memberDao.loginIdDupCheck(loginId) == null) {
			return false;
		}
		return true;
	}

}
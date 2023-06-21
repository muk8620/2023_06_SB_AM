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

	public int doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		Member existsMember = getMemberByLoginId(loginId);
		
		if (existsMember != null) {
			return -1;
		}
		
		existsMember = getMemberByNickname(nickname);
		
		if (existsMember != null) {
			return -2;
		}
		
		existsMember = getMemberByNameAndEmail(name, email);
		
		if (existsMember != null) {
			return -3;
		}
		
		memberDao.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		return getLastInsertId();
	}

	private Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	private Member getMemberByNickname(String nickname) {
		return memberDao.getMemberByNickname(nickname);
	}
	
	private Member getMemberByNameAndEmail(String name, String email) {
		return memberDao.getMemberByNameAndEmail(name, email);
	}
	
	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}
	
	public int getLastInsertId() {
		return memberDao.getLastInsertId();
	}

}
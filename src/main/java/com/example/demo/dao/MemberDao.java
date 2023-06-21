package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Member;

@Mapper
public interface MemberDao {
	
	public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

	public Member getMemberById(int id);
	
	public int getLastInsertId();

	public Member loginIdDupCheck(String loginId);
	
}

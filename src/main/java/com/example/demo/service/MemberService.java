package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Service
public class MemberService {

	@Value("${custom.siteName}")
	private String siteName;
	@Value("${custom.siteMainUri}")
	private String siteMainUri;
	
	private MemberDao memberDao;
	private MailService mailService;

	@Autowired
	MemberService(MemberDao memberDao, MailService mailService) {
		this.memberDao = memberDao;
		this.mailService = mailService;
	}

	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		Member existsMember = getMemberByLoginId(loginId);
		
		if (existsMember != null) {
			return ResultData.from("F-7", Util.f("이미 사용중인 아이디(%s)입니다", loginId));
		}
		
		existsMember = getMemberByNickname(nickname);
		
		if (existsMember != null) {
			return ResultData.from("F-8", Util.f("이미 사용중인 닉네임(%s)입니다", nickname));
		}
		
		existsMember = getMemberByNameAndEmail(name, email);
		
		if (existsMember != null) {
			return ResultData.from("F-9", Util.f("이미 사용중인 이름(%s)과 이메일(%s)입니다", name, email));
		}
		
		memberDao.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		return ResultData.from("S-1", Util.f("%s회원님이 가입되었습니다", nickname), "member", getMemberById(getLastInsertId()));
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberDao.getMemberByNameAndEmail(name, email);
	}

	private Member getMemberByNickname(String nickname) {
		return memberDao.getMemberByNickname(nickname);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public int getLastInsertId() {
		return memberDao.getLastInsertId();
	}

	public void doMemberModify(int loginedMemberId, String nickname, String cellphoneNum, String email) {
		memberDao.doMemberModify(loginedMemberId, nickname, cellphoneNum, email);
	}

	public void doPasswordModify(int loginedMemberId, String loginPw) {
		memberDao.doPasswordModify(loginedMemberId, loginPw);
	}
	
	public ResultData notifyTempLoginPwByEmail(Member member) {
		String title = "[" + siteName + "] 임시 패스워드 발송";
		String tempPassword = Util.getTempPassword(8);
		String body = "<h1>임시 패스워드 : " + tempPassword + "</h1>";
		body += "<a style='font-size:4rem;' href=\"" + siteMainUri + "/usr/member/login\" target=\"_blank\">로그인 하러가기</a>";

		ResultData sendRd = mailService.send(member.getEmail(), title, body);

		if (sendRd.isFail()) {
			return sendRd;
		}

		setTempPassword(member, tempPassword);

		return ResultData.from("S-1", "계정의 이메일주소로 임시 패스워드가 발송되었습니다");
	}

	private void setTempPassword(Member member, String tempPassword) {
		doPasswordModify(member.getId(), Util.sha256(tempPassword));
	}
	
	public int getMembersCnt(String authLevel, String searchKeywordType, String searchKeyword) {
		return memberDao.getMembersCnt(authLevel, searchKeywordType, searchKeyword);
	}

	public List<Member> getMembers(String authLevel, String searchKeywordType, String searchKeyword, int itemsInAPage,
			int page) {

		int limitStart = (page - 1) * itemsInAPage;

		return memberDao.getMembers(authLevel, searchKeywordType, searchKeyword, itemsInAPage, limitStart);
	}

	public void deleteMembers(List<Integer> memberIds) {
		for (int memberId : memberIds) {
			Member member = getMemberById(memberId);

			if (member != null) {
				deleteMember(member);
			}
		}
	}

	private void deleteMember(Member member) {
		memberDao.deleteMember(member.getId());
	}

}

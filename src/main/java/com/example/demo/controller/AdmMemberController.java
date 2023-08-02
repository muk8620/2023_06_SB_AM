package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Util;
import com.example.demo.vo.Member;
import com.example.demo.vo.Rq;


@Controller
public class AdmMemberController {

	private MemberService memberService;
	private Rq rq;

	@Autowired
	public AdmMemberController(MemberService memberService, Rq rq) {
		this.memberService = memberService;
		this.rq = rq;
	}

	@RequestMapping("/adm/member/list")
	public String showList(Model model, @RequestParam(defaultValue = "0") String authLevel,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "loginId,name,nickname") String searchKeywordType,
			@RequestParam(defaultValue = "") String searchKeyword) {

		int membersCnt = memberService.getMembersCnt(authLevel, searchKeywordType, searchKeyword);

		if (page <= 0) {
			return rq.jsReturnOnView("페이지번호가 올바르지 않습니다");
		}

		int itemsInAPage = 10;
		int pagesCnt = (int) Math.ceil((double) membersCnt / itemsInAPage);

		List<Member> members = memberService.getMembers(authLevel, searchKeywordType, searchKeyword, itemsInAPage,
				page);

		model.addAttribute("pagesCnt", pagesCnt);
		model.addAttribute("page", page);
		model.addAttribute("membersCnt", membersCnt);
		model.addAttribute("members", members);
		model.addAttribute("authLevel", authLevel);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("searchKeywordType", searchKeywordType);

		return "adm/member/list";
	}

	@RequestMapping("/adm/member/doDeleteMembers")
	@ResponseBody
	public String doDeleteMembers(@RequestParam(defaultValue = "") String ids) {

		if (Util.empty(ids)) {
			return Util.jsHistoryBack("선택한 회원이 없습니다");
		}

		if (ids.equals("3")) {
			return Util.jsHistoryBack("관리자 계정은 삭제할 수 없습니다");
		}

		List<Integer> memberIds = new ArrayList<>();

		for (String idStr : ids.split(",")) {
			memberIds.add(Integer.parseInt(idStr));
		}

		memberService.deleteMembers(memberIds);

		return Util.jsReplace("선택한 회원이 삭제되었습니다", "list");
	}

}
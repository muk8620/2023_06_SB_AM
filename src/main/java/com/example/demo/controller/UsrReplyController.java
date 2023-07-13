package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ReactionPoint;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller
public class UsrReplyController {
	private ReplyService replyService;
	private Rq rq;
	
	@Autowired
	public UsrReplyController(ReplyService replyService, Rq rq) {
		this.replyService = replyService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String body) {
		
		if (Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId,  body);

		return Util.jsReplace("댓글이 작성되었습니다.", Util.f("../article/detail?id=%d", relId));
	}
	
	@RequestMapping("/usr/reply/modify")
	@ResponseBody
	public ResultData showModify(@RequestParam(defaultValue = "0") int memberId) {

		if (rq.getLoginedMemberId() != memberId) {
			return ResultData.from("F-1", "해당 댓글에 대한 권한이 없습니다.");
		}

		return ResultData.from("S-1", "해당 댓글에 대한 권한이 있습니다.");
	}

	@RequestMapping("/usr/reply/doModify")
	@ResponseBody
	public String doModify(int id, int relId, String body) {

		replyService.modifyReply(rq.getLoginedMemberId(), id, body);

		return Util.jsReplace("댓글을 수정했습니다", Util.f("../article/detail?id=%d", relId));
	}

	@RequestMapping("/usr/reply/doDelete")
	@ResponseBody
	public String doDelete(int memberId, int relId, int id) {

		if (rq.getLoginedMemberId() != memberId) {
			return Util.jsHistoryBack("해당 댓글에 대한 권한이 없습니다");
		}

		replyService.deleteReply(rq.getLoginedMemberId(), id);

		return Util.jsReplace("댓글이 삭제되었습니다.", Util.f("../article/detail?id=%d", relId));
	}
	
}

package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
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
	
}

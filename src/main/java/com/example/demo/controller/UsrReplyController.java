package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;
import com.example.demo.vo.Reply;
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
	
	@RequestMapping("usr/reply/doWrite")
	@ResponseBody
	public String doWrite(String relTypeCode, int relId, String body) {
		
		if (Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");
		}
		
		replyService.writeReply(rq.getLoginedMemberId(), relTypeCode, relId,  body);

		return Util.jsReplace("댓글이 작성되었습니다.", Util.f("../article/detail?id=%d", relId));
	}
	
	@RequestMapping("usr/reply/getReplyContent")
	@ResponseBody
	public ResultData<Reply> getReplyContent(@RequestParam(defaultValue = "0") int id) {

		Reply reply = replyService.getReply(id);
		
		if (reply == null) {
			return ResultData.from("F-1", "해당 댓글은 존재하지 않습니다.");
		}

		if (reply.getMemberId() != rq.getLoginedMemberId()) {
			return ResultData.from("F-2", "해당 댓글에 대한 권한이 없습니다.");
		}
		
		return ResultData.from("S-1", "댓글 정보 조회 성공", "reply", reply);
	}

	@RequestMapping("usr/reply/doModify")
	@ResponseBody
	public String doModify(int id, String body) {
		
		Reply reply = replyService.getReply(id);
		
		if (reply.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("해당 댓글에 대한 권한이 없습니다");
		}

		replyService.modifyReply(id, body);

		return Util.jsReplace("댓글이 수정되었습니다.", Util.f("../article/detail?id=%d", reply.getRelId()));
	}

	@RequestMapping("usr/reply/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Reply reply = replyService.getReply(id);

		if (reply.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("해당 댓글에 대한 권한이 없습니다");
		}

		replyService.deleteReply(id);

		return Util.jsReplace("댓글이 삭제되었습니다.", Util.f("../article/detail?id=%d", reply.getRelId()));
	}
	
}

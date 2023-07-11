package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReactionPointService;
import com.example.demo.util.Util;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller
public class UsrReactionPointController {
	private ReactionPointService reactionPointService;
	private Rq rq;
	
	@Autowired
	public UsrReactionPointController(ReactionPointService reactionPointService, Rq rq) {
		this.reactionPointService = reactionPointService;
		this.rq = rq;
	}
	
	@RequestMapping("/usr/reaction/showReactionPoint")
	@ResponseBody
	public ResultData showReactionPoint(String relTypeCode, int relId, int memberId) {
		
		ResultData reactionPointRd = reactionPointService.showReactionPoint(relTypeCode, relId, memberId);
		
		return reactionPointRd;
	}
	
	@RequestMapping("/usr/reaction/doIncreaseReaction")
	@ResponseBody
	public String doIncreaseHitCnt(String relTypeCode, int relId, int memberId, int point) {
		
		ResultData reactionPointRd = reactionPointService.showReactionPoint(relTypeCode, relId, memberId);
		
		if (reactionPointRd.isFail()) {
			reactionPointService.doInsertReactionPoint(relTypeCode, relId, memberId, point);
		} else {
			int reactionPointDeleteRd = reactionPointService.doDeleteReactionPoint(relTypeCode, relId, memberId, point);
			if (reactionPointDeleteRd == 0) {
				reactionPointService.doDeleteReactionPoint(relTypeCode, relId, memberId, -point);
				reactionPointService.doInsertReactionPoint(relTypeCode, relId, memberId, point);
			}
		}
		
		return Util.jsReplace("",Util.f("/usr/article/detail?id=%d", relId));
		
	}
	

}

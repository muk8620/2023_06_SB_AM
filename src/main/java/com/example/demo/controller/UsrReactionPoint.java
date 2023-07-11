package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReactionPointService;
import com.example.demo.util.Util;
import com.example.demo.vo.ResultData;

@Controller
public class UsrReactionPoint {
	private ReactionPointService reactionPointService;
	
	@Autowired
	public UsrReactionPoint(ReactionPointService reactionPointService) {
		this.reactionPointService = reactionPointService;
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
			return Util.jsReplace("",Util.f("/usr/article/detail?id=%d", relId));
		} 
		
		reactionPointService.doUpdateReactionPoint(relTypeCode, relId, memberId, point);
		return Util.jsReplace("",Util.f("/usr/article/detail?id=%d", relId));
		
	}
	

}

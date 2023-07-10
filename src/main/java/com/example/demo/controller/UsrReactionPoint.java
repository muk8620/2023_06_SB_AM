package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReactionPointService;
import com.example.demo.vo.ResultData;

@Controller
public class UsrReactionPoint {
	private ReactionPointService reactionPointService;
	
	@Autowired
	public UsrReactionPoint(ReactionPointService reactionPointService) {
		this.reactionPointService = reactionPointService;
	}
	
	@RequestMapping("/usr/reaction/doIncreaseGoodReaction")
	@ResponseBody
	public ResultData doIncreaseHitCnt(int id) {
		
		ResultData increaseHitCntRd = reactionPointService.doIncreaseGoodReaction(id);
		
		if (increaseHitCntRd.isFail()) {
			return increaseHitCntRd;
		}
		
		ResultData rd = ResultData.from(increaseHitCntRd.getResultCode(), increaseHitCntRd.getMsg(), "hitCnt", articleService.getArticleHitCnt(id));
		
		rd.setData2("id", id);
		
		return rd;
	}
}

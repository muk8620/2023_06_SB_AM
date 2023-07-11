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
	public ResultData doIncreaseHitCnt(String relTypeCode, int relId, int point) {
		
		ResultData increaseHitCntRd = reactionPointService.doUpdateGoodReactionPoint(relTypeCode, relId, point);
		
		if (increaseHitCntRd.isFail()) {
			return increaseHitCntRd;
		}
		
//		ResultData rd = ResultData.from(increaseHitCntRd.getResultCode(), increaseHitCntRd.getMsg(), "hitCnt", );
//		
//		rd.setData2("id", id);
		
		return null;
	}
	
	@RequestMapping("/usr/reaction/showReactionPoint")
	@ResponseBody
	public ResultData showReactionPoint(String relTypeCode, int relId, int memberId) {
		
		ResultData reactionPointRd = reactionPointService.showReactionPoint(relTypeCode, relId, memberId);
		
		return reactionPointRd;
	}
}

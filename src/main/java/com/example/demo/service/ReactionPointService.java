package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReactionPointDao;
import com.example.demo.vo.ReactionPoint;
import com.example.demo.vo.ResultData;

@Service
public class ReactionPointService {
	private ReactionPointDao reactionPointDao;
	
	@Autowired
	public ReactionPointService(ReactionPointDao reactionPointDao) {
		this.reactionPointDao = reactionPointDao;
	}

	public ResultData doUpdateGoodReactionPoint(String relTypeCode, int relId, int point) {
		
		reactionPointDao.doUpdateGoodReactionPoint(relTypeCode, relId, point);
		
		return null;
	}
	
	public ResultData doUpdateBadReactionPoint(String relTypeCode, int relId, int point) {
		
		reactionPointDao.doUpdateBadReactionPoint(relTypeCode, relId, point);
		
		return null;
	}
	
	
	public ResultData showReactionPoint(String relTypeCode, int relId, int memberId) {
		
		ReactionPoint reactionpoint = reactionPointDao.showReactionPoint(relTypeCode, relId, memberId);
		
		if (reactionpoint == null) {
			return ResultData.from("F-1", "추천 기록 없음", "point", 0);
		}
		
		return ResultData.from("S-1", "추천 기록 있음", "point", reactionpoint.getPoint());
	}
}

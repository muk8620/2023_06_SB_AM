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
	
	public ReactionPoint getReactionPoint(String relTypeCode, int relId, int memberId) {
		
		ReactionPoint reactionpoint = reactionPointDao.getReactionPoint(relTypeCode, relId, memberId);
		
		return reactionpoint;
	}

	public int doDeleteReactionPoint(String relTypeCode, int relId, int memberId, int point) {
		
		return reactionPointDao.doDeleteReactionPoint(relTypeCode, relId, memberId, point);
	}
	
	public void doInsertReactionPoint(String relTypeCode, int relId, int memberId, int point) {
		
		reactionPointDao.doInsertReactionPoint(relTypeCode, relId, memberId, point);
	}
	

}

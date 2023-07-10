package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReactionPointDao;

@Service
public class ReactionPointService {
	private ReactionPointDao reactionPointDao;
	
	@Autowired
	public ReactionPointService(ReactionPointDao reactionPointDao) {
		this.reactionPointDao = reactionPointDao;
	}
}

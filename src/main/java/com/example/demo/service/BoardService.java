package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BoardDao;
import com.example.demo.vo.Board;

@Service
public class BoardService {

	private BoardDao boardDao;

	@Autowired
	BoardService(BoardDao boardDao) {
		this.boardDao = boardDao;
	}

	public Board getBoardById(int id) {
		return boardDao.getBoardById(id);
	}

}

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ReplyDao;
import com.example.demo.vo.Reply;

@Service
public class ReplyService {
	
	private ReplyDao replyDao;
	
	@Autowired
	public ReplyService(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}

	public void writeReply(int loginedMemberId, String relTypeCode, int relId,  String body) {
		replyDao.writeReply(loginedMemberId, relTypeCode, relId, body);
	}

	public List<Reply> getReplies(String relTypeCode, int relId) {
		
		List<Reply> replies = replyDao.getReplies(relTypeCode, relId);
		
		for (Reply reply : replies) {
			reply.setUpdateDate(reply.getUpdateDate().substring(0, reply.getUpdateDate().length() - 3));
		}
		
		return replies;
	}

	public void modifyReply(int loginedMemberId, int id, String body) {
		replyDao.modifyReply(loginedMemberId, id, body);
	}

	public void deleteReply(int loginedMemberId, int id) {
		replyDao.deleteReply(loginedMemberId, id);
	}

}

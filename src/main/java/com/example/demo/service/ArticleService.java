package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Service
public class ArticleService {
	private ArticleDao articleDao;
	
	@Autowired
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
//	서비스메서드
	public void writeArticle(int loginedMemberId, String title, String body) {
		articleDao.writeArticle(loginedMemberId, title, body);
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public ResultData<Article> modifyArticle(int loginedMemberId, int id, String title, String body) {
		
		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			return ResultData.from("F-1", Util.f("%d번 게시글은 존재하지 않습니다.", id));
		}
		
		if (loginedMemberId != foundArticle.getMemberId()) {
			return ResultData.from("F-B", "해당 게시글에 대한 권한이 없습니다.");
		}
		
		articleDao.modifyArticle(id, title, body);
		
		return ResultData.from("S-1", Util.f("%d번 게시글을 수정했습니다.", id), getArticleById(id));
	}
	
	public void deleteArticle(int id) {
		articleDao.deleteArticle(id);
	}

	public List<Article> getArticles() {
		return articleDao.getArticles();
	}

	public int getLastInsertId() {
		return articleDao.getLastInsertId();
	}
}

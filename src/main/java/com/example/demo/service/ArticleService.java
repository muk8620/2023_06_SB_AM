package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.vo.Article;

@Service
public class ArticleService {
	private ArticleDao articleDao;
	
	@Autowired
	public ArticleService(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}
	
//	서비스메서드
	public void writeArticle(String title, String body) {
		articleDao.writeArticle(title, body);
	}
	
	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);
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

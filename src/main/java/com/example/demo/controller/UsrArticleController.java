package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

@Controller
public class UsrArticleController {
	private int lastArticleId;
	private List<Article> articles;
	
	
	UsrArticleController() {
		this.lastArticleId = 0;
		this.articles = new ArrayList<>();
		makeTestData();
	}
	
//	서비스메서드
	private Article getArticleById(int id) {
		
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}
	
	private Article writeArticle(String title, String body) {
		
		int id = ++lastArticleId;
		
		Article article = new Article(id, title, body);
		
		articles.add(article);
		
		return article;
	}
	
	private void deleteArticle(int id) {
		Article article = getArticleById(id);
		articles.remove(article);
	}
	
	private void modifyArticle(int id, String title, String body) {
		Article article = getArticleById(id);
		article.setTitle(title);
		article.setBody(body);
	}
	
	private void makeTestData() {
		for (int i = 1; i <= 10; i++) {
			
			String title = "제목" + i;
			String body = "내용" + i;
			
			writeArticle(title, body);
		}
	}
	

//	액션메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public Article doAdd(String title, String body) {
		
		return writeArticle(title, body);
	}
	

	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {
		
		return articles;
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			return id + "번 게시글은 존재하지 않습니다.";
		}
		
		deleteArticle(id);
		
		return id + "번 게시글을 삭제했습니다.";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		
		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			return id + "번 게시글은 존재하지 않습니다.";
		}
		
		modifyArticle(id, title, body);
		
		return id + "번 게시글을 수정했습니다.";
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public Object getArticle(int id) {
		
		Article foundArticle = getArticleById(id);
		
		if (foundArticle == null) {
			return id + "번 게시글은 존재하지 않습니다.";
		}
		
		return foundArticle;
	}
}



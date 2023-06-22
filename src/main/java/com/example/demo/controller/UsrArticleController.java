package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	
	private ArticleService articleService;
	
	@Autowired
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
//	액션메서드
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(String title, String body) {
		
		if (Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요.");
		}
		
		if (Util.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요.");
		}
		
		articleService.writeArticle(title, body);
		
		int id = articleService.getLastInsertId();
		
		return ResultData.from("S-1", Util.f("%d번 게시글이 생성되었습니다.", id), articleService.getArticleById(id));
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {
		
		List<Article> articles = articleService.getArticles();
		
		if (articles.size() == 0) {
			return ResultData.from("F-1", "게시물이 존재하지 않습니다.");
		}
		
		return ResultData.from("S-1", "게시글 목록", articleService.getArticles());
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		return ResultData.from("S-1", "%d번 게시글 입니다.", foundArticle);
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		articleService.modifyArticle(id, title, body);
		return ResultData.from("S-1", Util.f("%d번 게시물을 수정했습니다.", id));
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		
		Article foundArticle = articleService.getArticleById(id);
	
		if (foundArticle == null) {
			return ResultData.from("F-1", Util.f("%d번 게시물은 존재하지 않습니다.", id));
		}
		
		articleService.deleteArticle(id);
		return ResultData.from("S-1", Util.f("%d번 게시물을 삭제했습니다..", id));
	}
	
}

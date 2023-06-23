package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
	public ResultData<Article> doAdd(HttpSession session, String title, String body) {
		
		if (session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-A", "로그인 후 이용해주세요.");
		}
		
		if (Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요.");
		}
		
		if (Util.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요.");
		}
		
		int loginedMemberId = (int) session.getAttribute("loginedMemberId");
		
		articleService.writeArticle(loginedMemberId, title, body);
		
		int id = articleService.getLastInsertId();
		
		return ResultData.from("S-1", Util.f("%d번 게시글이 생성되었습니다.", id), articleService.getArticleById(id));
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData<List<Article>> getArticles() {
		
		List<Article> articles = articleService.getArticles();
		
		if (articles.size() == 0) {
			return ResultData.from("F-1", "게시글이 존재하지 않습니다.");
		}
		
		return ResultData.from("S-1", "게시글 목록", articleService.getArticles());
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData<Article> getArticle(int id) {
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return ResultData.from("F-1", Util.f("%d번 게시글은 존재하지 않습니다.", id));
		}
		
		return ResultData.from("S-1", "%d번 게시글 입니다.", foundArticle);
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Article> doModify(HttpSession session, int id, String title, String body) {
		
		if (session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-A", "로그인 후 이용해주세요.");
		}
		
		if (Util.empty(id)) {
			return ResultData.from("F-1", "글 번호를 입력해주세요.");
		}
		
		int loginedMemberId = (int) session.getAttribute("loginedMemberId");
		
		return articleService.modifyArticle(loginedMemberId, id, title, body);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(HttpSession session, int id) {
		
		
		if (session.getAttribute("loginedMemberId") == null) {
			return ResultData.from("F-A", "로그인 후 이용해주세요.");
		}
		
		if (Util.empty(id)) {
			return ResultData.from("F-1", "글 번호를 입력해주세요.");
		}
		
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			return ResultData.from("F-2", Util.f("%d번 게시글은 존재하지 않습니다.", id));
		}
		
		if ((int) session.getAttribute("loginedMemberId") != foundArticle.getMemberId()) {
			return ResultData.from("F-B", "해당 게시글에 대한 권한이 없습니다.");
		}
		
		articleService.deleteArticle(id);
		return ResultData.from("S-1", Util.f("%d번 게시글을 삭제했습니다.", id));
	}
	
}

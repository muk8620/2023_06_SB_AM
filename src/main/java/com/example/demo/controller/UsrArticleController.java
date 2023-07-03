package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.Rq;

@Controller
public class UsrArticleController {

	private ArticleService articleService;

	@Autowired
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	// 액션 메서드
	
	@RequestMapping("/usr/article/write")
	public String showWrite(Model model, int boardId) {
		
		int id = articleService.getLastInsertId();
		
		model.addAttribute("id", id);
		model.addAttribute("boardId", boardId);
		
		return "usr/article/write";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req, int boardId, String title, String body) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		if (Util.empty(title)) {
			return Util.jsHistoryBack("제목을 입력해주세요");
		}

		if (Util.empty(body)) {
			return Util.jsHistoryBack("내용을 입력해주세요");	
		}

		articleService.writeArticle(rq.getLoginedMemberId(), boardId, title, body);

		int id = articleService.getLastInsertId();

		return Util.jsReplace(Util.f("%d번 게시글이 작성되었습니다", id), Util.f("list?boardId=%d", boardId));
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model, int boardId) {

		List<Article> articles = articleService.getArticles(boardId);

		model.addAttribute("articles", articles);
		
		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(id);

		model.addAttribute("article", article);
		
		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/modify")
	public String showModify(HttpServletRequest req, Model model, int id) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(id);
		
		if (article == null) {
			return rq.jsReturnOnView(Util.f("%d번 게시글은 존재하지 않습니다", id));
		}

		if (rq.getLoginedMemberId() != article.getMemberId()) {
			return rq.jsReturnOnView("해당 게시글에 대한 권한이 없습니다");
		}
		
		model.addAttribute("article", article);
		
		return "usr/article/modify";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String title, String body) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시글은 존재하지 않습니다", id));
		}

		if (rq.getLoginedMemberId() != article.getMemberId()) {
			return Util.jsHistoryBack("해당 게시글에 대한 권한이 없습니다");
		}
		
		articleService.modifyArticle(id, title, body);
		
		return Util.jsReplace(Util.f("%d번 게시글을 수정했습니다", id), Util.f("detail?id=%d", id));
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id, int boardId) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getArticleById(id);

		if (article == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시글은 존재하지 않습니다", id));
		}

		if (rq.getLoginedMemberId() != article.getMemberId()) {
			return Util.jsHistoryBack("해당 게시글에 대한 권한이 없습니다");
		}

		articleService.deleteArticle(id);

		return Util.jsReplace(Util.f("%d번 게시글을 삭제했습니다", id), Util.f("list?boardId=%d", boardId));
	}
}

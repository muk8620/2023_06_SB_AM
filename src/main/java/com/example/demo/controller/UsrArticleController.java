package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Util;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

@Controller
public class UsrArticleController {

	private ArticleService articleService;

	@Autowired
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}

	// 액션 메서드
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData<Article> doAdd(HttpServletRequest req, String title, String body) {

		Rq rq = new Rq(req);
		
		if (rq.getLoginedMemberId() == 0) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}

		if (Util.empty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}

		if (Util.empty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}

		articleService.writeArticle(rq.getLoginedMemberId(), title, body);

		int id = articleService.getLastInsertId();

		return ResultData.from("S-1", Util.f("%d번 게시글이 생성되었습니다", id), "article", articleService.getArticleById(id));
	}

	@RequestMapping("/usr/article/list")
	public String showList(Model model) {
		
		System.out.println("컨트롤러 실행됨?");

		List<Article> articles = articleService.getArticles();
		
		model.addAttribute("articles", articles);

		return "usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, HttpServletRequest req, int id) {

		Article foundArticle = articleService.getForPrintArticle(id);
		
		Rq rq = new Rq(req);
		
		model.addAttribute("loginedMemberId", rq.getLoginedMemberId());
		
		model.addAttribute("article", foundArticle);

		return "usr/article/detail";
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(HttpServletRequest req, int id, String title, String body) {

		
		Rq rq = new Rq(req);
		
		if (rq.getLoginedMemberId() == 0) {
			return Util.jsHistoryBack("로그인 후 이용해주세요");
		}

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시글은 존재하지 않습니다", id));
		}
		
		if (rq.getLoginedMemberId() != foundArticle.getMemberId()) {
			return Util.jsHistoryBack("해당 게시글에 대한 권한이 없습니다");
		}

		articleService.modifyArticle(id, title, body);
		
		return Util.jsReplace(Util.f("%d번 게시글을 수정했습니다", id), Util.f("detail?id=%d", id));
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {

		Rq rq = new Rq(req);
		
		if (rq.getLoginedMemberId() == 0) {
			return Util.jsHistoryBack("로그인 후 이용해주세요");
		}
	

		Article foundArticle = articleService.getArticleById(id);

		if (foundArticle == null) {
			return Util.jsHistoryBack(Util.f("%d번 게시글은 존재하지 않습니다", id));
		}

		if (rq.getLoginedMemberId() != foundArticle.getMemberId()) {
			return Util.jsHistoryBack("해당 게시글에 대한 권한이 없습니다");
		}

		articleService.deleteArticle(id);

		return Util.jsReplace(Util.f("%d번 게시글을 삭제했습니다", id), "list");
	}
}

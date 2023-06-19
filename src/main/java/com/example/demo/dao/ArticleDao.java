package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleDao {

	public Article writeArticle(String title, String body);

	@Select("SELECT * FROM article WHERE id = #{id}")
	public Article getArticleById(int id);
	
	@Update("""
			UPDATE article 
				set updateDate = now()
					, title = #{title}
					, `body` = #{body}
				where id = #{id}
			""")
	public void modifyArticle(int id, String title, String body);

	@Delete("DELETE from article where id = #{id}")
	public void deleteArticle(int id); 
	
	@Select("""
			SELECT * 
				FROM article 
				ORDER BY id DESC
			""")
	public List<Article> getArticles(); 
}

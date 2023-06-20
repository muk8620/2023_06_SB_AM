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
	
	@Select("""
			select * 
				from article
				where id = #{id}
			""")
	public Article getArticleById(int id);
	
	@Update("""
			<script>
				update article
					<set>
						updateDate = now()
						<if test="title != null and title != ''">
							, title = #{title}
						</if>	
						<if test="body != null and body != ''">
							, `body` = #{body}
						</if>	
					</set>
					where id = #{id}
			</script>
			""")
	public void modifyArticle(int id, String title, String body);

	@Delete("""
			delete from article
				where id = #{id}
			""")
	public void deleteArticle(int id); 
	
	@Select("""
			select *
				from article
				order by id DESC
			""")
	public List<Article> getArticles(); 
}

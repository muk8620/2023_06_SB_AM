package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Reply;

@Mapper
public interface ReplyDao {
	
	@Insert("""
			INSERT INTO reply
				SET regDate = NOW()
					, updateDate = NOW()
					, memberId = #{loginedMemberId}
					, relTypeCode = #{relTypeCode}
					, relId = #{relId}
					, `body`= #{body}
			""")
	void writeReply(int loginedMemberId, String relTypeCode, int relId, String body);
	
	@Select("""
			SELECT m.nickname as writer
					, r.id	
					, r.memberId
				    , r.body
				    , r.updateDate
				FROM reply AS r
				INNER JOIN `member` AS m
				ON r.memberId = m.id
				WHERE r.relTypeCode = #{relTypeCode}
				AND r.relId = #{relId}
				ORDER BY r.regDate
			""")
	List<Reply> getReplies(String relTypeCode, int relId);

	@Update("""
			UPDATE reply
			    SET updateDate = NOW()
			        , `body` = #{body}
			    WHERE memberId = #{loginedMemberId}
				AND id = #{id}
			""")
	void modifyReply(int loginedMemberId, int id, String body);

	@Delete("""
			DELETE FROM reply
			    WHERE memberId = #{loginedMemberId}
			    AND id = #{id}
			""")
	void deleteReply(int loginedMemberId, int id);

}

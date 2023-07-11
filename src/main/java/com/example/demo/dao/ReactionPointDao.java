package com.example.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.ReactionPoint;

@Mapper
public interface ReactionPointDao {
		
	@Update("""
			UPDATE reactionPoint
			    SET updateDate = NOW()
			    , `point`= IF(`point` = #{point}, 0, #{point})
			    WHERE memberId = #{memberId}
			    AND relTypeCode = #{relTypeCode}
			    AND relId = #{relId}
			""")
	void doUpdateReactionPoint(String relTypeCode, int relId, int memberId, int point);
	
	@Insert("""
			INSERT INTO reactionPoint
				SET regDate = NOW()
				, updateDate = NOW()
				, memberId = #{memberId}
				, relTypeCode = #{relTypeCode}
				, relId = #{relId}
				, `point`= #{point};
			""")
	void doInsertReactionPoint(String relTypeCode, int relId, int memberId, int point);
	
	@Select("""
			SELECT * 
			    FROM reactionPoint
			    WHERE relTypeCode = #{relTypeCode}
			    AND relId = #{relId}
			    AND memberId = #{memberId}
			""")
	ReactionPoint showReactionPoint(String relTypeCode, int relId, int memberId);

}

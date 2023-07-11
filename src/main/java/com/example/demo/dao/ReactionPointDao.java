package com.example.demo.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.ReactionPoint;

@Mapper
public interface ReactionPointDao {
		
	@Delete("""
			DELETE FROM reactionPoint
                WHERE memberId = #{memberId}
			    AND relTypeCode = #{relTypeCode}
			    AND relId = #{relId}
			""")
	int doDeleteReactionPoint(String relTypeCode, int relId, int memberId, int point);
	
	@Insert("""
			INSERT INTO reactionPoint
				SET regDate = NOW()
					, updateDate = NOW()
					, memberId = #{memberId}
					, relTypeCode = #{relTypeCode}
					, relId = #{relId}
					, `point`= #{point}
			""")
	void doInsertReactionPoint(String relTypeCode, int relId, int memberId, int point);
	
	@Select("""
			SELECT IFNULL(SUM(point), 0) as sumReactionPoint 
			    FROM reactionPoint
			    WHERE relTypeCode = #{relTypeCode}
			    AND relId = #{relId}
			    AND memberId = #{memberId}
			""")
	ReactionPoint getReactionPoint(String relTypeCode, int relId, int memberId);

}

package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.ReactionPoint;

@Mapper
public interface ReactionPointDao {
		
	@Update("""
			
			""")
	void doUpdateGoodReactionPoint(String relTypeCode, int relId, int point);
	
	void doUpdateBadReactionPoint(String relTypeCode, int relId, int point);
	
	void doInsertGoodReactionPoint(String relTypeCode, int relId, int point);
	
	void doInsertBadReactionPoint(String relTypeCode, int relId, int point);
	
	@Select("""
			SELECT * 
			    FROM reactionPoint
			    WHERE relTypeCode = #{relTypeCode}
			    AND relId = #{relId}
			    AND memberId = #{memberId}
			""")
	ReactionPoint showReactionPoint(String relTypeCode, int relId, int memberId);

}

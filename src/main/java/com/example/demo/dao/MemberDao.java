package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.vo.Member;

@Mapper
public interface MemberDao {

	@Insert("""
			INSERT INTO `member`
				SET regDate = NOW()
					, updateDate = NOW()
					, loginId = #{loginId}
					, loginPw = #{loginPw}
					, `name` = #{name}
					, nickname = #{nickname}
					, cellphoneNum = #{cellphoneNum}
					, email = #{email}
			""")
	public void doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email);

	@Select("""
			SELECT *
				FROM `member`
				WHERE id = #{id}
			""")
	public Member getMemberById(int id);

	@Select("SELECT LAST_INSERT_ID()")
	public int getLastInsertId();

	@Select("""
			SELECT *
				FROM `member`
				WHERE loginId = #{loginId}
			""")
	public Member getMemberByLoginId(String loginId);

	@Select("""
			SELECT *
				FROM `member`
				WHERE `name` = #{name}
				AND email = #{email}
			""")
	public Member getMemberByNameAndEmail(String name, String email);
	
	@Select("""
			SELECT *
				FROM `member`
				WHERE nickname = #{nickname}
			""")
	public Member getMemberByNickname(String nickname);
	
	@Update("""
			UPDATE `member`
				SET updateDate = now()
					, nickname = #{nickname}
					, cellphoneNum = #{cellphoneNum}
					, email = #{email}
				where id = #{loginedMemberId}
			""")
	public void doMemberModify(int loginedMemberId, String nickname, String cellphoneNum, String email);
	
	@Update("""
			update `member`
				set loginPw = #{loginPw}
				where id = #{loginedMemberId}
			""")
	public void doPasswordModify(int loginedMemberId, String loginPw);
	
	@Select("""
			<script>
			SELECT COUNT(*)
				FROM `member`
				WHERE 1 = 1
				<if test="authLevel != 0">
					AND authLevel = #{authLevel}
				</if>
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchKeywordType == 'loginId'">
							AND loginId LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'name'">
							AND name LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'nickname'">
							AND nickname LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<otherwise>
							AND (
								loginId LIKE CONCAT('%', #{searchKeyword}, '%')
								OR name LIKE CONCAT('%', #{searchKeyword}, '%')
								OR nickname LIKE CONCAT('%', #{searchKeyword}, '%')
							)
						</otherwise>
					</choose>
				</if>
			</script>
			""")
	public int getMembersCnt(String authLevel, String searchKeywordType, String searchKeyword);

	@Select("""
			<script>
			SELECT *
				FROM `member`
				WHERE 1 = 1
				<if test="authLevel != 0">
					AND authLevel = #{authLevel}
				</if>
				<if test="searchKeyword != ''">
					<choose>
						<when test="searchKeywordType == 'loginId'">
							AND loginId LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'name'">
							AND name LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<when test="searchKeywordType == 'nickname'">
							AND nickname LIKE CONCAT('%', #{searchKeyword}, '%')
						</when>
						<otherwise>
							AND (
								loginId LIKE CONCAT('%', #{searchKeyword}, '%')
								OR name LIKE CONCAT('%', #{searchKeyword}, '%')
								OR nickname LIKE CONCAT('%', #{searchKeyword}, '%')
							)
						</otherwise>
					</choose>
				</if>
				ORDER BY id DESC
				LIMIT #{limitStart}, #{itemsInAPage}
			</script>
			""")
	public List<Member> getMembers(String authLevel, String searchKeywordType, String searchKeyword, int itemsInAPage,
			int limitStart);

	@Update("""
			<script>
				UPDATE `member`
					<set>
						updateDate = NOW(),
						delStatus = 1,
						delDate = NOW()
					</set>
					WHERE id = #{id}
			</script>
			""")
	public void deleteMember(int id);
}
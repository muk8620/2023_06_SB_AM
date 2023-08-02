package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int id;
	private String regDate;
	private String updateDate;
	private String loginId;
	private String loginPw;
	private int authLevel;
	private String name;
	private String nickname;
	private String cellphoneNum;
	private String email;
	private int delStatus;
	private String delDate;
	
	public String delStatusStr() {
		if (delStatus == 0) {
			return "미삭제";
		}
		return "삭제";
	}

	public String delDateStr() {
		if (delDate == null) {
			return "없음";
		}
		return delDate.substring(2, 16);
	}
}

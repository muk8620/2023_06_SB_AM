package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private String title;
	private String body;
	private int memberId;
	private int boardId;
	private int hitCnt;
	
	private String writer;
	private int goodReactionPoint;
	private int badReactionPoint;
	private int sumReactionPoint;
	private String boardName;
	
	public String getForPrintBody() {
		return this.body.replaceAll("\n", "<br />");
	}
}

package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	
		private int id;
		private String regDate;
		private String updateDate;
		private int memberId;
		private String relTypeCode;
		private int relId;
		private String body;
		
		private String writer;
		
		public String getForPrintBody() {
			return this.body.replaceAll("\n", "<br />");
		}

}

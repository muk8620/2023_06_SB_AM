<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Detail"/>
<%@ include file= "../common/header.jsp" %>
	
		<tbody>
			<tr>
				<th>번호</th>
				<td>${article.id }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${article.regDate }</td>
			</tr>
			<tr>
				<th>수정일</th>
				<td>${article.updateDate }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${article.title }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${article.body }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${article.memberId }</td>
			</tr>
		</tbody>
	</table>

<%@ include file= "../common/footer.jsp" %>
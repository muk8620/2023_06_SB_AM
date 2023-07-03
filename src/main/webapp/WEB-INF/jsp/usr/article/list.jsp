<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="List" />
<%@ include file="../common/header.jsp" %>

	<section class="mt-8">
		<div class="container mx-auto">
			<div class="table-box-type-1">
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>작성일</th>
							<th>제목</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles }">
							<tr>
								<td>${article.id }</td>
								<td>${article.regDate.substring(2, 16) }</td>
								<td><a class="hover:underline" href="detail?id=${article.id }">${article.title }</a></td>
								<td>${article.writer }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:if test="${rq.getLoginedMemberId() != 0}">
					<div class="flex justify-end">
						<a class="btn btn-active btn-neutral" href="write?boardId=${article.boardId }">글쓰기</a>
					</div>
				</c:if>	
			</div>
		</div>
	</section>
	
<%@ include file="../common/footer.jsp" %>
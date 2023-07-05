<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="${board.name} 게시판" />
<%@ include file="../common/header.jsp" %>
	
	<script>
		function isEmpty(searchWord) {
			if (!searchWord) {
				alert('검색어를 입력해주세요.');
				return history.back();
			}
		}
	</script>
	
	<section class="mt-8">
		<div class="container mx-auto">
			<div class="flex justify-between items-end mb-2">
				<span >총 : ${articlesCnt }개</span>
				<form>
					<input type="hidden" name="boardId" value="${board.id }"/>
					<select class="select select-bordered max-w-xs" name="searchType" >
						<option value="title">제목</option>
						<option value="body">내용</option>
						<option value="writer">작성자</option>
					</select>
					<input name="searchWord" class="input input-bordered max-w-xs" type="text" placeholder="검색어를 입력해주세요."/>
					<button class="btn btn-outline max-w-xs" onclick="isEmpty(searchWord)">검색</button>
				</form>
			</div>
			<div class="table-box-type-1">
				<table class="table">
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
					<div class="mt-1 flex justify-end">
						<a class="btn btn-active btn-neutral" href="write">글쓰기</a>
					</div>
				</c:if>	
			</div>
			
			<div class="join mt-1 flex justify-center">
				<div>
					<c:set var="pageMenuLen" value="5" />
					<c:set var="startPage" value="${page - pageMenuLen >= 1 ? page - pageMenuLen : 1 }" />
					<c:set var="endPage" value="${page + pageMenuLen <= pagesCnt ? page + pageMenuLen : pagesCnt }" />
					<c:if test="${page == 1 }">
						<a class="join-item btn btn-disabled" >«</a>
						<a class="join-item btn btn-disabled" >&lt;</a>
					</c:if>
					<c:if test="${page > 1 }">
						<a class="join-item btn" href="?boardId=${board.id }&page=1">«</a>
						<a class="join-item btn" href="?boardId=${board.id }&page=${page - 1}">&lt;</a>
					</c:if>
					<c:forEach begin="${startPage }" end="${endPage }" var="i">
						<a class="join-item btn ${page == i ? 'btn-active' : '' }" href="?boardId=${board.id }&page=${i }">${i }</a>
					</c:forEach>
					<c:if test="${page == pagesCnt }">
						<a class="join-item btn btn-disabled" >&gt;</a>
						<a class="join-item btn btn-disabled" >»</a>
					</c:if>
					<c:if test="${page < pagesCnt }">
						<a class="join-item btn" href="?boardId=${board.id }&page=${page + 1}">&gt;</a>
						<a class="join-item btn" href="?boardId=${board.id }&page=${pagesCnt }">»</a>
					</c:if>
				</div>
			</div>
		</div>
	</section>
	
<%@ include file="../common/footer.jsp" %>
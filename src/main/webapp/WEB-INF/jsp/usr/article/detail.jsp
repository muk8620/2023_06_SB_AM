
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Detail" />
<%@ include file="../common/header.jsp"%>
<script>
	function ArticleDetail_getReactionPoint() {
		$.get('../reactionPoint/getReactionPoint', {
			relTypeCode : 'article',
			relId : ${article.id}
		}, function(data){
			
			console.log($('#goodBtn').attr('href'));
			console.log($('#badBtn').prop('href'));
			
			if (data.data1.sumReactionPoint == 1) {
				let goodBtn = $('#goodBtn');
				goodBtn.removeClass('btn-outline');
				goodBtn.attr('href', '../reactionPoint/doDeleteReactionPoint?relTypeCode=article&relId=${article.id }&point=1');
			} else if (data.data1.sumReactionPoint == -1) {
				let badBtn = $('#badBtn');
				badBtn.removeClass('btn-outline');
				badBtn.attr('href', '../reactionPoint/doDeleteReactionPoint?relTypeCode=article&relId=${article.id }&point=-1');
			}
			
		}, 'json')
		
	}
	
	$(function(){
		ArticleDetail_getReactionPoint();
	})
	
</script>

<section class="mt-8">
	<div class="container mx-auto">
		<div class="table-box-type-1">
			<table class="table">
				<colgroup>
					<col width="200" />
				</colgroup>
				<tbody>
					<tr>
						<th>번호</th>
						<td><span class="badge badge-neutral">${article.id }</span></td>
					</tr>
					<tr>
						<th>조회수</th>
						<td><span id="articleDetail_increaseHitCnt">${article.hitCnt }</span></td>
					</tr>
					<tr>
						<th>추천</th>
						<td><c:if test="${rq.getLoginedMemberId() == 0 }">
								<span>${article.sumReactionPoint }</span>
							</c:if> <c:if test="${rq.getLoginedMemberId() != 0 }">
								<div>
									<a id="goodBtn"
										class="btn btn-outline btn-info btn-xs"
										href="../reactionPoint/doInsertReactionPoint?relTypeCode=article&relId=${article.id }&point=1">좋아요👍
									</a> <span class="ml-2">좋아요 : ${article.goodReactionPoint }</span>
								</div>
								<div class="mt-2">
									<a id="badBtn"
										class="btn btn-outline btn-error btn-xs"
										href="../reactionPoint/doInsertReactionPoint?relTypeCode=article&relId=${article.id }&point=-1">싫어요👎
									</a> <span class="ml-2">싫어요 : ${article.badReactionPoint }</span>
								</div>
							</c:if></td>
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
						<td>${article.writer }</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<button class="btn btn-accent btn-neutral"
				onclick="location.replace('list?boardId=${article.boardId}');">뒤로가기</button>

			<c:if test="${rq.getLoginedMemberId() == article.memberId }">
				<a class="btn btn-active btn-neutral" href="modify?id=${article.id}">수정</a>
				<a class="btn btn-active btn-neutral"
					href="doDelete?id=${article.id}"
					onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
			</c:if>
		</div>
	</div>
</section>

<%@ include file="../common/footer.jsp"%>
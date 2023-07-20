
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Detail" />
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/toastUi.jsp"%>

	<script>
		function getReactionPoint() {
			$.get('../reactionPoint/getReactionPoint', {
				relTypeCode : 'article',
				relId : ${article.id}
			}, function(data){
				
				console.log(data);
				
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
			getReactionPoint();
		})
	
	</script>

	<section class="mt-8">
		<div class="container mx-auto pb-5 border-bottom-line">
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
								<td>
								<c:if test="${rq.getLoginedMemberId() == 0 }">
									<span>${article.sumReactionPoint }</span>
								</c:if> 
								<c:if test="${rq.getLoginedMemberId() != 0 }">
									<div>
										<a id="goodBtn" class="btn btn-outline btn-info btn-xs" href="../reactionPoint/doInsertReactionPoint?relTypeCode=article&relId=${article.id }&point=1">좋아요👍</a><span class="ml-2">좋아요 : ${article.goodReactionPoint }</span>
									</div>
									<div class="mt-2">
										<a id="badBtn" class="btn btn-outline btn-error btn-xs" href="../reactionPoint/doInsertReactionPoint?relTypeCode=article&relId=${article.id }&point=-1">싫어요👎</a><span class="ml-2">싫어요 : ${article.badReactionPoint }</span>
									</div>
								</c:if>
								</td>
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
							<td>
								<div class="toast-ui-viewer">
									<script type="text/x-template">${article.body }</script>
								</div>
							</td>
						</tr>
						<tr>
							<th>작성자</th>
							<td>${article.writer }</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="mt-2">
				<button class="btn btn-accent btn-neutral" onclick="location.replace('list?boardId=${article.boardId}');">뒤로가기</button>
	
				<c:if test="${rq.getLoginedMemberId() == article.memberId }">
					<a class="btn btn-active btn-neutral" href="modify?id=${article.id}">수정</a>
					<a class="btn btn-active btn-neutral" href="doDelete?id=${article.id}" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
				</c:if>
			</div>
		</div>
	</section>
	
	<script>
		
		originalId = null;
		originalForm = null;
	
		function replyModify_getForm(replyId, i) {
			$.get('../reply/getReplyContent', {
				id : replyId
			}, function(data){
				
				if (originalForm != null) {
					replyModify_cancle(originalId);
				}
				
				let replyContent = $('#' + i);
				
				originalId = i;
				originalForm = replyContent.html();
				
				let addHtml = `
					<form action="../reply/doModify" method="POST">
						<input type="hidden" name="id" value="\${data.data1.id}" /> 
						<div class="mt-4 border border-gray-400 rounded-lg text-base p-4">
							<div class="mb-2"><span>${rq.loginedMember.nickname }</span></div>
							<textarea class="textarea textarea-info w-full" name="body" placeholder="댓글을 남겨주세요.">\${data.data1.body}</textarea>
							<div class="mt-1 flex justify-end">
								<a class="btn btn-outline btn-neutral btn-sm mr-2" onclick="replyModify_cancle(\${i});">취소</a>
								<button class="btn btn-outline btn-neutral btn-sm">수정</button>
							</div>
						</div>
					</form>
					`;
					
				replyContent.empty().html('');
				replyContent.append(addHtml);
				
			}, 'json')
			
		}
		
		function replyModify_cancle(i) {
			let replyContent = $('#' + i);
			replyContent.html(originalForm);
			
			originalId = null;
			originalForm = null;
		}
		
	</script>
	
	<section class="my-5 text-xl">
		<div class="container mx-auto px-3 ">
			<h2>댓글</h2>
	
			<c:forEach var="reply" items="${replies }" varStatus="status">
				<div id="${status.count }" class="text-base py-4 pl-16 border-bottom-line">
					<div class="flex justify-between items-end">
						<div class="font-semibold"><span>${reply.writer }</span></div>
						<c:if test="${reply.memberId == rq.getLoginedMemberId()}">
							<div class="dropdown">
								<label tabindex="0" class="btn btn-sm mr-8">⁝</label>
								<ul tabindex="0" class="dropdown-content z-[1] menu p-1 shadow rounded-box">
									<li><button class="button btn-sm btn-outline btn-neutral" onclick="replyModify_getForm(${reply.id }, ${status.count })">수정</button></li>
									<li><a class="button btn-sm btn-outline btn-neutral" href="../reply/doDelete?id=${reply.id }" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a></li>
								</ul>
							</div>
						</c:if>
					</div>
					<div class="my-1 text-lg pl-2"><span>${reply.getForPrintBody() }</span></div>
					<div class="text-xs text-gray-400"><span>${reply.updateDate }</span></div>
				</div>
			</c:forEach>
	
			<c:if test="${rq.getLoginedMemberId() != 0 }">
				<form action="../reply/doWrite" method="POST">
					<input type="hidden" name="relTypeCode" value="article" /> 
					<input type="hidden" name="relId" value="${article.id }" />
					<div class="mt-4 border border-gray-400 rounded-lg text-base p-4">
						<div class="mb-2"><span>${rq.loginedMember.nickname }</span></div>
						<textarea class="textarea textarea-info w-full" name="body" placeholder="댓글을 남겨주세요."></textarea>
						<div class="mt-1 flex justify-end"><button class="btn btn-outline btn-neutral btn-sm">등록</button></div>
					</div>
				</form>
			</c:if>
		</div>
	</section>

<%@ include file="../common/footer.jsp"%>
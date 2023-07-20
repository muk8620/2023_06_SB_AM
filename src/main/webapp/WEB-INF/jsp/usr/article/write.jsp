<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Write" />
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/toastUi.jsp" %>

	<section class="mt-8">
		<div class="container mx-auto">
			<form action="doWrite" method="GET" onsubmit="submitForm(this); return false;">
				<input type="hidden" name="body"/>
				<div class="table-box-type-1">
					<table class="table">
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>게시판</th>
								<td>
									<label>
										<input type="radio" name="boardId" value="1"/>
										&nbsp;공지사항
									</label>
									&nbsp;
									<label>
										<input type="radio" name="boardId" value="2" checked/>
										&nbsp;자유
									</label>
								</td>
							</tr>
							<tr>
								<th>제목</th>
								<td><input class="input input-bordered input-accent w-full" type="text" name="title" placeholder="제목을 입력해주세요." /></td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
									<div class="toast-ui-editor">
										<script type="text/x-template"></script>
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2"><button class="btn btn-active btn-neutral" >글쓰기</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<div>
				<button class="btn btn-active btn-neutral" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</section>

<%@ include file="../common/footer.jsp" %>
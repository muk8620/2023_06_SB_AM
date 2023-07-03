<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Write" />
<%@ include file="../common/header.jsp" %>
	
	<section class="mt-8">
		<div class="container mx-auto">
			<form action="doWrite" method="GET">
				<div class="table-box-type-1">
					<table>
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>번호</th>
								<td>${id + 1}</td>
							</tr>
							<tr>
								<th>제목</th>
								<td><input class="w-96" type="text" name="title" placeholder="제목을 입력해주세요." /></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><textarea name="body" placeholder="내용을 입력해주세요."></textarea></td>
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
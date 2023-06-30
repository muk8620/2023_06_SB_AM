<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Modify" />
<%@ include file="../common/header.jsp" %>

	<section class="mt-8">
		<div class="container mx-auto">
			<form action="doModify" method="POST">
				<div class="table-box-type-1">
					<table>
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>제목</th>
								<td><input class="w-96" type="text" name="title" placeholder="제목을 입력해주세요." value="${title }" /></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><input class="w-96" type="text" name="body" placeholder="내용을 입력해주세요." value="${body }"/></td>
							</tr>
							<tr>
								<td colspan="2"><button>수정</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<div>
				<button class="btn-text-link" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</section>
	
<%@ include file="../common/footer.jsp" %>
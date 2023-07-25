<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Find LoginId" />
<%@ include file="../common/header.jsp" %>

	<section class="mt-8">
		<div class="container mx-auto">
			<form action="doFindLoginId" method="POST">
				<div class="table-box-type-1">
					<table>
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>이름</th>
								<td><input class="input input-bordered input-accent w-96" type="text" name="name" placeholder="이름을 입력해주세요" /></td>
							</tr>
							<tr>
								<th>이메일</th>
								<td><input class="input input-bordered input-accent w-96" type="text" name="email" placeholder="이메일을 입력해주세요" /></td>
							</tr>
							<tr>
								<td colspan="2"><button class="btn btn-accent btn-sm">아이디 찾기</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<div class="mt-2 flex justify-between">
				<button class="btn btn-accent btn-sm" onclick="history.back();">뒤로가기</button>
				<div>
					<a class="btn btn-accent btn-sm" href="findLoginPw">비밀번호 찾기</a>
					<a class="btn btn-accent btn-sm" href="login">로그인</a>
				</div>
			</div>
		</div>
	</section>
	
<%@ include file="../common/footer.jsp" %>
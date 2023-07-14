<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Page" />
<%@ include file="../common/header.jsp"%>

	<section class="mt-8">
		<div class="container mx-auto pb-5 border-bottom-line">
			<form action="doMemberModify">
				<div class="table-box-type-1">
					<table class="table">
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>가입일</th>
								<td>${rq.loginedMember.regDate }</td>
							</tr>
							<tr>
								<th>아이디</th>
								<td>${rq.loginedMember.loginId }</td>
							</tr>
							<tr>
								<th>이름</th>
								<td>${rq.loginedMember.name }</td>
							</tr>
							<tr>
								<th>닉네임(수정가능)</th>
								<td><input class="input input-bordered input-accent w-96" type="text" name="nickname" placeholder="닉네임을 입력해주세요." value="${rq.loginedMember.nickname }"/></td>
							</tr>
							<tr>
								<th>전화번호(수정가능)</th>
								<td><input class="input input-bordered input-accent w-96" type="text" name="cellphoneNum" placeholder="전화번호를 입력해주세요." value="${rq.loginedMember.cellphoneNum }"/></td>
							</tr>
							<tr>
								<th>이메일(수정가능)</th>
								<td><input class="input input-bordered input-accent w-96" type="text" name="email" placeholder="이메일을 입력해주세요." value="${rq.loginedMember.email }"/></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="mt-2 flex justify-between">
					<button class="btn btn-accent btn-neutral" onclick="history.back();">뒤로가기</button>
					<div>
						<a class="btn btn-accent btn-neutral" href="loginPwModify">비밀번호 변경</a>
						<button class="btn btn-accent btn-neutral">수정</button>
					</div>
				</div>
			</form>
		</div>
	</section>

<%@ include file="../common/footer.jsp"%>
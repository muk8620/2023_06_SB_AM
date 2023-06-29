<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Login"/>
<%@ include file= "../common/header.jsp" %>

	<section class="mt-8">
		<div class="container mx-auto">
			<form action="doLogin" method="POST">
				<div class="table-box-type-1">
						<table>
							<colgroup>
								<col width="200" />
							</colgroup>
							<tbody>
								<tr>
									<th>아이디</th>
									<td> <input name="loginId" placeholder="아이디를 입력해주세요" /> </td>
								</tr>
								<tr>
									<th>비밀번호</th>
									<td><input name="loginPw" placeholder="비밀번호를 입력해주세요" /></td>
								</tr>
								<tr>
									<th colspan="2"><button>로그인</button></th>
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
	
<%@ include file= "../common/footer.jsp" %>
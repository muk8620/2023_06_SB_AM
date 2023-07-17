<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Join" />
<%@ include file="../common/header.jsp"%>

<script>

	validLoginId = '';
	
	function join_submitForm(form) {
		
		form.loginId.value = form.loginId.value.trim();
		if (form.loginId.value.length == 0) {
			alert('아이디를 입력해주세요.');
			form.loginId.focus();
			return;
		}
		
		if (form.loginId.value != validLoginId) {
			alert(form.loginId.value + '은(는) 사용할 수 없는 아이디입니다');
			form.loginId.value = '';
			form.loginId.focus();
			return;
		}
		
		form.loginPw.value = form.loginPw.value.trim();
		if (form.loginPw.value.length == 0) {
			alert('비밀번호를 입력해주세요.');
			form.loginPw.focus();
			return;
		}
		
		form.loginPwCheck.value = form.loginPwCheck.value.trim();
		if (form.loginPwCheck.value.length == 0) {
			alert('비밀번호 확인을 입력해주세요.');
			form.loginPwCheck.focus();
			return;
		}
		
		if (form.loginPw.value != form.loginPwCheck.value) {
			alert('비밀번호가 일치하지 않습니다.');
			form.loginPw.value = '';
			form.loginPwCheck.value = '';
			form.loginPw.focus();
			return;
		}
		
		form.name.value = form.name.value.trim();
		if (form.name.value.length == 0) {
			alert('이름을 입력해주세요.');
			form.name.focus();
			return;
		}
		
		form.nickname.value = form.nickname.value.trim();
		if (form.nickname.value.length == 0) {
			alert('닉네임을 입력해주세요.');
			form.nickname.focus();
			return;
		}
		
		form.cellphoneNum.value = form.cellphoneNum.value.trim();
		if (form.cellphoneNum.value.length == 0) {
			alert('전화번호를 입력해주세요.');
			form.cellphoneNum.focus();
			return;
		}
		
		form.email.value = form.email.value.trim();
		if (form.email.value.length == 0) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			return;
		}
		
		form.submit();
	}
	
	function loginIdDupCheck(el) {
		
		let loginIdDupCheckMsg = $('#loginIdDupCheckMsg');
		
		let regExp = /^[A-Za-z0-9]{5,16}$/;
		
		loginIdDupCheckMsg.empty();
		
		el.value = el.value.trim();
		
		if (el.value.length == 0) {
			loginIdDupCheckMsg.html('<span class="text-red-400">아이디는 필수 입력 정보입니다.</span>');
			return;
		}
		
		if (!regExp.test(el.value)) {
			loginIdDupCheckMsg.html('<span class="text-red-400">아이디는 영문자 5~16자리로 입력해 주세요.</span>');
			return;
		}
		
		$.get('loginIdDupCheck', {
			loginId : el.value
		}, function(data){
			console.log(data);
			
			if (data.fail) {
				loginIdDupCheckMsg.html(`<span class="text-red-400">\${data.data1}은(는) \${data.msg}</span>`);
				validLoginId = '';
			} else {
				loginIdDupCheckMsg.html(`<span class="text-green-400">\${data.data1}은(는) \${data.msg}</span>`);
				validLoginId = data.data1;
			}
			
		}, 'json')
	}
</script>

	<section class="mt-8">
		<div class="container mx-auto pb-5 border-bottom-line">
			<form action="doJoin" onsubmit="join_submitForm(this); return false;">
				<div class="table-box-type-1">
					<table class="table">
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr height="100">
								<th>아이디</th>
								<td>
									<input class="input input-bordered input-accent w-3/5" type="text" name="loginId" placeholder="아이디를 입력해주세요." onblur="loginIdDupCheck(this);"/>
									<div id="loginIdDupCheckMsg" class="text-base mt-2 h-5"></div>
								</td>
							</tr>
							<tr>
								<th>비밀번호</th>
								<td><input class="input input-bordered input-accent w-3/5" type="password" name="loginPw" placeholder="비밀번호를 입력해주세요."/></td>
							</tr>
							<tr>
								<th>비밀번호 확인</th>
								<td><input class="input input-bordered input-accent w-3/5" type="password" name="loginPwCheck" placeholder="비밀번호 확인을 입력해주세요."/></td>
							</tr>
							<tr>
								<th>이름</th>
								<td><input class="input input-bordered input-accent w-3/5" type="text" name="name" placeholder="이름을 입력해주세요."/></td>
							</tr>
							<tr>
								<th>닉네임</th>
								<td><input class="input input-bordered input-accent w-3/5" type="text" name="nickname" placeholder="닉네임을 입력해주세요."/></td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td><input class="input input-bordered input-accent w-3/5" type="text" name="cellphoneNum" placeholder="전화번호를 입력해주세요."/></td>
							</tr>
							<tr>
								<th>이메일</th>
								<td><input class="input input-bordered input-accent w-3/5" type="email" name="email" placeholder="이메일을 입력해주세요."/></td>
							</tr>
							<tr>
								<th colspan="2"><button class="btn btn-active btn-neutral">회원가입</button></th>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="mt-2 flex justify-between">
					<button class="btn btn-accent btn-neutral" onclick="history.back();">뒤로가기</button>
				</div>
			</form>
		</div>
	</section>

<%@ include file="../common/footer.jsp"%>
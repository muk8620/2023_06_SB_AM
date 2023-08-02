<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html data-theme="light">
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="/resource/images/favicon.ico" />
<!-- 테일윈드 불러오기 -->
<!-- 노말라이즈, 라이브러리 -->
<link href="https://cdn.jsdelivr.net/npm/daisyui@3.1.7/dist/full.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<link rel="stylesheet" href="/resource/common.css" />
<script src="/resource/common.js" defer="defer"></script>
<title>${pageTitle }</title>
<script>
	function Theme_toggle() {
		const theme = localStorage.getItem("theme") ?? "light";
		
		if (theme == 'light') {
			localStorage.setItem("theme", "dark");
		} else {
			localStorage.setItem("theme", "light");
		}
		
		location.reload();
	}
	
	function Theme_applyTo(themeName) {
		$('html').attr('data-theme', themeName);
	}
	
	function Theme_init() {
		const theme = localStorage.getItem("theme") ?? "light";
		Theme_applyTo(theme);
	}
	
	Theme_init();
</script>
</head>
<body>
	<div class="h-20 flex container mx-auto text-3xl">
		<a class="px-3 flex items-center" href="/"><span>로고</span></a>
		<div class="flex-grow"></div>
		<ul class="flex menu menu-horizontal bg-base-200">
			<li>
				<a class="h-full px-3 theme-toggle flex items-center" href="javascript:Theme_toggle();">
					<span><i class="fa-regular fa-sun"></i></span>
					<span><i class="fa-solid fa-sun"></i></span>
				</a>
			</li>
			<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/">HOME</a></li>
			<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/article/list?boardId=1">NOTICE</a></li>
			<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/article/list?boardId=2">FREE</a></li>
			<c:if test="${rq.getLoginedMemberId() == 0}">
				<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/member/join">JOIN</a></li>
				<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/member/login">LOGIN</a></li>
			</c:if>
			<c:if test="${rq.getLoginedMemberId() != 0}">
				<c:choose>
					<c:when test="${rq.getLoginedMember().authLevel != 3 }">
						<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/usr/member/myPage"><span>MYPAGE</span></a></li>
					</c:when>
					<c:otherwise>
						<li class="hover:underline"><a class="h-full px-3 flex items-center" href="/adm/member/list"><span>회원관리</span></a></li>
					</c:otherwise>
				</c:choose>
				<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/member/doLogout">LOGOUT</a></li>
			</c:if>
			<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/api/APITest">API</a></li>
			<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/api/APITest2">API2</a></li>
		</ul>
	</div>
	
	<section class="my-3 text-2xl">
		<div class="container mx-auto px-3">
			<h1>${pageTitle }</h1>
		</div>
	</section>
	
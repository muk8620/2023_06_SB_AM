<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
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
<!-- dompurify -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/dompurify/2.3.0/purify.min.js"></script>

<!-- 토스트 UI 에디터 코어 -->
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />

<!-- 토스트 UI 에디터 플러그인, 컬러피커 -->
<link rel="stylesheet" href="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.css" />
<script src="https://uicdn.toast.com/tui-color-picker/latest/tui-color-picker.min.js"></script>
<link rel="stylesheet" href="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.css" />
<script src="https://uicdn.toast.com/editor-plugin-color-syntax/latest/toastui-editor-plugin-color-syntax.min.js"></script>

<!-- 토스트 UI 차트 -->
<link rel="stylesheet" href="https://uicdn.toast.com/chart/latest/toastui-chart.css">
<script src="https://uicdn.toast.com/chart/latest/toastui-chart.js"></script>
<!-- 토스트 UI 차트와 토스트 UI 에디터를 연결  -->
<script src="https://uicdn.toast.com/editor-plugin-chart/latest/toastui-editor-plugin-chart.min.js"></script>

<!-- 토스트 UI 에디터 플러그인, 코드 신텍스 하이라이터 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/prism/1.24.1/themes/prism.min.css">
<link rel="stylesheet" href="https://uicdn.toast.com/editor-plugin-code-syntax-highlight/latest/toastui-editor-plugin-code-syntax-highlight.min.css">
<script src="https://uicdn.toast.com/editor-plugin-code-syntax-highlight/latest/toastui-editor-plugin-code-syntax-highlight-all.min.js"></script>

<!-- 토스트 UI 에디터 플러그인, 테이블 셀 병합 -->
<script src="https://uicdn.toast.com/editor-plugin-table-merged-cell/latest/toastui-editor-plugin-table-merged-cell.min.js"></script>

<!-- 토스트 UI 에디터 플러그인, katex -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.13.13/katex.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/KaTeX/0.13.13/katex.min.css">

<!-- 토스트 UI 에디터 플러그인, UML -->
<script src="https://uicdn.toast.com/editor-plugin-uml/latest/toastui-editor-plugin-uml.min.js"></script>

<title>${pageTitle }</title>
</head>
<body>
	<div class="h-20 flex container mx-auto text-4xl">
		<a class="px-3 flex items-center" href="/"><span>로고</span></a>
		<div class="flex-grow"></div>
		<ul class="flex menu menu-horizontal bg-base-200">
			<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/">HOME</a></li>
			<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/article/list?boardId=1">NOTICE</a></li>
			<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/article/list?boardId=2">FREE</a></li>
			<c:if test="${rq.getLoginedMemberId() == 0}">
				<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/member/join">JOIN</a></li>
				<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/member/login">LOGIN</a></li>
			</c:if>
			<c:if test="${rq.getLoginedMemberId() != 0}">
				<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/member/myPage">MYPAGE</a></li>
				<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/member/doLogout">LOGOUT</a></li>
			</c:if>
			<li class="hover:underline"><a class="h-full px-3 flex items-center text-3xl" href="/usr/api/APITest">API</a></li>
		</ul>
	</div>
	
	<section class="my-3 text-2xl">
		<div class="container mx-auto px-3">
			<h1>${pageTitle }</h1>
		</div>
	</section>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 테일윈드 불러오기 -->
<!-- 노말라이즈, 라이브러리 -->
<script src="https://unpkg.com/tailwindcss-jit-cdn"></script>
<!-- 제이쿼리 불러오기 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<!-- 폰트어썸 불러오기 -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />
<title>${pageTitle }</title>
</head>
<body>
	<div>
		<a href="/">로고</a>
		<ul>
			<li><a href="/">HOME</a></li>
			<li><a href="/usr/article/list">LIST</a></li>
		</ul>
	</div>
	
	<section>
		<div>
			<h1>${pageTitle }&nbsp;Page</h1>
		</div>
	</section>


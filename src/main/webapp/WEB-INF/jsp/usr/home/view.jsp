<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 뷰</title>
</head>
<body>
	<c:forEach var="file" items="${files }">
		<div>
			<img src="/usr/home/file/${file.id}" />
		</div>
	</c:forEach>
	
	<div>
		<a href="/"><span>메인으로</span></a>
	</div>
</body>
</html>
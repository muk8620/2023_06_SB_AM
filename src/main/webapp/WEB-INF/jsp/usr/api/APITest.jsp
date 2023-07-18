<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="APITest" />
<%@ include file="../common/header.jsp" %>
<script>

	const API_KEY = 'em%2B0%2F1Ku2ghuHVXBFebN%2F9kXmnRwsPRoLVbk8j5UuXk2EX6bTs3JDGM0fZgdUYSLSN9mFLgfK%2Bw0Bm1ZR%2Buv9A%3D%3D';
	
	async function getData() {
		const url = 'http://apis.data.go.kr/1262000/CountryCovid19SafetyServiceNew/getCountrySafetyNewsListNew?serviceKey=' + API_KEY + '&numOfRows=10&pageNo=1';
		
		const response = await fetch(url);
		const data = await response.json();
		
		console.log(data);
		
		$('.API-content').html(data.data[0].txt_origin_cn);
		$('.API-content').append(`<div>\${data.data[0].title}</div>`);
	}
	
	getData();
</script>

<div class="container mx-auto">
	<div class="API-content"></div>
</div>

<%@ include file="../common/footer.jsp" %>
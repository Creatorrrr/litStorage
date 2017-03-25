<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">


<title>작품 검색</title>
<style>
		.literatureBox {
			display: inline-block;
		    margin: 10px;
		    border: 3px solid #73AD21;
		}
</style>
</head>
<body>
<%@ include file="header.jspf"%>
<!-- 작품 검색창 -->
		<select name="type" id="type">
			<option value="id">작가 아이디</option>
			<option value="name">작품 이름</option>
		</select> <input type="text" name="keyword"> <input type="button"
			name="search" id="btn" value="검색">
	<!-- 장르 선택 버튼들 -->
	<h3>장르</h3>
	<c:forEach items="${genreList }" var="genre">
		<button onclick="getList('${genre.title}')" value="${genre.title }">${genre.title }</button><br>
	</c:forEach>
	<!-- 작품 목록. 처음엔 게시판 첫번째 이름 장르의 작품 목록이 보이고 검색하면 검색결과 보임-->
	<div id="result">
	<c:forEach items="${literatures }" var="literature">
	<div class="literatureBox">
		<table border="1">
			<tr>
						<td>작품명</td>
						<td><a
							href="${ctx}/literature/profile.do?id=${literature.id}">${literature.name }</a></td>
					</tr>
					<tr>
						<td>작가</td>
						<td>${literature.creator.id }</td>
					</tr>
					<tr>
						<td>장르</td>
						<td>${literature.genre }</td>
					</tr>
					<tr>
						<td>소개</td>
						<td>${literature.introduce }</td>
					</tr>
					<tr>
						<td>조회수</td>
						<td>${literature.hits }</td>
					</tr>
		</table>
	</div>
	</c:forEach>
	</div>


	<!-- script area -->

	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

	<script>
	/* 장르선택 스크립트 */
	var getList = function(title) {
	$.ajax({
		url : "${ctx}/genreList.do",
		data : {type : "newGenre",
				genre : title,
				from : "allList"},
		type : "get",
		dataType : "xml",
		success : function(xml) {
				
				var xmlData = $(xml).find("literature");
				var listLength = xmlData.length;
				$("#result").empty();			
				if (listLength) {
					var contentStr = "";
					$(xmlData).each(function() {
						contentStr += "<div class='literatureBox'><table border='1'><tr><td>작품명</td><td><a href='${ctx}/episode/list.do?LiteratureId="+ $(this).find("id").text() + "'>"+ $(this).find("name:first").text()
								+ "</a></td></tr><tr><td>작가</td><td>"+ $(this).find("creator").find("id").text() + "</td></tr><tr>"
								+"<td>장르</td><td>"+ $(this).find("genre").text()
								+"</td></tr><tr><td>소개</td><td>"+$(this).find("introduce").text()
								+ "</td></tr><tr><td>조회수</td><td>"+$(this).find("hits").text()+"</td></tr></table></div>";
					});
					$("#result").append(contentStr);
				}
			}
		});
};

	$(document).ready(function() {

	/* ajax로 작품 다른거 검색하기*/
						
	$("input[name='search']").click(function() {
		$.ajax({
			url : "${ctx}/literature/search.do",
			data : {type : $("#type option:selected").val(),
					keyword : $("input[name='keyword']").val()},
			type : "post",
			dataType : "xml",
			success : function(xml) {
					var xmlData = $(xml).find("literature");
					var listLength = xmlData.length;
					$("#result").empty();			
					if (listLength) {
						var contentStr = "";
						$(xmlData).each(function() {
							contentStr += "<div class='literatureBox'><table border='1'><tr><td>작품명</td><td><a href='${ctx}/litStorage/profile.do?id="+ $(this).find("id").text() + "'>"+ $(this).find("name:first").text()
							+ "</a></td></tr><tr><td>작가</td><td>"+ $(this).find("creator").find("id").text() + "</td></tr><tr>"
							+"<td>장르</td><td>"+ $(this).find("genre").text()
							+"</td></tr><tr><td>소개</td><td>"+$(this).find("introduce").text()
							+ "</td></tr><tr><td>조회수</td><td>"+$(this).find("hits").text()+"</td></tr></table></div>";
						});
						$("#result").append(contentStr);
					}
				}
			});
		});
	
		
	});
	</script>
	<script>
		
	</script>
</body>
</html>
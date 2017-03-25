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


<title>작품 저장소 검색</title>
<style>
		.litStorageBox {
			display: inline-block;
		    margin: 10px;
		    border: 3px solid #73AD21;
		}
</style>
</head>
<body>
<%@ include file="header.jspf"%>

	<form method="post">
		<select name="type" id="type">
			<option value="id">생성자 아이디</option>
			<option value="name">저장소 이름</option>
		</select> <input type="text" name="keyword"> <input type="button"
			name="search" id="btn" value="검색">
	</form>
	<div id="result">
	</div>



	

	<!-- script area -->

	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

	<script>
	
	$(document).ready(function() {

	/* search ajax */
						
	$("input[name='search']").click(function() {
		$.ajax({
			url : "${ctx}/litStorage/search.do",
			data : {type : $("#type option:selected").val(),
					keyword : $("input[name='keyword']").val()},
			type : "post",
			dataType : "xml",
			success : function(xml) {
					var xmlData = $(xml).find("litStorage");
					var listLength = xmlData.length;
					$("#result").empty();			
					if (listLength) {
						var contentStr = "";
						$(xmlData).each(function() {
							contentStr += "<div class='litStorageBox'><table border='1'><tr><td>이름</td><td><a href='${ctx}/litStorage/profile.do?id="+ $(this).find("id").text() + "'>"+ $(this).find("name:first").text()
									+ "</a></td></tr><tr><td>소개</td><td>"+ $(this).find("introduce").text() + "</td></tr><tr>"
									+"<td>생성자 : </td><td>"+ $(this).find("creator").find("id").text()
									+ "</td></tr></table></div>";
						});
						$("#result").append(contentStr);
					}
				}
			});
		});
	});
	</script>
</body>
</html>
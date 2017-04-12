<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>작품 검색 - 소설 공동작업</title>

<%@ include file="header.jspf"%>



	<form method="post">
		<select name="type" id="type">
			<option value="id">작가 아이디</option>
			<option value="name">작품 이름</option>
		</select>
		<input type="text" name="keyword">
		<input type="button" name="search" id="btn" value="검색">
	</form>
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


	<script>
	
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
					alert(listLength);
					$("#result").empty();			
					if (listLength) {
						var contentStr = "";
						$(xmlData).each(function() {
							contentStr += "<div class='literatureBox'><table border='1'><tr><td>이름</td><td><a href='${ctx}/litStorage/profile.do?id="+ $(this).find("id").text() + "'>"+ $(this).find("name:first").text()
							+ "</a></td></tr><tr><td>작가</td><td>"+ $(this).find("creator").find("id").text() + "</td></tr><tr>"
							+"<td>소개</td><td>"+ $(this).find("introduce").text()
							+"</td></tr><tr><td>장르</td><td>"+$(this).find("genre").text()
							+ "</td></tr><tr><td>조회수</td><td>"+$(this).find("hits").text()+"</td></tr></table></div>";
						});
						$("#result").append(contentStr);
						alert(contentStr);
					}
				}
			});
		});
	});
	</script>
	
	

	
<%@ include file="footer.jspf"%>
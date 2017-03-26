<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>작품 검색 - 소설 공동작업</title>

<%@ include file="header.jspf"%>



	<!-- 장르 선택 버튼들 -->
	<h3>장르</h3>
	<c:forEach items="${genreList }" var="genre">
		<button onclick="getList('${genre.title}')" value="${genre.title }" class="btn btn-success">${genre.title }</button>
	</c:forEach>
	<!-- 작품 목록. 처음엔 게시판 첫번째 이름 장르의 작품 목록이 보이고 검색하면 검색결과 보임-->
	<div id="result">
	<c:forEach items="${literatures }" var="literature">
	<div class="literatureBox">
		<table border="1">
		<tr>
			<td><img src="${literature.imagePath }"></td>
			</tr>
			<tr>
						<td>작품명</td>
						<td><a
							href="${ctx}/episode/list.do?literatureId=${literature.id}">${literature.name }</a></td>
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
						contentStr += "<div class='literatureBox'><table border='1'><tr><img src='"+$(this).find("imagePath").text() + "'></tr>"
						+"<tr><td>작품명</td><td><a href='${ctx}/episode/list.do?literatureId="+ $(this).find("id").text() + "'>"+ $(this).find("name:first").text()
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

	/* $(document).ready(function() {

	 ajax로 작품 다른거 검색하기
						
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
							contentStr += "<div class='literatureBox'><table border='1'><tr><img src='"+$(this).find("imagePath").text() + "'></tr>"
							+"<tr><td>작품명</td><td><a href='${ctx}/episode/list.do?literatureId="+ $(this).find("id").text() + "'>"+ $(this).find("name:first").text()
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
	
		
	}); */
	</script>


<%@ include file="footer.jspf"%>
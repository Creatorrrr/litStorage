<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>작품 검색 - 소설 공동작업</title>

<%@ include file="header.jspf"%>


${box1}
		<!-- 장르 선택 버튼들 -->
		<h3>장르</h3>
		<c:forEach items="${genreList }" var="genre">
			<button onclick="getList('${genre.title}')" value="${genre.title }" class="list-group-item">${genre.title }</button>
		</c:forEach>
${box2}
		<!-- 작품 목록. 처음엔 게시판 첫번째 이름 장르의 작품 목록이 보이고 검색하면 검색결과 보임-->
		<div id="result">
			<c:set var="box_list" value="${ literatures}" />
			<%@ include file="_literatureBox.jsp" %>
		</div>
${box3}

<script>
/* 장르선택 스크립트 */
function getList(title) {
	$.ajax({
		url : "${ctx}/genreList.do",
		data : {type : "newGenre",
				genre : title,
				from : "allList"},
		type : "get",
		dataType : "xml",
		success : function(xml) {
			secessFun(xml,"#result");
		}
	});
};
</script>


<%@ include file="footer.jspf"%>
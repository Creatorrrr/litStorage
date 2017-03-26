<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>자유게시판 - 소설 공동작업</title>

<%@ include file="header.jspf"%>







<div id="result">

	<!-- 장르별 추천작, 장르를 select에서 선택하면 ajax로 controller 들러서 리스트 다시 뿌려줌 -->
	<div  class="navbar-form">
		<select id="recoGenre" class="form-control navbar-right">
			<c:forEach items="${genreList }" var="genre">
				<option value="${genre.title }">${genre.title }</option>
			</c:forEach>
		</select>
		<h2>장르별 추천작</h2>
	</div>
	<!-- 추천작 결과 목록 뿌려주기 -->
	<div id="recoResult">
		<c:set var="box_list" value="${ recoLiteratures}" />
		<%@ include file="_literatureBox.jsp" %>
	</div>

	<!-- 장르별 신작, 추천작이랑 똑같이 동작함 -->
	<div  class="navbar-form">
		<select id="newGenre"  class="form-control navbar-right">
			<c:forEach items="${genreList }" var="genre">
				<option value="${genre.title }">${genre.title }</option>
			</c:forEach>
		</select>
		<h2>장르별 신작</h2>
	</div>
	<!-- 신작 결과 목록 뿌려주기 -->
	<div id="newResult">
		<c:set var="box_list" value="${ newLiteratures}" />
		<%@ include file="_literatureBox.jsp" %>
	</div>
	
</div>
	
	<script>
	/* 장르 선택할때마다 ajax로 갔다와서 장르별로 새로 뿌리기 */

			$('#recoGenre').change(function() {
				$.ajax({
					url : "${ctx}/genreList.do",
					data : {type : "recoGenre",
							genre : $("#recoGenre option:selected").val(),
							from : "main"},
					type : "get",
					dataType : "xml",
					success : function(xml) {
						secessFun(xml,"#recoResult");
					}
				});
			});
			$('#newGenre').change(function() {
				$.ajax({
					url : "${ctx}/genreList.do",
					data : {type : "newGenre",
							genre : $("#newGenre option:selected").val(),
							from : "main"},
					type : "get",
					dataType : "xml",
					success : function(xml) {
						secessFun(xml,"#newResult");
					}
				});
			});
			
	</script>
	
	
	
<%@ include file="footer.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>작품 저장소 검색 - 소설 공동작업</title>

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

<%@ include file="footer.jspf"%>
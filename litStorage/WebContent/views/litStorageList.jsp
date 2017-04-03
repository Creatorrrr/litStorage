<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>작품 저장소 - 소설 공동작업</title>

<%@ include file="header.jspf"%>


${box1 }	
	<%@ include file="_litStorageNav.jsp" %>

		
		
${box2 }

		<h3>작품 저장소 전체 목록</h3>
		<c:choose>
			<c:when test="${litStorages eq null || empty litStorages }">
				<table border="1">
					<tr>
						<th colspan="7" class="text-center">등록된 작품저장소의 정보가 존재하지 않습니다.</th>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<div id="result">
					<c:forEach items="${litStorages }" var="litStorage">
						<div class="litStorageBox" style="display:inline-block;padding:5px">
							<table border="1" style="width:240px">
								<tr>
									<td>이름</td>
									<td><a href="${ctx}/litStorage/profile.do?id=${litStorage.id}">${litStorage.name }</a></td>
								</tr>
								<tr>
									<td>소개</td>
									<td>${litStorage.introduce }</td>
								</tr>
								<tr>
									<td>생성자</td>
									<td>${litStorage.creator.id }</td>
								</tr>
								<tr>
									<td>Email : </td>
									<td>${litStorage.creator.email }</td>
								</tr>
							</table>
						</div>
					</c:forEach>
				</div>
				<ul class="pagination pagination-sm">
					<fmt:parseNumber var="pages" integerOnly="true" value="${rows / 50 }"/>
					<c:forEach var="i" begin="1" end="${pages + 1 }" step="1">
						<li><a href="${ctx }/litStorage/allList.do?pageNum=${i}">${i}</a></li>
					</c:forEach>
				</ul>
			</c:otherwise>
		</c:choose>
		<!-- 검색 ajax script 영역 -->
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

${box3 }

<%@ include file="footer.jspf"%>
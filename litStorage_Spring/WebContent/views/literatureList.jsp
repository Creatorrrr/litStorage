<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>작품 목록 - 소설 공동작업</title>

<%@ include file="header.jspf"%>


${box1 }
	<c:choose>
		<c:when test="${litStorage.creator.id eq loginId}">
			<c:set var="isMaster" value="true" />
		</c:when>
		<c:otherwise>
			<c:set var="isMaster" value="false" />
		</c:otherwise>
	</c:choose>


	
	
	<jsp:include page="litStorageSideNav.jsp">
		 <jsp:param name="litStorage" value="${litStorage.id }"/>
		 <jsp:param name="isMaster" value="${isMaster }"/>
		 <jsp:param name="onGroup" value="${onGroup }"/>
	</jsp:include>


${box2 }

	
	<div>
		<c:if test="${onGroup || sessionScope.isAdmin }">
			<div style="float:right">
				<a href="${ctx }/literature/register.do?litStorageId=${litStorage.id}" class="btn btn-sm btn-success">작품 등록</a>
			</div>
		</c:if>
		<h1>${litStorage.name } 저장소의 작품 목록</h1>
	</div>
	
	<table class="table table-hover table-condensed" style="border:1px solid #ccc;margin:4px;">
		<colgroup>
			<col width="50">
			<col width="100">
			<col width="500">
			<col width="100">
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>장르</th>
				<th>제목</th>
				<th>작성자</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${litStorage.literatures }" var="literature" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>${literature.genre }</td>
					<td>
						<a href="${ctx}/episode/list.do?literatureId=${literature.id}">${literature.name }</a>
					</td>
					<td>${literature.creator.name }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script type="text/javascript">
		function deleteEpisodeFunction() {
			var deleteEpisode = confirm("삭제하시겠습니까");
	
			if (deleteEpisode == true) {
				document.getElementId("deleteEpisode").click();
			}
	
		}
	</script>
${box3 }


<%@ include file="footer.jspf"%>
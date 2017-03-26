<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>작품 목록 - 소설 공동작업</title>

<%@ include file="header.jspf"%>



<c:choose>
 <c:when test="${litStorage.creator.id eq loginId}">
 <c:set var="isMaster" value="true" />
 </c:when>
 <c:otherwise>
 <c:set var="isMaster" value="false" />
 </c:otherwise>
 </c:choose>



	<div class="container">

		<div class="col-xs-12 col-md-12">
			<div class="col-xs-6 col-md-4">
				<div>
				<h1>${litStorage.name }</h1>
				<jsp:include page="litStorageSideNav.jsp">
					 <jsp:param name="litStorage" value="${litStorage.id }"/>
					 <jsp:param name="isMaster" value="${isMaster }"/>
				</jsp:include>
				</div>
			</div>
			<div class="col-xs-12 col-md-8">
				<h1>작품 목록</h1>
				<c:if test="${onGroup || sessionScope.isAdmin }">
					<div class="text-right">
						<a href="${ctx }/literature/register.do?litStorageId=${litStorage.id}">작품 등록</a>
					</div>
				</c:if>
				<div class="panel panel-default">
					<div>
						<table border="1">
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
					</div>
				</div>
			</div>
		</div>
	</div>


<script type="text/javascript">
	function deleteEpisodeFunction() {
		var deleteEpisode = confirm("삭제하시겠습니까");

		if (deleteEpisode == true) {
			document.getElementId("deleteEpisode").click();
		}

	}
</script>



<%@ include file="footer.jspf"%>
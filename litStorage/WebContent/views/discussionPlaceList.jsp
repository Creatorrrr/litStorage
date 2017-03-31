<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>토론장 목록 - 소설 공동작업</title>

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
	<jsp:param name="litStorage" value="${litStorageId }"/>
	<jsp:param name="isMaster" value="${isMaster }"/>
	<jsp:param name="onGroup" value="${onGroup }"/>
</jsp:include>


${box2 }





		<!-- Button trigger modal -->
		<button type="button" style="float:right;" class="btn btn-sm btn-success" data-toggle="modal" data-target="#myModal">토론장 생성</button>

		<!-- Modal -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
				
					<form id="registerForm" action="${ctx }/discussionPlace/register.do" method="post">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel" style="text-align:center;">토론장 생성</h4>
					</div>
					<div class="modal-body">
						<div id="registerBox">
							<h3 style="display:inline-block;">제목:</h3>
							<input type="text" id="registerTitle" name="title" class="form-control" style="display:inline-block; width:80%;" >
							<div id="errorMessage"></div>
						</div>
					</div>
					<div class="modal-footer">
						<input type="hidden" name="litStorageId" value="${litStorage.id }" class="form-control">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="button" id="registerBtn" class="btn btn-primary">토론장 생성</button>
					</div>
					</form>

					<script type="text/javascript">
						
							$("#registerBtn").click(function() {
								if($.trim($("#registerTitle").val()) != ""){
									$("#registerForm").submit();
								}else{
									//[토론장 제목을 입력하지 않은 경우] 5. 제목 미입력 메시지를 표시한다.
									$("#errorMessage").html("제목을 입력하여 주세요.").show().hide(5000);
								}
								
							});

					</script>

				</div>
			</div>
		</div>

	
	<h2>토론장</h2>
	
	
	<table class="table table-striped">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>생성 회원ID</td>
	
		</tr>
		<c:forEach items="${ discussionPlaces}" var="discussionPlace"
			varStatus="status">
			<tr>
				<td>${ status.count }</td>
				<td><a
					href="${ctx }/discussionPlace/detail.do?id=${discussionPlace.id }&litStorageId=${litStorage.id}">${discussionPlace.title }</a></td>
				<td>${ discussionPlace.creator.id}(${discussionPlace.creator.name })</td>
		
			</tr>
		</c:forEach>
	</table>
	
	
	<!-- 검색바 시작-->
	<form action="${ctx }/discussionPlace/search.do" method="get">

		<select name="searchType" class="form-control" style="width:100px; display:inline-block;">
			<option value="title">제목</option>
			<option value="userId">회원ID</option>
		</select>
		
		<input type="text" name="searchText" class="form-control" style="display:inline-block;width:70%;">
		<button id="searchBtn" class="btn btn-primary" style="display:inline-block;width:80px;">검색</button>

	</form>
	<!-- 검색바 끝 -->

${box3 }
	
<%@ include file="footer.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>토론장 목록</title>


<link href="${ctx }/resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery-3.2.0.min.js"></script>

<!--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>-->
<script type="text/javascript">
	$(function () {
		
		//$('button,a').addClass('btn btn-success');
		//$('input').addClass('form-control');
		
	});


</script>


</head>
<body>


<h1>토론장</h1>
<div class="panel panel-default">
	<div class="col-md-8">
		<form action="${ctx }/discussionPlace/search.do" method="get">
			<select name="searchType">
				<option value="title">제목</option>
			 	<option value="userId">회원ID</option>
			</select>
			<input type="text" name="searchText" ><button id="searchBtn">검색</button>
		</form>
	</div>
	
	<div class="text-right">
		
		<a href="javascript:$('#registerBox').css('display','block');">토론장 생성</a>
		<div id="registerBox" style="display:none;position: absolute; top: 100px;left:100px;  background: #ccc;">
			<span><a href="javascript:$('#registerBox').css('display','none');">x</a></span>
			
			<form id="registerForm" action="${ctx }/discussionPlace/register.do" method="post">
				제목:<input type="text" name="title">
				
				
				<input type="hidden" name="litStorageId" value="333" class="form-control">
				<button type="button" id="registerBtn" class="btn btn-success">토론장 생성</button>
			</form>
		</div>
			<script type="text/javascript">
				//[토론장 제목을 입력하지 않은 경우] 5. 제목 미입력 메시지를 표시한다.
				//회원은 작품 저장소에 소속되어 있어야함
				$(function() {
					$("#registerBtn").click(function(){
						$("#registerForm").submit();
					});
				});
			
			
			</script>
		
		
	</div>
	<table class="table table-striped">
		<tr><td>번호</td><td>제목</td><td>생성 회원ID</td></tr>
		<c:forEach items="${ discussionPlaces}" var="discussionPlace" varStatus="status">
			<tr>
			<td>${ status.count }</td>
			<td><a href="${ctx }/discussionPlace/detail.do?id=${discussionPlace.id }">${discussionPlace.title }</a></td>
			<td>${ discussionPlace.creator.id}(${discussionPlace.creator.name })</td></tr>
		</c:forEach>
	</table>
</div>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>토론장 목록</title>
<link href="${ctx }/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/resources/css/bootstrap-theme.min.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/resources/js/bootstrap.min.js"></script>
<style type="text/css">
div{border: 1px solid #ccc; }

</style>
</head>
<body>

<div class="container">

<div class="row">
 <div class="col-xs-12 col-md-12">
 	
 	<div class="row">
 		<div class="col-md-6">
 			<h1>제목</h1>
 		</div>
	 	<div class="text-right">
		 	<button>로그아웃</button>
		 	<button>회원 정보 수정</button>
	 	</div>
 	</div>
 	<div class="row">
 		<div class="col-md-6">
		 	<a href="javascript:;" >자유게시판</a>
		 	<a href="javascript:;" >직품 저장소</a>
		 	<a href="javascript:;" >작품 목록</a>
	 	</div>
	 	<div class="text-right">
		 	<select >
			  <option>작가 아이디</option>
			  <option>작가명</option>
			  <option>회원 이름</option>
			</select>
			<input type="text" name="search" ><button>검색</button>
			<button>검색</button>
		</div>
 	</div>

 </div>
</div>
<div class="col-xs-12 col-md-12">
	
	<div class="col-xs-6 col-md-4">
		<h1>홍길동전 -작품 ..</h1>
		<div>
			<pl>
				<li><a href="javascript:;">프로필</a></li>
				<li><a href="javascript:;">작품 목록</a></li>
				<li><a href="javascript:;">토론장</a></li>
				<li><a href="javascript:;">참가 회원 목록</a></li>
				<li><a href="javascript:;">회원 초대</a></li>
			
			</pl>
		</div>

	</div>
	<div class="col-xs-12 col-md-8">
		<h1>토론장</h1>
		<div class="panel panel-default">
			<div class="col-md-8">
				<select >
				  <option>이름</option>
				  <option>제목</option>
				</select>
				<input type="text" name="search_discussionPlace" ><button>검색</button>
			</div>
			<div class="text-right">
				<button>토론장 생성</button>
			</div>
			<table class="table table-striped">
				<tr><td>번호</td><td>제목</td><td>생성 회원</td></tr>
				<tr><td>1</td><td>홍길동전 업데이트 날짜 회의</td><td>hong</td></tr>
				<tr><td>2</td><td>저 이제 탈퇴 할까 합니다.</td><td>11111</td></tr>
			</table>
		</div>
	</div>
</div>


</div>

</body>
</html>
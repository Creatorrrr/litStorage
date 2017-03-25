<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>플랫폼 메인 페이지</title>
</head>
<body>
<%@ include file="header.jspf"%>
<h1>이 밑에서 버튼 만들어서 기능구현 테스트 해주세요~</h1>

<a href="${ctx }/episode/list.do">연재글 목록</a><br>
<a href="${ctx }/views/episodeRegister.jsp">연재글 등록</a><br>
<a href="${ctx }/episode/detail.do">연재글 상세</a><br>

<%-- <a href="${ctx }/episode/modify.do">연재글 수정</a><br> --%>

<a href="${ctx }/litStorage/register.do">작품 저장소 등록</a><br>
<a href="${ctx }/litStorage/myList.do">내 작품 저장소!</a><br>

<a href="${ctx }/litStorage/memberList.do?id=4">작품 저장소 참가 회원</a><br>
<a href="${ctx }/post/postList.do">자유 게시판</a><br>
<a href="${ctx }/litStorage/allList.do">작품 저장소 목록</a><br>
<a href="${ctx }/views/discussionPlaceList.jsp">토론장 목록</a><br>

<br><br>
<!-- if you want to move member invite page, make sure that page must contain LitStorage -->
<!-- this link is test for litStorage which have id 4 , if you want to test another litStorage,
		just change the get parameter(litStorageId)-->
<a href="${ctx }/member/search.do?litStorageId=4">작품 저장소 초대 회원 검색</a><br>
<a href="${ctx }/litStorage/allList.do">작품 저장소 목록</a><br>
<a href="${ctx }/litStorage/register.do">작품 저장소 등록</a><br>
<a href="${ctx }/litStorage/myList.do">내 작품 저장소!</a><br><hr>

<a href="${ctx }/member/inviteList.do">누가 날 초대했지?</a><br>
<a href="${ctx }/member/requestList.do">누가 내 저장소에 참가를 요청했지?</a><br><hr>

<a href="${ctx }/litStorage/search.do">작품 저장소 검색</a><br>
<a href="${ctx }/literature/search.do">작품 검색</a><br>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="row">

	<div class="col-xs-8 col-md-3">
		<h1>작품이름</h1>
		<div class="list-group table-of-contents">
			<a href="${ctx }/views/litStorageProfile.jsp"  class="list-group-item">프로필</a>
			<a href="${ctx }/views/litStorageList.jsp"  class="list-group-item">작품 목록</a>
			<a href="${ctx }/views/discussionPlaceList.jsp"  class="list-group-item">토론장</a>
			<a href="${ctx }/litStorage/memberList.do?id="  class="list-group-item">참가 회원 목록</a>
			<a href="${ctx }/member/search.do?litStorageId=${litStorageId }"  class="list-group-item">회원초대</a>
		</div>
	</div>
	<div class="col-xs-12 col-md-9">
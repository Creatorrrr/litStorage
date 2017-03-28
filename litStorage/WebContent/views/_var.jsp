<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"  />
<c:set var="a_episode_list" value='<a href="${ctx }/episode/list.do">연재글 목록x</a>' />
<c:set var="a_episode_register" value='<a href="${ctx }/views/episodeRegister.jsp">연재글 등록</a>' />





<c:set var="box1" value='
<div class="row">
	<!-- 좌측 영역 시작 -->
	<div class="col-lg-3 col-md-3 col-sm-4">
		<div class="list-group table-of-contents">
'/>

<c:set var="box2" value='
		</div>
	</div>
	<!-- 좌측 영역 끝 -->
	<!-- 우측 영역 시작 -->
	<div class="col-lg-9 col-md-9 col-sm-8">
'/>


<c:set var="box3" value='
	</div>
	<!-- 우측 영역 끝 -->
</div>
'/>

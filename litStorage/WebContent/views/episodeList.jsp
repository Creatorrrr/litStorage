<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>연재글 목록</title>
<link href="${ctx }/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/resources/css/bootstrap-theme.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctx }/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function deleteLitFunction() {
		var deleteLit = confirm("삭제하시겠습니까?");

	}
</script>

<style type="text/css">
div {
	border: 1px solid #ccc;
}
</style>
</head>
<body>
<%@include file="./header.jspf" %>   
	<div class="container">

		<div class="row">
			<div class="col-xs-12 col-md-12">

				<div class="row">
					<div class="col-md-6">
						<h1>제목</h1>
					</div>
					<%-- <div class="text-right">                      header.jspf 붙임  
						<a href="${ctx }/logout.do">로그아웃</a> <a
							href="${ctx}/member/detail.do">회원정보</a> <a
							href="${ctx}/member/inviteList.do">초대 온 목록</a>
					</div> --%>
				</div>
				<div class="row">
					<%-- <div class="col-md-6">                    header.jspf 붙임  
						<a href="javascript:;">자유게시판</a> <a
							href="${ctx }/litStorage/allList.do;">직품 저장소</a> <a
							href="javascript:;">작품 목록</a>
					</div> --%>
					<form action="../episode/list.do" method="post">
						<div class="text-right">
							<select name="selectContents">
								<option value="writerId">작가 아이디</option>
								<option value="writerName">작가명</option>
								<option value="memberName">회원 이름</option>
							</select> <input type="text" name="search" placeholder="검색 내용을 입력해주세요.">
							<button type="submit">검색</button>
						</div>
					</form>
				</div>

			</div>
		</div>
		<div class="col-xs-12 col-md-12">

			<div class="col-xs-6 col-md-4">
				<h1>작품이름</h1>
				<div>

					<a href="javascript:;">프로필</a> 
					<a href="${ctx }/views/literatureList.jsp">작품 목록</a> 
					<a href="${ctx }/views/discussionPlaceList.jsp">토론장</a>
				 	<a href="javascript:;">참가 회원 목록</a> 
				 	<a href="${ctx }/member/search.do?litStorageId">회원 초대</a>

				</div>

			</div>
			<form action="../episode/list.do" method="post">
				<c:forEach items="${literatures }" var="literature"></c:forEach>
				<div class="col-xs-12 col-md-8">
					<h1>연재 글 목록</h1>
					<div class="text-right">
						<!-- LiteratureDeleteController -->
						<a
							href="${ctx}/literature/delete.do?deleteLiteratureId=${literature.id}">작품
							삭제</a>
					</div>
					<div class="panel panel-default">
						<div class="row">

							<form method="post" enctype="multipart/form-data"
								action="imgup.jsp">
								<input type="file" name="filename1" size=40> <input
									type="submit" value="업로드"><br> <br>

								<div class="col-md-3">

									<h4>${literature.genre }</h4>
									<h4>${literature.name }</h4>
									<h4>${literature.introduce }</h4>
								</div>
								<div class="text-middle">
									<h4>${literature.creator.name }</h4>
									<h4>${literature.creator.id }</h4>
									<h4>${literature.creator.email }</h4>
								</div>
						</div>
						<div class="text-right">
							<button type="button"
								onclick="location.href='../episode/register.do?literatureId=${literature.id}' ">연재글
								등록</button>
						</div>
						<table class="table table-striped">
							<tr>
								<td>제목</td>
								<td>작성자</td>
								<td>공개 범위</td>
							</tr>

							<c:forEach items="${literature.episodes }" var="episode">
								<c:if test="${episode ne null }">
									<tr>
										<td><a
											href="${ctx }/episode/detail.do?episodeId=${episode.id }">${episode.title }</a></td>
										<td>${episode.writer.name }</td>

										<td><select name="openSelect">
												<option value="M">저장소 협업 작가만 공개</option>
												<option value="A">모두 공개</option>
										</select></td>

									</tr>
								</c:if>
							</c:forEach>


						</table>
					</div>
					<div class="text-right">
						<button type="button"
							onclick="location.href='${ctx }/episode/register.do?literatureId=${literature.id}' ">연재글
							등록</button>
					</div>
					<table class="table table-striped">
						<tr>
							<td>제목</td>
							<td>작성자</td>
							<td>공개 범위</td>
						</tr>
						<c:forEach items="${episodes }" var="episode">
							<c:if test="${episode ne null }">
								<tr>
									<td><a
										href="${ctx }/episode/detail.do?episodeId=${episode.id }">${episode.title }</a></td>
									<td>${episode.writer.name }</td>
									<td><select name="openSelect">
											<option value="M">저장소 협업 작가만 공개</option>
											<option value="A">모두 공개</option>
									</select></td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
		</div>
		</form>
	</div>


	</div>

</body>
</html>
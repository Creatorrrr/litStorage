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
<style type="text/css">
div {
	border: 1px solid #ccc;
}
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
						<a href="javascript:;">자유게시판</a> <a href="javascript:;">직품 저장소</a>
						<a href="javascript:;">작품 목록</a>
					</div>
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
					<pl>
					<li><a href="javascript:;">프로필</a></li>
					<li><a href="javascript:;">작품 목록</a></li>
					<li><a href="javascript:;">토론장</a></li>
					<li><a href="javascript:;">참가 회원 목록</a></li>
					<li><a href="javascript:;">회원 초대</a></li>

					</pl>
				</div>

			</div>
			<form action="../episode/list.do" method="post">
			<c:forEach items="${literatures }" var="literature">
				<div class="col-xs-12 col-md-8">
					<h1>연재 글 목록</h1>
					<div class="panel panel-default">
						<div class="row">
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
							<button>연재글 등록</button>
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
										<td>${episode.title }</td>
										<td>${episode.writer.name }</td>
										
										<td><select>
												<option>저장소 공개</option>
												<option>모두 공개</option>
										</select></td>
									</tr>
								</c:if>
							</c:forEach>


						</table>
					</div>
				</div>
			</c:forEach>
			</form>
		</div>


	</div>

</body>
</html>
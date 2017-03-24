<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>작품 목록</title>
<link href="${ctx }/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/resources/css/bootstrap-theme.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${ctx }/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function deleteEpisodeFunction() {
		var deleteEpisode = confirm("삭제하시겠습니까");

		if (deleteEpisode == true) {
			document.getElementId("deleteEpisode").click();
		}

	}
</script>

<style type="text/css">
div {
	border: 1px solid #ccc;
}
</style>
</head>

<body>
	<!-- <input type="button" id="deleteEpisode"
		onclick="location.href='../episode/list' "> -->
	<form action="../episode/deteil.do" method="post">
		<input type="hidden" id="deleteEpisode" value="F">
	</form>
	<div class="container">

		<div class="row">
			<div class="col-xs-12 col-md-12">

				<div class="row">
					<div class="col-md-6">
						<h1>제목</h1>
					</div>
					<div class="text-right">
						<button>로그인</button>
						<button>회원 정보 수정</button>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<a href="javascript:;">자유게시판</a> <a
							href="${ctx }/litStorage/allList.do;">작품 저장소</a> <a
							href="javascript:;">작품 목록</a>
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
				<h1>${literature.name }</h1>
				<div>

					<a href="javascript:;">프로필</a> <a href="javascript:;">작품 목록</a> <a
						href="javascript:;">토론장</a> <a href="javascript:;">참가 회원 목록</a> <a
						href="javascript:;">회원 초대</a>


				</div>

			</div>
			<div class="col-xs-12 col-md-8">
				<h1>작품 목록</h1>
				<div class="text-right">
					<a
						href="${ctx }/literature/register.do?litStorageId=${literature.litStorage.id}">작품
						등록</a>
				</div>
				<div class="panel panel-default">
					<div>
						<a>장르</a> <a>:제목:</a> <a>작성자</a>
					</div>
					<c:forEach items="${literatures }" var="literature">
						<div>
							<a>${literature.genre }</a><a
								href="${ctx}/episode/list.do?LiteratureId=${literature.id}">${literature.name }</a><a>${literature.creator.name }</a>
						</div>
					</c:forEach>
				</div>
				<div class="row"></div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
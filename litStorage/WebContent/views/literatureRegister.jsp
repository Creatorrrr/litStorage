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
						<a href="javascript:;">자유게시판</a> <a
							href="${ctx }/litStorage/allList.do;">직품 저장소</a> <a
							href="javascript:;">작품 목록</a>
					</div>
					<form action="${ctx }/episode/list.do" method="post">
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

					<a href="javascript:;">프로필</a> <a href="javascript:;">작품 목록</a> <a
						href="javascript:;">토론장</a> <a href="javascript:;">참가 회원 목록</a> <a
						href="javascript:;">회원 초대</a>


				</div>

			</div>
			<div>
			<img  src="${imagePath }" style="width:304px;height:228px;">
			<form action="../literature/registerImg.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="litStorageId" value="${litStorageId }">
				<h4>이미지 업로드</h4>
				제목:
				<input type="text" name="title" placeholder="제목을 입력하세요"><br> 
				파일1:
				<input type="file" name="filename1"><br>
				
				<input type="submit" value="업로드">
				</form>

			</div>
			<form action="${ctx }/literature/register.do" method="post"
				enctype="multipart/form-data">
				<input type="hidden" name="litStorageId" value="${litStorageId }">
				<input type="hidden" name="imagePath" value="${imagePath }">
				<div class="col-xs-12 col-md-8">
					<h1>${litStorageId }</h1>
					<div class="text-right">
						<!-- LiteratureDeleteController -->
						<a
							href="${ctx}/literature/delete.do?deleteLiteratureId=${literature.id}">작품
							삭제</a>
					</div>
					<div class="panel panel-default">
						<div class="row">
							<div class="col-md-3">
								<div>
									<h4>장르</h4>
									<select name="selectGenre">
										<option value="martial">무협</option>
										<option value="romance">로맨스</option>
										<option value="action">액션</option>
									</select>
								</div>
								<div>
									<h4>이름</h4>
									<textarea rows="10" cols="1" name="inputName"></textarea>
								</div>
								<div>
									<h4>내용</h4>
									<textarea rows="10" cols="1" name="inputIntroduce"></textarea>
								</div>
							</div>

						</div>
						<div class="text-right">
							<button type="submit">등록</button>
						</div>

					</div>
				</div>

			</form>
		</div>


	</div>

</body>
</html>
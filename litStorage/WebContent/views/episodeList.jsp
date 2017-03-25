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
	<%@ include file="header.jspf"%>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-md-12">
				<div class="row">
					<div class="col-md-6">
						<h1>제목</h1>
					</div>
					<!-- <div class="text-right">
						<button>로그아웃</button>
						<button>회원 정보 수정</button>
					</div> -->
				</div>
				<div class="row">
					<div class="col-md-6">
						<%-- <a href="javascript:;">자유게시판</a> <a
							href="${ctx }/litStorage/allList.do;">직품 저장소</a> <a
							href="javascript:;">작품 목록</a>
					</div> --%>
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
						<a href="${ctx }/views/litStorageProfile.jsp">프로필</a> <a
							href="${ctx }/views/litStorageList.jsp">작품 목록</a> <a
							href="${ctx }/views/discussionPlaceList.jsp">토론장</a> <a
							href="${ctx }/litStorage/memberList.do?id=">참가 회원 목록</a> <a
							href="${ctx }/member/search.do?litStorageId=${litStorageId }">회원
							초대</a>
					</div>
				</div>
				<div class="col-xs-12 col-md-8">
					<h1>연재 글 목록</h1>
					<div class="text-right">
						<!-- LiteratureDeleteController -->
						<a
							href="${ctx}/literature/delete.do?literatureId=${literature.id}">작품삭제</a>
					</div>
					<div class="panel panel-default">
						<div class="row">
							<div>
								<img src="${ctx }/literature/image.do?literatureId=${literature.id }"
									width="128" height="128">
							</div>
							<table class="col-md-3">
								<tr>
									<th>장르</th>
									<td>${literature.genre }</td>
								</tr>
								<tr>
									<th>제목</th>
									<td>${literature.name }</td>
								</tr>
								<tr>
									<th>내용</th>
									<td>${literature.name }</td>
								</tr>
							</table>
							<table class="text-middle">
								<tr>
									<th>이름</th>
									<td>${literature.creator.name }</td>
								</tr>
								<tr>
									<th>아이디</th>
									<td>${literature.creator.id }</td>
								</tr>
								<tr>
									<th>이메일</th>
									<td>${literature.creator.email }</td>
								</tr>
							</table>
						</div>
						<div class="text-right">
							<button type="button"
								onclick="location.href='${ctx }/episode/register.do?literatureId=${literature.id}'">연재글
								등록</button>
						</div>
						<table class="table table-striped">
							<colgroup>
								<col width="50">
								<col width="500">
								<col width="100">
								<col width="100">
							</colgroup>
							<thead>
								<tr>
									<td>번호</td>
									<td>제목</td>
									<td>작성자</td>
									<td>공개 범위</td>
									<td></td>
								</tr>
							</thead>
							<tbody>
								<c:choose>
									<c:when test="${literature.episodes eq null}">
										<tr>
											<td colspan="7" class="text-center">등록된 연재글의 정보가 존재하지 않습니다.</td>
										</tr>
									</c:when>
									<c:otherwise>
										<c:forEach items="${literature.episodes}" var="episode" varStatus="status">
											<tr>
												<td>${status.count }</td>
												<td>
													<a href="${ctx }/episode/detail.do?episodeId=${episode.id }">${episode.title }</a>
												</td>
												<td>${episode.writer.id }</td>
												<td>
													<form action="${ctx }/episode/bound.do" method="post">
														<input type="hidden" name="episodeId" value="${episode.id }">
														<select name="bound">
															<option value="M">저장소 협업 작가만 공개</option>
															<option value="A">모두 공개</option>
														</select>
														<button type="submit">공개 선택</button>
													</form>
												</td>
											</tr>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
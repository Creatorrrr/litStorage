<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>연재글 목록 - 소설 공동작업</title>

<%@ include file="header.jspf"%>



<style type="text/css">
div {
	border: 1px solid #ccc;
}
</style>


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
							href="${ctx }/member/search.do?litStorageId=${litStorageId }">회원초대</a>
					</div>
				</div>
				<div class="col-xs-12 col-md-8">
					<h1>연재 글 목록</h1>
					<c:if test="${litearture.litStorage.creator.id eq sessionScope.loginId || literature.creator.id eq sessionScope.loginId || sessionScope.isAdmin }">
						<div class="text-right">
							<!-- LiteratureDeleteController -->
							<a href="${ctx}/literature/delete.do?literatureId=${literature.id}">작품삭제</a>
						</div>
					</c:if>
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
						<c:if test="${onGroup || sessionScope.isAdmin}">
							<div class="text-right">
								<button type="button"
									onclick="location.href='${ctx }/episode/register.do?literatureId=${literature.id}'">연재글
									등록</button>
							</div>
						</c:if>
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
											<c:if test="${episode.bound.charAt(0) eq 'A'.charAt(0) || onGroup || sessionScope.isAdmin}">
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
																<c:choose>
																	<c:when test="${episode.bound.charAt(0) eq 'M'.charAt(0) }">
																		<option value="M">저장소 협업 작가만 공개</option>
																		<option value="A">모두 공개</option>
																	</c:when>
																	<c:otherwise>
																		<option value="A">모두 공개</option>
																		<option value="M">저장소 협업 작가만 공개</option>
																	</c:otherwise>
																</c:choose>
															</select>
															<c:if test="${episode.writer.id eq sessionScope.loginId || sessionScope.isAdmin}">
																<button type="submit">공개 선택</button>
															</c:if>
														</form>
													</td>
												</tr>
											</c:if>
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
	
	
	
<script type="text/javascript">
	function deleteLitFunction() {
		var deleteLit = confirm("삭제하시겠습니까?");

	}
</script>
	

<%@ include file="footer.jspf"%>
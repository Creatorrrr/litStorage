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


<%@ include file="_episodeNav.jsp" %>

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


	
	
<script type="text/javascript">
	function deleteLitFunction() {
		var deleteLit = confirm("삭제하시겠습니까?");

	}
</script>
	

<%@ include file="footer.jspf"%>
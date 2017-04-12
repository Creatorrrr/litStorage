<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>연재글 목록 - 소설 공동작업</title>

<%@ include file="header.jspf"%>

	<c:choose>
		<c:when test="${literature.creator.id eq loginId}">
			<c:set var="isMaster" value="true" />
		</c:when>
		<c:otherwise>
			<c:set var="isMaster" value="false" />
		</c:otherwise>
	</c:choose>
	


${box1 }

	<jsp:include page="litStorageSideNav.jsp">
		 <jsp:param name="litStorage" value="${literature.litStorage.id }"/>
		 <jsp:param name="isMaster" value="${isMaster }"/>
		 <jsp:param name="onGroup" value="${onGroup }"/>
	</jsp:include>


${box2 }
<div>
	<div style="float:right;margin-top:15px;">
	
		<c:if test="${litearture.litStorage.creator.id eq sessionScope.loginId || literature.creator.id eq sessionScope.loginId || sessionScope.isAdmin }">	
			<!-- LiteratureDeleteController -->
			<a class="btn btn-sm btn-warning"  href="${ctx}/literature/delete.do?literatureId=${literature.id}">작품삭제</a>
		</c:if>
		<c:if test="${onGroup || sessionScope.isAdmin}">
			<button type="button" class="btn btn-sm btn-success"  onclick="location.href='${ctx }/episode/register.do?literatureId=${literature.id}'">연재글 등록</button>
		</c:if>
	</div>
	<h2>[${literature.litStorage.name}]의 연재 글 목록</h2>
	<h3>제목: ${literature.name }</h3>
</div>
	


			<div style="float:left;">
				<img src="${literature.imagePath }" width="165" height="239">
			</div>
			<table class="table table-hover table-condensed" style="float:right;border:1px solid #ccc;margin:4px;width:500px; height:239px;">
				<tr>
					<td><b>장르</b>: ${literature.genre }</td><td><b>이름</b>: ${literature.creator.name }</td>

				</tr>
				<tr>
					<td><b>아이디</b>: ${literature.creator.id }</td><td><b>이메일</b>: ${literature.creator.email }</td>

				</tr>
				<tr>
					<td colspan="2"><b>제목</b>: ${literature.name }</td>
				</tr>
				<tr>
					<td colspan="2"><b>내용</b>: ${literature.introduce }</td>
				</tr>



			
			</table>

		<table class="table table-striped table-hover ">
			<colgroup>
				<col width="80">
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
									<td  class="nav nav-group">
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


	<script type="text/javascript">
		function deleteLitFunction() {
			var deleteLit = confirm("삭제하시겠습니까?");
	
		}
	</script>
	
${box3 }

<%@ include file="footer.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>자유게시판 - 소설 공동작업</title>

<%@ include file="header.jspf"%>


${box1 }
	<%@ include file="_boardNav.jsp" %>
		
${box2 }

	

<div id="result" >
	
	<style>
	th {text-align: center;}
	</style>
	
	<c:if test="${boards ne null && fn:length(boards) > 0}">
		<c:forEach items="${boards }" var="board">
		<c:if test="${board.id eq boardId }">
		
			

			<div >
				<div style="float:right;">
				<c:if test="${sessionScope.loginId ne null && boardId ne null}">
					<a class="btn btn-sm btn-success" href="${ctx}/post/register.do?boardId=${boardId}" >게시물등록</a>
				</c:if>
				</div>
				<h2>${board.title } 게시판</h2>
			</div>
			
			
			<div style="text-align: center;">
				<table class="table table-hover ">
					<colgroup>
						<col width="80" align="center">
						<col width="100">
						<col width="30%">
						<col width="*">
						<col width="100">
						<col width="100">
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${posts eq null || fn:length(posts) == 0}">
								<tr>
									<td colspan="6" align="center">등록된 게시물이 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${posts }" var="post">
									<tr>
										<td>${post.id }</td>
										<td class="text-left"><a href="${ctx }/post/detail.do?postId=${post.id }">${post.title }</a></td>
										<td>${post.writer.id}</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>

					</tbody>
				</table>
				<div>
					<ul class="pagination pagination-sm">
						<fmt:parseNumber var="pages" integerOnly="true" value="${rows / 20 }"/>
						<c:forEach var="i" begin="1" end="${pages + 1 }" step="1">
							<li><a href="${ctx }/post/list.do?boardId=${boardId}&pageNum=${i}">${i}</a></li>
						</c:forEach>
					</ul>
					

				</div>
				</div>
			</c:if>
		</c:forEach>
	</c:if>
	

	<!-- 검색바 시작 -->
	<form action="${ctx }/post/searchList.do" method="post">
		<div class="navbar-form text-center" >
			<select name="select" class="form-control" style="width:135px;">
				<option value="postTitle">제목</option>
				<option value="postContent">내용</option>
				<option value="postHashtag">해시태그</option>
			</select>
			
			
			<input type="hidden" name="boardId" value="${boardId }" class="form-control">
			<input type="text" name="searchside" placeholder="검색 내용을 입력해주세요." class="form-control">
			<button type="submit"  class="btn btn-default">검색</button>
		</div>
	</form>
	<!-- 검색바 끝 -->
		
	

	
${box3 }
</div>	



	
<%@ include file="footer.jspf"%>
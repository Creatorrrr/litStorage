<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>자유게시판 - 소설 공동작업</title>

<%@ include file="header.jspf"%>
<style>
div{border: 1px solid #ccc;}
</style>
<div id="result" class="row">

<!-- 좌측 영역 시작 -->
<div class="col-lg-3 col-md-3 col-sm-4">
	<h3>자유게시판 목록</h3>
	<c:if test="${sessionScope.isAdmin }">
		<button class="btn" type="button" onclick="addBoard();">게시판추가</button>
		<div class="container" id="boardRegisterDiv">
			<form name="boardRegisterForm" action="${ctx }/board/boardRegister.do" method="post" onsubmit="return validate()">
				<input id="boardName" name="boardName" class="form-control" type="text" value="">
				<input class="btn" type="submit" value="추가">
			</form>
		</div>
	</c:if>
	<c:choose>
		<c:when test="${boards eq null || fn:length(boards) == 0}">
			<span>등록된 게시판이 없습니다.</span>
		</c:when>
		<c:otherwise>
			<c:forEach items="${boards }" var="board">
				<div>
					<a class="btn btn-sm btn-success"
						href="${ctx}/post/list.do?boardId=${board.id}">${board.title } 게시판</a>
					<c:if test="${sessionScope.isAdmin}">
						<a class="btn" href="${ctx }/boardDelete.do?boardId=${board.id }">삭제</a>
					</c:if>
				</div>
			</c:forEach>
		</c:otherwise>
	</c:choose>
	
	<c:if test="${boards ne null && fn:length(boards) > 0}">
		<c:forEach items="${boards }" var="board">
			<c:if test="${board.id eq boardId }">
			
				<div class="list-group table-of-contents">
					<form action="${ctx }/post/searchList.do" method="post">
						<input type="hidden" name="boardId" value="${boardId }" class="form-control">
						<div class="text-right">
							<select name="select" class="form-control">
								<option value="postTitle">제목</option>
								<option value="postContent">내용</option>
								<option value="postHashtag">해시태그</option>
							</select>
							<input type="text" name="search" placeholder="검색 내용을 입력해주세요." class="form-control">
							<button type="submit"  class="btn btn-default">검색</button>
						</div>
					</form>
				</div>
					
			</c:if>
		</c:forEach>
	</c:if>	
			
		</div>
		<!-- 좌측 영역 끝 -->
		
		<!-- 우측 영역 시작 -->
		<div class="col-lg-9 col-md-9 col-sm-8">
		
		
		
		<c:if test="${boards ne null && fn:length(boards) > 0}">
			<c:forEach items="${boards }" var="board">
				<c:if test="${board.id eq boardId }">
							
					
					<h1>${board.title } 게시판</h1>	
					<table class="table table-hover table-condensed">
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
									<c:forEach items="${posts }" var="post" varStatus="status">
										<tr>
											<td>${status.count }</td>
											<td><a href="${ctx }/post/detail.do?postId=${post.id }">${post.title }</a></td>
											<td>${post.writer.id}</td>
										</tr>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
		
				</c:if>
			</c:forEach>
		</c:if>
		
		</div>
		<!-- 우측 영역 끝 -->

		<c:if test="${sessionScope.loginId ne null && boardId ne null}">
			<td><a class="btn btn-sm btn-success"
				href="${ctx}/post/register.do?boardId=${boardId}">게시물등록</a></td>
		</c:if>
</div>


	<script type="text/javascript">
		var boardFlag = false;

		function addBoard() {
			if (boardFlag === false) {
				document.getElementById("boardRegisterDiv").style.display = "block";
				boardFlag = true;
			} else {
				document.getElementById("boardRegisterDiv").style.display = "none";
				boardFlag = false;
			}
		}
		
		function validate() {
		    var x = document.forms["boardRegisterForm"]["boardName"].value;
		    if (x == "") {
		        alert("생성할 게시판 이름을 입력해주세요");
		        return false;
		    }
		}
	</script>
	
	
	
<%@ include file="footer.jspf"%>
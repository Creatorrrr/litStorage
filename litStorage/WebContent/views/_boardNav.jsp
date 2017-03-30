<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

	<div class="list-group">
		<div class="list-group-item active">자유게시판 목록</div>

		<!-- 메뉴 시작 -->
		<c:choose>
			<c:when test="${boards eq null || fn:length(boards) == 0}">
			</c:when>
			<c:otherwise>
				<c:forEach items="${boards }" var="board">
					<div class="list-group-item ">
						<a href="${ctx}/post/list.do?boardId=${board.id}">${board.title } 게시판</a>
						<!-- 관리자일때 삭제 버튼이 생김 시작-->
						<c:if test="${sessionScope.isAdmin}">
							<a class="label label-danger" style="padding:2px;float:right;" href="${ctx }/boardDelete.do?boardId=${board.id }">삭제</a>
						</c:if>
						<!-- 관리자일때 삭제 버튼이 생김 끝-->
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
	<!-- 메뉴 끝 -->
	
	
	<!-- 게시판 추가 버튼 시작(관리자일때 겟판 추가 할수 있음.) -->
	<c:if test="${sessionScope.isAdmin }">
	<div>
		<button id="registerBoardBtn" style="width:100%;" class="btn btn-success" type="button" onclick="addBoard();">게시판추가</button>
	</div>
		<div class="modal" id="modal2">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button class="close" aria-hidden="true" type="button" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">게시판 추가</h4>
		      </div>
		      <div class="modal-body">
		       
		
				<div id="boardRegisterDiv" class="navbar-form" >
					<form name="boardRegisterForm" action="${ctx }/board/boardRegister.do" method="post" onsubmit="return validate()">
						<label>게시판 이름: <input id="boardName" name="boardName" class="form-control" type="text" placeholder="게시판 이름을 입력해주세요."></label>
						<button class="btn btn-primary" type="submit" type="button">추가</button>
					</form>
				</div>
		
		      </div>
		
		    </div>
		  </div>
		</div>
		<script type="text/javascript">
		$('#registerBoardBtn').click(function(){
			$('#modal2').modal('toggle');
		});
		
		
		function validate() {
		    var x = document.forms["boardRegisterForm"]["boardName"].value;
		    if (x == "") {
		        alert("생성할 게시판 이름을 입력해주세요");
		        return false;
		    }
		}
		</script>

		
		
	</c:if>
	<!-- 게시판 추가 버튼 끝 -->
	
	
	<!-- 검색바 시작 -->
	<form action="${ctx }/post/searchList.do" method="post">
		<div class="navbar-form text-center" style="background:#bbb;padding:1px;">
			<select name="select" class="form-control" style="width:135px;">
				<option value="postTitle">제목</option>
				<option value="postContent">내용</option>
				<option value="postHashtag">해시태그</option>
			</select>
			<button type="submit"  class="btn btn-default">검색</button>
			
			<input type="hidden" name="boardId" value="${boardId }" class="form-control">
			<input type="text" name="searchside" placeholder="검색 내용을 입력해주세요." class="form-control">
		</div>
	</form>
	<!-- 검색바 끝 -->
		
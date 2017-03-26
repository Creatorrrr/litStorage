<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>연재글 상세 - 소설 공동작업</title>

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
						<button>로그인</button>
						<button>회원 정보 수정</button>
					</div> -->
				</div>
				<div class="row">
					<%-- <div class="col-md-6">
						<a href="javascript:;">자유게시판</a>
						<a href="${ctx }/litStorage/allList.do;">직품 저장소</a>
						<a href="javascript:;">작품 목록</a>
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
				<h1>${episode.literature.name }</h1>
				<div>
					<a href="${ctx }/views/litStorageProfile.jsp">프로필</a> <a
						href="${ctx }/views/litStorageList.jsp">작품 목록</a> <a
						href="${ctx }/views/discussionPlaceList.jsp">토론장</a> <a
						href="${ctx }/litStorage/memberList.do?id=">참가 회원 목록</a> <a
						href="${ctx }/member/search.do?litStorageId=${litStorageId }">회원초대</a>
				</div>
			</div>
			<div class="col-xs-12 col-md-8">
				<h1>연재 글</h1>
				<div class="text-right">
					<button type="button"
						onclick="location.href='${ctx }/episode/modify.do?episodeId=${episode.id}'">수정하기</button>
					<button type="button"
						onclick="location.href='${ctx }/episode/delete.do?episodeId=${episode.id}'">삭제하기</button>
				</div>
				<div class="panel panel-default">
					<div class="row">
						<div class="col-md-3">
							<div>
								<h2>제목 : ${episode.title }</h2>
							</div>
							<div>
								<textarea rows="10" cols="75" name="episodeContents">${episode.contentFromGit }</textarea>
							</div>
						</div>
						<c:if test="${onGroup || sessionScope.isAdmin }">
							<div class="panel panel-default">
								<c:forEach items="${episode.histories }" var="history"
									varStatus="status">
									<a
										href="${ctx }/episode/changeHistoryDetail.do?historyId=${history.id}">
										<span>등록 날짜 : ${history.changeTime } </span> <span>변경 회원
											: ${history.editor.id } </span> <span>메시지 : ${history.message}
									</span>
									</a>
									<br>
								</c:forEach>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	
<script type="text/javascript">
	function deleteEpisodeFunction() {
		var deleteEpisode = confirm("삭제하시겠습니까");

		if (deleteEpisode == true) {
			document.getElementId("deleteEpisode").click();
		}

	}
</script>
	
	
		
<%@ include file="footer.jspf"%>
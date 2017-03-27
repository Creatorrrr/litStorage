<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>작품 저장소 프로필 - 소설 공동작업</title>

<%@ include file="header.jspf"%>

<style>


h2 {
	margin-left: 65px;
	text-shadow: 1px 0px 3px gray;
}

h3 {
	font-size: 18px;
	text-align: center;
}

#onclick {
	text-align: center;
}

b {
	font-size: 18px;
	text-shadow: 1px 0px 3px gray;
}

#popup {
	padding-top: 80px;
}

.form {
	border-radius: 2px;
	padding: 20px 30px;
	box-shadow: 0 0 15px;
	font-size: 14px;
	font-weight: bold;
	width: 350px;
	margin: 20px 250px 0 35px;
	float: left;
}

input {
	width: 100%;
	height: 35px;
	margin-top: 5px;
	border: 1px solid #999;
	border-radius: 3px;
	padding: 5px;
}

input[type=button], input[type=submit] {
	background-color: #123456;
	border: 1px solid white;
	font-Weight: bold;
	font-size: 18px;
	color: white;
	width: 49%;
}

textarea {
	width: 100%;
	height: 80px;
	margin-top: 5px;
	border-radius: 3px;
	padding: 5px;
	resize: none;
}

#sendRequest {
	opacity: 0.92;
	position: absolute;
	top: 0px;
	left: 0px;
	height: 100%;
	width: 100%;
	background: #000;
	display: none;
}

#send {
	width: 350px;
	margin: 0px;
	background-color: white;
	position: relative;
	border: 5px solid rgb(90, 158, 181);
}

#send {
	left: 50%;
	top: 50%;
	margin-left: -210px;
	margin-top: -158px;
}
</style>


 <c:choose>
 <c:when test="${litStorage.creator.id eq loginId}">
 <c:set var="isMaster" value="true" />
 </c:when>
 <c:otherwise>
 <c:set var="isMaster" value="false" />
 </c:otherwise>
 </c:choose>

 <jsp:include page="litStorageSideNav.jsp">
 <jsp:param name="litStorage" value="${litStorage.id }"/>
 <jsp:param name="isMaster" value="${isMaster }"/>
 <jsp:param name="onGroup" value="${onGroup }"/>
</jsp:include>
	<table class="table table-hover table-condensed">
		<colgroup>
			<col width="80" align="center">
			<col width="*">
		</colgroup>
		
		

		<tr>
			<td>작품 저장소 이름</td>
			<td>${litStorage.name }</td>
		</tr>
		<tr>
			<td>생성 회원 아이디</td>
			<td>${litStorage.creator.id }</td>
		</tr>
		<tr>
			<td>이메일 주소</td>
			<td>${litStorage.creator.email }</td>
		</tr>
		<tr>
			<td>작품 저장소 소개</td>
			<td>${litStorage.introduce }</td>
		</tr>
	</table>
	<c:if test="${isNotJoined}">
	<button id="onclick" type="button">참가 요청</button>
	</c:if>
	<c:if test="${litStorage.creator.id eq sessionScope.loginId }">
		<a href="${ctx }/litStorage/delete.do?litStorageId=${litStorage.id }">작품 저장소 삭제</a>
	</c:if>
	<!-- request popup form div start-->
	<div id="sendRequest">
		<form class="form" action="${ctx }/litStorage/request.do" id="send" method="post">
		<input type="hidden" name="receiverId" value="${litStorage.creator.id }">
		<input type="hidden" name="litStorageId" value="${litStorage.id }">
		
			<h3>요청 메시지 작성</h3>
			<hr />
			<br /> <label>보내는 사람 : ${loginId }</label> <br /> <br /> <label>요청
				메시지 :</label> <br /> <input type="text" id="message" name="message"
				placeholder="메세지를 작성하세요" /><br /> <br /> <input type="submit"
				id="submit" value="보내기" /> <input type="button" id="cancel"
				value="취소" /> <br />
		</form>

	</div>
	<!-- request popup form div end -->


	<script>
		$(document).ready(function() {
			
			$("#send #cancel").click(function() {
				$(this).parent().parent().hide();
			});
			$("#onclick").click(function() {
				$("#sendRequest").css("display", "block");
			});
			// Login form popup login-button click event.
			$("#submit").click(function() {
				alert("요청 완료!");
			});
		});
	</script>

	
	
<%@ include file="footer.jspf"%>
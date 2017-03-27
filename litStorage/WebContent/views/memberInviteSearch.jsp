<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>초대 회원 검색 - 소설 공동작업</title>

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
</jsp:include>
	<form method="post">
		<select name="type" id="type">
			<option value="id">아이디</option>
			<option value="name">이름</option>
		</select>
		<input type="text" name="keyword" id="keywordId">
		<input type="button" name="searchbutton" id="btn" value="검색">
	</form>
	<table id="result" class="table table-hover table-condensed">
		<colgroup>
			<col width="80" align="center">
			<col width="80">
			<col width="100">
			<col width="80">
		</colgroup>
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>이메일</th>
				<th>초대</th>
			</tr>
		</thead>
		<tbody>
			 <tr>
				<td>아이디</td>
				<td>이름</td>
				<td>이메일</td>
				<td>초대해볼까?</td>
			</tr> 
		</tbody>
	</table>



	<!-- request popup form div start-->
	<div id="sendRequest">
		<form class="form" action="${ctx }/litStorage/invite.do" id="send"
			method="post">
			<input type="hidden" name="litStorageId" value="${litStorage.id }">
			<h3>초대 메시지 작성</h3>
			<hr/>
			<br /> <label>보내는 사람 : ${loginId }</label> <br /> <br /> <label>초대
				메시지 :</label> <br /> <input type="text" id="message" name="message"
				placeholder="메세지를 작성하세요" /><br /> <br /> <input type="button" name="sendMessage"
				id="submit" value="보내기" /> <input type="button" id="cancel"
				value="취소" /> <br />
		</form>
	</div>
	<!-- request popup form div end -->

	<!-- script area -->

	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

	<script>
 	var addReceiver = function(id){
		var addContext = "<input type='hidden' name='receiverId' value='" + id + "'>";
		$("#send").append(addContext);
		$("#sendRequest").css("display", "block");
 	}
	$(document).ready(function() {
		
	$("#cancel").click(function() {
		$(this).parent().parent().hide();
	});
	
	// Contact form popup send-button click event.
	// Login form popup login-button click event.
	//
	$("#submit").click(function() {
		alert("초대 완료!");
		$(this).parent().parent().hide();
		$.ajax({
			url :"${ctx }/litStorage/invite.do",
			type:"post",
			data:{ receiverId:$("input[name='receiverId']").val(),
					message:$("input[name='message']").val(),
					litStorageId:$("input[name='litStorageId']").val()
			}
		});
	});

	/* search ajax */
						
	$("input[name='searchbutton']").click(function() {
		$.ajax({
			url : "${ctx}/member/search.do",
			data : {type : $("#type option:selected").val(),
					keyword : $("#keywordId").val()},
			type : "post",
			dataType : "xml",
			success : function(xml) {
					var xmlData = $(xml).find("members");
					var listLength = xmlData.length;
					$("#result > tbody").empty();			
					if (listLength) {
						var contentStr = "";
						$(xmlData).each(function() {
							contentStr += "<tr><td>"+ $(this).find("id").text() + "</td><td>"
								+ $(this).find("name").text() + "</td><td>"
								+ $(this).find("email").text() + "</td>"
								+ "<td>" + "<button onclick='addReceiver(\"" + $(this).find("id").text() + "\")' id='pop' type='button'>초대하기</button>" + "</td></tr>";
						});
						$("#result > tbody").append(contentStr);
					}
				}
			});
		});
	});
	</script>



<%@ include file="footer.jspf"%>
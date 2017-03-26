<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>작품 저장소 등록 - 소설 공동작업</title>

<%@ include file="header.jspf"%>



	<form action="${ctx }/litStorage/register.do" method="post"
		name="litRegister" onsubmit="return checkIt()">
		<input type="hidden" name="userId" value="${loginId }">
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" maxlength="30"></td>
			</tr>
			<tr>
				<td>소개</td>
				<td><input type="text" name="introduce" maxlength="300"></td>
			</tr>
		</table>
		<br>
		<div align="center">
			<input class="btn" type="submit" value="등록하기"">
		</div>
	</form>


	<script type="text/javaScript">
		function checkIt() {

			var litRegister = document.litRegister;

			if (!litRegister.name.value) {
				alert("이름을 입력하세요");
				return false;
			}

			if (!litRegister.introduce.value) {
				alert("소개를 입력하세요");
				return false;
			}
			return true;
		}
	</script>



<%@ include file="footer.jspf"%>
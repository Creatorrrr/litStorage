<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<c:set var="ctx">${pageContext.request.contextPath }</c:set>
<html lang="ko">
	<head>
	<title>작품 저장소 등록</title>
	<meta charset="utf-8">
	<link href="resources/css/bootstrap_modify.css" rel="stylesheet">
	<link href="resources/css/layout.css" rel="stylesheet">
	<script src="resources/js/jquery-2.1.3.js"></script>
	<script type="text/javascript">
		var checkValidate = function() {
			// name must be not empty
		}
	</script>
	</head>
	<body>
		<form action="${ctx }/litStorage/register.do" method="post">
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
			<button type="submit" onclick="checkValidate();">등록</button>
		</form>
	</body>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="ctx">${pageContext.request.contextPath }</c:set>
<html lang="ko">
<head>
<title>작품 저장소 등록</title>
<meta charset="utf-8">
<link href="resources/css/bootstrap_modify.css" rel="stylesheet">
<link href="resources/css/layout.css" rel="stylesheet">
<script src="resources/js/jquery-2.1.3.js"></script>
<!-- <script type="text/javascript">
	var checkValidate = function() {
		// name must be not empty
	}
</script> -->
</head>
<body>
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


</body>
>>>>>>> refs/remotes/origin/jo
</html>
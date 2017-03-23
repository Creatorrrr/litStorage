<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보수정</title>

<!-- <link href="style.css" rel="stylesheet" type="text/css"> -->

<script type="text/javaScript">
	function checkIt() {

		var userinput = document.userinput;

		if (!userinput.passwd.value) {
			alert("비밀번호를 입력하세요");
			return false;
		}

		if (!userinput.name.value) {
			alert("사용자 이름을 입력하세요");
			return false;
		}
		return true;
	}
</script>

</head>

<body>
	<form method="post" action="${ctx }/modifyPro.do" name="userinput"
		onsubmit="return checkIt()">
		<table width="600" border="1" cellspacing="0" cellpadding="3" align="center">
			<tr>
				<td colspan="2" height="39" align="center"><font size="+1"><b>회원정보수정</b></font></td>
			</tr>
			<tr>
				<td width="200">사용자 ID</td>
				<td width="400">${member.id}</td>
			</tr>
			<tr>
				<td width="200">비밀번호</td>
				<td width="400"><input type="password" name="password" size="10"maxlength="10" value="${member.password}"></td>
			</tr>
			<tr>
				<td width="200">이름</td>
				<td width="400"><input type="text" name="name" size="15" maxlength="10" value="${member.name}"></td>
			</tr>
			<tr>
				<td width="200">E-Mail</td>
				<td width="400"><input type="text" name="email" size="40" maxlength="30" value="${member.email}"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" name="modify" value="정보수정"> 
				<input type="button" value="취소" onclick="javascript:window.location='login.do'">
				<input type="button" value="탈퇴하기" onclick="javascript:window.location='${ctx }/member/deleteMember.do'">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<a href="${ctx }/member/deleteMember.do">탈퇴하기</a>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
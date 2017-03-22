<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보수정</title>

<!-- <link href="style.css" rel="stylesheet" type="text/css"> -->

<script type="text/javaScript">



function checkIt() {

var userinput = document.userinput;

 

if(!userinput.passwd.value ) {

alert("비밀번호를 입력하세요");

return false;

}

 

if(!userinput.name.value) {

alert("사용자 이름을 입력하세요");

return false;

}

return true;

}



</script>

</head>

<body>

<form method="post" action="modifyPro.do" name="userinput" onsubmit="return checkIt()">

 

<table width="600" border="1" cellspacing="0" cellpadding="3"  align="center">

<tr>
<td colspan="2" height="39" align="center">
<font size="+1" ><b>회원 정보수정</b></font></td>
</tr>
<tr>
<td colspan="2" align="center">회원의 정보를 수정합니다.</td>
</tr>
<!-- <tr>
<td width="200"><b>아이디 입력</b></td>

<td width="400">&nbsp;</td>

</tr>  -->

<tr>
<td  width="200"> 사용자 ID</td>
<td  width="400">${member.id}</td>
</tr>

<tr>
<td width="200"> 비밀번호</td>
<td width="400">
<input type="password" name="passwd" size="10" maxlength="10" value="${member.passwd}">
</td>
</tr> 

<!-- <tr>
<td  width="200"><b>개인정보 입력</b></td>
<td width="400">&nbsp;</td>
</tr>  -->

<tr>
<td   width="200">이름</td>
<td  width="400">
<input type="text" name="name" size="15" maxlength="10" value="${member.name}">
</td>
</tr>



<tr>
<td width="200">E-Mail</td>
<td width="400">
<input type="text" name="email" size="40" maxlength="30" value="${member.email}">
</td>
</tr>



<tr>
<td colspan="2" align="center">
<input type="submit" name="modify" value="정보수정" >
<input type="button" value="취소" onclick="javascript:window.location='login.do'">     
</td>
</tr>

</table>

</form>

</body>
</html>
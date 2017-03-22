<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<c:set var="ctx">${pageContext.request.contextPath }</c:set>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
 <style type="text/css">
    #jointable table{width:600px;}
    #jointable th{text-align: right; background-color:orange;}
    #jointable td input {border:1px solid seagreen;}
</style>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript" src="jquery.ui.datepicker-ko.js"></script>

<script type="text/javascript"> 
    $(document).ready(function() {
 
    $("#join").click(function() {
       
            var tel1_pattern = /(^01[016789]$)/; //정규식
 			var em =/^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
           
            if ($("#id").val() == "") {
                alert("아이디를  입력해주세요.");
                $("#id").focus();
            } else if ($("#pwd").val() == "") {
                alert("비밀번호를 입력해주세요.");
                $("#password").focus();
 
            } else if ($("#password2").val() == "") {
                alert("재확인 비밀번호확인를 입력해주세요.");
                $("#password2").focus();
 
            } else if ($("#password").val() != $("#password2").val()) {
                alert("비밀번호와 비밀번호 확인이 일치하지않습니다.");
                $("#password").val("");
                $("#password2").val("");
                $("#password").focus();
 
            } else if ($("#name").val() == "") {
                alert("이름을 입력해주세요.");
                $("#name").focus();
                
            }else if ($("#email").val() == "") {
                alert("이메일을 입력해주세요.");
                $("#email").focus();
                
            } else {
                alert("가입이 완료되었습니다.");
            }
 
 
        });
    });
   
</script>
</head>
<body>
<h1>회원 가입</h1>

<form action="${ctx}/join.do " method="post" >
 

<table id="jointable">
    <tr>
        <th>아이디</th>
        <td><input type="text" name="id" id="id" size="12" maxlength="12" /></td>
    </tr>
    <tr>
        <th>비밀번호</th>
        <td><input type="password" name="password" id="password" size="12" maxlength="12" /></td>
    </tr>
    <tr>
        <th>비밀번호확인</th>
        <td><input type="password" name="password2" id="password2" size="12" maxlength="12"/></td>
    </tr>
    <tr>
        <th>이름</th>
        <td><input type="text" name="name" id="name" size="12" maxlength="12" /></td>
    </tr>
    <tr>
        <th>이메일</th>
        <td><input type="text" name="email" id="email" size="50" maxlength="50" /></td>
    </tr>
    <tr>
       
        <td colspan="2">
            <input type="button" id="join" value="가입하기">
            <input type="reset" id="cancel" value="취소">
        </td>
    </tr>
</table>
 
 
</form>
 

</body>
</html>
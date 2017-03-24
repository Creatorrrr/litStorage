<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<script type="text/javascript" src="${ctx }/resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery.validate.js"></script>
<link href="${ctx }/resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">


$(function() {
	
	$("#registerForm").validate({
		
		
		rules: {
			id: "required",
			name: {
				required: true,
				minlength: 2
			},
			password: {
				required: true,
				minlength: 5
			},
			password2: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			}
		},
		messages: {
			id: "ID를 입력해주세요.",
			name: "이름을 입력해주세요.",
			password: {
				required: "비밀번호를 입력해주세요.",
				minlength: "비밀번호는 5자 이상이여야 합니다."
			},
			password2: {
				required: "비밀번호를 확인을 입력해주세요.",
				minlength: "비밀번호는 5자 이상이여야 합니다.",
				equalTo: "비밀번호가 일치하지 않습니다."
			},
			email: "이메일을 입력해주세요."
		}
	});

});

</script>

</head>
<body>
 

    <h3>회원가입</h3>
	
	<br>
	<form id="registerForm" action="${ctx }/member/register.do" method="post" >
		<table class="table">
			<tr>
				<th><label for="id">ID</label></th>
				<td><input id="id" name="id" class="form-control" type="text" value="" placeholder="ID를 입력해주세요." minlength="2" required></td>
			</tr>
			<tr>
				<th><label for="password">비밀번호</label></th>
				<td><input id="password" name="password" class="form-control" type="password" value="" placeholder="비밀번호를 입력해주세요." required></td>
			</tr>
			<tr>
				<th><label for="password2">비밀번호 확인</label></th>
				<td><input id="password2" name="password2" class="form-control" type="password" value="" placeholder="비밀번호 확인을 입력해주세요." required></td>
			</tr>
			<tr>
				<th><label for="name">이름</label></th>
				<td><input id="name"  name="name" class="form-control" type="text" value="" placeholder="이름을 입력해주세요." required></td>
			</tr>
			<tr>
				<th><label for="email">이메일</label></th>
				<td><input id="email"  name="email" class="form-control" type="email" value="" placeholder="이메일을 입력해주세요." required></td>
			</tr>
		</table><br>
		<div align="center"><input class="btn" type="reset" value="취소"> <input class="btn btn-success" type="submit" value="회원가입"></div>
	</form>
	<br>
        
        
        
        
 
</body>
</html>
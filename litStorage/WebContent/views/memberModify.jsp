<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>회원정보수정 - 소설 공동작업</title>

<%@ include file="header.jspf"%>


	<form id="registerForm" action="${ctx }/member/modify.do"  method="post">
		<table class="table table-striped table-hover " >
			<tr>
				<td colspan="2" height="39" align="center"><b>회원정보수정</b></td>
			</tr>
			<tr>
				<td width="200">사용자 ID</td>
				<td width="400">${member.id}</td>
			</tr>
			<tr>
				<td width="200">비밀번호</td>
				<td width="400"><input type="password" name="password" class="form-control" size="10"maxlength="10" value="${member.password}"></td>
			</tr>
			<tr>
				<td width="200">이름</td>
				<td width="400"><input type="text" name="name" class="form-control" size="15" maxlength="10" value="${member.name}"></td>
			</tr>
			<tr>
				<td width="200">E-Mail</td>
				<td width="400"><input type="text" name="email" class="form-control" size="40" maxlength="30" value="${member.email}"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="수정" class="btn btn-success" /> 
				<button type="button" onclick="backBtn();" class="btn btn-default">취소</button>
				<button type="button" onclick="deleteBtn();" class="btn btn-warning">탈퇴하기</button>
				</td>
			</tr>
		</table>
	</form>
	
	
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


	function backBtn(){
		location.href="${ctx }/member/detail.do";
	}
	
	function deleteBtn(){
		var r = confirm("탈퇴하시면 로그인을 할수가 없습니다!");
		if (r == true) {
		   	location.href="${ctx }/member/delete.do";
		}
	}

	
	
	</script>
	

<%@ include file="footer.jspf"%>
	
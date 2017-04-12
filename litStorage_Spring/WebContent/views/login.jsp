<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>로그인 - 소설 공동작업</title>

<%@ include file="header.jspf"%>
<title>로그인</title>
	<div class="alert alert-dismissible alert-warning">
	  <button type="button" class="close" data-dismiss="alert">&times;</button>
	  <h4>알림!</h4>
	  <p>로그인 하셔야 작품 저장소를 사용하실수 있습니다.</p>
	</div>
	<h1>로그인</h1>
	<form action="${ctx }/login.do" method="post">
		<table class="table">
			<tr>
				<th>ID</th>
				<td><input id="loginId" name="loginId" class="form-control"
					type="text" value="" placeholder="ID를 입력해주세요."></td>
			</tr>
			<tr>
				<th>Password</th>
				<td><input id="password" name="password" class="form-control"
					type="password" value="" placeholder="비밀번호를 입력해주세요."></td>
			</tr>
		</table>
		<br>
		<div align="center">
			<input class="btn" type="reset" value="취소"> <input
				class="btn btn-success" type="submit" value="로그인">
		</div>
	</form>




<%@ include file="footer.jspf"%>
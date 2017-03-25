<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="utf-8">
<title>index</title>
<%@ include file="_includeCSSJS.jspf" %>

<body>
	<%@ include file="_mainbar.jsp"%>
	
	
	
	<div class="container-fluid">
		<div class="row content">
		   <div class="col-sm-3 sidenav">
	
	
		   </div>
		   <div class="col-sm-9">
	
	
			</div>
		</div>
	</div>
	
		<div id="searchResult"></div>
		<%@ include file="main.jsp" %>

	
	<%@ include file="_footer.jsp" %>
	
	
	
</body>
</html>
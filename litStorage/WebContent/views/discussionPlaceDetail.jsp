<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>토론장 Detail</title>
<link href="${ctx }/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx }/resources/css/bootstrap-theme.min.css" rel="stylesheet">
<script type="text/javascript" src="${ctx }/resources/js/bootstrap.min.js"></script>
<style type="text/css">
div{border: 1px solid #ccc; }

</style>
</head>
<body>

<div class="container">

<%@ include file="./header.jspf" %>

<div class="col-xs-12 col-md-12">
	
	<div class="col-xs-6 col-md-4">
	
		<%@ include file="./submenuDP.jspf" %>

	</div>
	<div class="col-xs-12 col-md-8">
		
		
	</div>
</div>


</div>

</body>
</html>
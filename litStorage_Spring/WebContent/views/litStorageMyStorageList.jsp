<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>내 작품 저장소 - 소설 공동작업</title>

<%@ include file="header.jspf"%>






${box1 }	
	<%@ include file="_litStorageNav.jsp" %>

		
		
${box2 }
		<!-- 내 작품 저장소 목록 보여주기 -->
		<h3>내 작품 저장소</h3>
		<c:choose>
			<c:when test="${litStorages eq null || empty litStorages }">
				<table class="table table-striped table-hover ">
					<tr>
						<th colspan="7" class="text-center">등록된 작품저장소의 정보가 존재하지 않습니다.</th>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<c:forEach items="${litStorages }" var="litStorage">
					<div class="litStorageBox" style="display:inline-block; width:250px;border:2px solid #ccc">
						<table class="table table-striped table-hover " style=" table-layout: fixed;word-wrap:break-word;">
							<tr>
								<td>이름</td>
								<td><a href="${ctx}/litStorage/profile.do?id=${litStorage.id}">${litStorage.name }</a></td>
							</tr>
							<tr>
								<td>소개</td>
								<td>${litStorage.introduce }</td>
							</tr>
							<tr>
								<td>생성자</td>
								<td>${litStorage.creator.id }</td>
							</tr>
							<tr>
								<td>Email : </td>
								<td>${litStorage.creator.email }</td>
							</tr>
						</table>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

${box3 }


<%@ include file="footer.jspf"%>
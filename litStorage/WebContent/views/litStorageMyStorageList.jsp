<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>내 작품 저장소 - 소설 공동작업</title>

<%@ include file="header.jspf"%>





	<!-- 내 작품 저장소 목록 보여주기 -->
	<h1>내 작품 저장소</h1>
		
	<!-- 내 작품 저장소 페이지에서 작품 저장소 등록 버튼이 보임 -->
	<c:if test="${loginId ne null }">

		
		<!-- 작품 저장소 등록 버튼 -->
		<button id="btn1" type="button" class="text-warning" data-toggle="modal" data-target=".bs-example-modal-lg">작품 저장소 등록</button>
		<br>
		<div class="modal" id="modal">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		        <h4 class="modal-title">작품 저장소 등록</h4>
		      </div>
		      
		      
		      	<form action="${ctx }/litStorage/register.do" method="post" name="litRegister" onsubmit="return checkIt()">
					<input type="hidden" name="userId" value="${loginId }">
				      <div class="modal-body">
				        <label>이름<input type="text" name="name" maxlength="30" class="form-control" value="" placeholder="이름을 입력해주세요."></label>
						<label>소개<input type="text" name="introduce" maxlength="300" class="form-control" type="password" value="" placeholder="소개를 입력해주세요."></label>
						 <button type="submit" class="btn btn-success">등록하기</button>
				
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				      
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
		      
		      
		    </div>
		  </div>
		</div>
		<script type="text/javascript">
		$('#btn1').click(function(){
			$('#modal').modal('toggle');
		});
		</script>

	</c:if>
	
	<!-- 사이드 네비게이션 링크 목록 -->
	<a href="${ctx }/litStorage/myList.do">내 작품 저장소</a><br>
	<a href="${ctx }/litStorage/allList.do">작품 저장소 전체 목록</a><br>
	<hr>
		
		
		
		
		
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

<%@ include file="footer.jspf"%>
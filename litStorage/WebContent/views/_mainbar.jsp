<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />


<div class="jumbotron">

  <div class="container text-center">
    <h1>소설 공동 작업</h1>      
    <p>Mission, Vission & Values</p>
  </div>
  
  
  <div class=" navbar-right" style="background: #ccc;">
  		<c:choose>
			<c:when test="${loginId eq null }">
				<ul class="nav navbar-nav">
					<li>
						<!-- 로그인 시작 -->
						<button id="loginBtn" type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg">로그인</button>

						<div class="modal" id="modal">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						        <h4 class="modal-title">로그인</h4>
						      </div>
						      
						      <form action="${ctx }/login.do" method="post">
						      <div class="modal-body">
						        <label>ID<input id="loginId" name="loginId" class="form-control" type="text" value="" placeholder="ID를 입력해주세요."></label>
								<label>Password<input id="password" name="password" class="form-control" type="password" value="" placeholder="비밀번호를 입력해주세요."></label>
								 <button type="submit" class="btn btn-success">로그인</button>
						
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						      
						      </div>
						      </form>
						      
						    </div>
						  </div>
						</div>
						<script type="text/javascript">
						
							$('#loginBtn').click(function(){
								$('#modal').modal('toggle');
							});
						
						</script>
						<!-- 로그인 끝 -->


					</li>
					<li><a href="${ctx }/views/register.jsp" class="btn btn-success">회원가입</a></li>
				</ul>
			</c:when>
			<c:otherwise>
				<span class="glyphicon glyphicon-user"><b>${loginId}</b>님 환영합니다. [<a href="${ctx }/logout.do">로그아웃</a>]
				<ul >
					<li><a href="${ctx}/member/detail.do">회원정보</a></li>
					<li><a href="${ctx}/member/inviteList.do">초대 온 목록</a></li>
				</ul>
			</c:otherwise>
		</c:choose>
   </div>
</div>

<!-- 메인메뉴 시작 -->
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="${ctx }/">메인페이지</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
		<li><a href="${ctx }/post/list.do">자유게시판</a></li>
		<li><a href="${ctx }/litStorage/myList.do">작품 저장소</a></li>
		<li><a href="${ctx }/literature/allList.do">작품 목록</a></li>
		
      </ul>

		<!-- 메인 페이지용 작품 검색창, 검색시 이동함 -->
		<form method="get"  class="navbar-form navbar-right" action="${ctx }/literature/search.do">
			<div class="form-group">
				<select name="type" id="type" class="form-control">
					<option value="id">작가 아이디</option>
					<option value="name">작품 이름</option>
				</select>
				<input type="text" name="keyword" class="form-control">
				<input type="button" id="btn" name="search" value="검색" class="btn btn-success" >
			</div>
		</form>
		
		<script>
		$(document).ready(function() {
	
		/* ajax로 작품 다른거 검색하기*/
		$("input[name='search']").click(function() {
			$.ajax({
				url : "/litStorage/literature/search.do",
				data : {type : $("#type option:selected").val(),
						keyword : $("input[name='keyword']").val()},
				type : "post",
				dataType : "xml",
				success : function(xml) {
						var xmlData = $(xml).find("literature");
						var listLength = xmlData.length;
						//alert(listLength);
						$("#searchResult").empty();			
						if (listLength) {
							var contentStr = "";
							$(xmlData).each(function() {
								contentStr += "<div class='literatureBox'><table class='table table-striped table-hover'><tr><td>이름</td><td><a href='/litStorage/litStorage/profile.do?id="+ $(this).find("id").text() + "'>"+ $(this).find("name:first").text()
								+ "</a></td></tr><tr><td>작가</td><td>"+ $(this).find("creator").find("id").text() + "</td></tr><tr>"
								+"<td>소개</td><td>"+ $(this).find("introduce").text()
								+"</td></tr><tr><td>장르</td><td>"+$(this).find("genre").text()
								+ "</td></tr><tr><td>조회수</td><td>"+$(this).find("hits").text()+"</td></tr></table></div>";
							});
							$("#searchResult").append(contentStr);
							//alert(contentStr);
						}
					}
				});
			});
		});
		</script>
		<!-- 메인 메뉴 검색바 끝 -->

      </div>
    </div>
  </div>
</nav>
<!-- 메인 메뉴 끝 -->

<c:if test="${message ne null }">
	<!-- 요청결과 (3초후 자동 없어짐) -->
	<div class="alert alert-success fade in" id="message">
        <a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
        <p><strong>알림 메시지!</strong></p>
        ${message }
    </div>
</c:if>




<style>
		.literatureBox {
			display: inline-block;
		    margin: 10px;
		    border: 3px solid #73AD21;
		}
</style>
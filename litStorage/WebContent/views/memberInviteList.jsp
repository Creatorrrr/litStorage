<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ include file="_var.jsp"%>
<%@ include file="_html.jsp"%>
<title>초대 확인 목록 - 소설 공동작업</title>

<%@ include file="header.jspf"%>



<style type="text/css">
div{border: 1px solid #ccc; }
</style> 


<div class="container">


<div class="col-xs-12 col-md-12">

	<div class="col-xs-12 col-md-8">
		<h1>내게 온 초대 목록</h1>
		<div class="panel panel-default">
			
			<table class="table table-striped">
				<tbody>
						<c:choose>
							<c:when test="${inviteLists eq null || empty inviteLists}">
								<!--  request.setAttribute("inviteLists", list) null or not null-->
								<tr>
									<td colspan="6" align="center">초대 목록이 없습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach items="${inviteLists }" var="invite" varStatus="status">
										<tr><td>${status.count }.</td><tr>
										<tr><td>발신회원</td><td>${invite.sender.id}</td></tr>
										<tr><td>메시지</td><td>${invite.message}</td>
										<td><button type="button" onclick="confirm('${invite.sender.id}','${loginId }','${invite.litStorage.id }')">초대 승인</button>
										</tr>
										<tr><td>저장소 이름</td><td>${invite.litStorage.name }</td></tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
			</table>
		</div>
	</div>
</div>


</div>

<script type="text/javascript">
var confirm = function(senderId, receiverId, litStorageId){
	$.ajax({
		url :"${ctx }/member/decision.do",
		type:"get",
		data:{ senderId:senderId,
				receiverId:receiverId,
				litStorageId:litStorageId,
				form:"I"
		},
		success:function(){
			alert("저장소에 가입되었습니다.");
			location.href="${ctx}/member/inviteList.do";
		}
		});
	}
</script>



<%@ include file="footer.jspf"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<!-- 장르별 추천작, 장르를 select에서 선택하면 ajax로 controller 들러서 리스트 다시 뿌려줌 -->
<h2>장르별 추천작</h2>
<select id="recoGenre">
	<c:forEach items="${genreList }" var="genre">
		<option value="${genre.title }">${genre.title }</option>
	</c:forEach>
</select>
<!-- 추천작 결과 목록 뿌려주기 -->
<div class="container">
<div class="row">
<div id="recoResult">
	<c:forEach items="${newLiteratures }" var="literature">
		<div class='col-sm-4'>
		<div class='panel panel-primary'>
			<div class='panel-heading'></div>
			<div class='panel-body'>
				<div class="literatureBox">
					<table class="table table-striped table-hover ">
						<!-- 이건 지금 안됩니다. 이미지 추가되면 경로 되고나서 될거임~ 되면 주석 지우세용  -->
						<!-- <tr><td><img src="${literature.imagePath }"></td></tr> -->
						<tr>
							<td>작품명</td>
							<td><a
								href="${ctx}/episode/list.do?LiteratureId=${literature.id}">${literature.name }</a></td>
						</tr>
						<tr>
							<td>작가</td>
							<td>${literature.creator.id }</td>
						</tr>
						<tr>
							<td>장르</td>
							<td>${literature.genre }</td>
						</tr>
						<tr>
							<td>소개</td>
							<td>${literature.introduce }</td>
						</tr>
						<tr>
							<td>조회수</td>
							<td>${literature.hits }</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="panel-footer"></div>
		</div>
		</div>
	</c:forEach>
</div>
</div>
</div>

<!-- 장르별 신작, 추천작이랑 똑같이 동작함 -->
<h2>장르별 신작</h2>
<select id="newGenre">
	<c:forEach items="${genreList }" var="genre">
		<option value="${genre.title }">${genre.title }</option>
	</c:forEach>
</select>
<!-- 신작 결과 목록 뿌려주기 -->
<div class="container">
<div class="row">
<div id="newResult" >
	
	<c:forEach items="${newLiteratures }" var="literature">
		<div class='col-sm-4'>
		<div class='panel panel-primary'>
			<div class='panel-heading'></div>
			<div class='panel-body'>
				<div class="literatureBox">
					<table class="table table-striped table-hover ">
						<!-- 이건 지금 안됩니다. 이미지 추가되면 경로 되고나서 될거임~ 되면 주석 지우세용  -->
						<!-- <tr><td><img src="${literature.imagePath }"></td></tr> -->
						<tr>
							<td>작품명</td>
							<td><a
								href="${ctx}/episode/list.do?LiteratureId=${literature.id}">${literature.name }</a></td>
						</tr>
						<tr>
							<td>작가</td>
							<td>${literature.creator.id }</td>
						</tr>
						<tr>
							<td>장르</td>
							<td>${literature.genre }</td>
						</tr>
						<tr>
							<td>소개</td>
							<td>${literature.introduce }</td>
						</tr>
						<tr>
							<td>조회수</td>
							<td>${literature.hits }</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="panel-footer"></div>
		</div>
		</div>
	</c:forEach>
</div>
</div>
</div>

	
<script>
	/* 장르 선택할때마다 ajax로 갔다와서 장르별로 새로 뿌리기 */

	$(document).ready(function() {
		
		function secessFun(xml,resultId){
			var xmlData = $(xml).find("literature");
			var listLength = xmlData.length;
			$(resultId).empty();			
			if (listLength) {
				var contentStr = "";
				$(xmlData).each(function() {
					contentStr +="<div class='col-sm-4'><div class='panel panel-primary'><div class='panel-heading'></div><div class='panel-body'>"
							+"<div class='literatureBox'><table class='table table-striped table-hover'><tr><td>이름</td><td><a href='${ctx}/episode/list.do?LiteratureId="+ $(this).find("id").text() + "'>"+ $(this).find("name:first").text()
							+ "</a></td></tr><tr><td>작가</td><td>"+ $(this).find("creator").find("id").text() + "</td></tr><tr>"
							+"<td>소개</td><td>"+ $(this).find("introduce").text()
							+"</td></tr><tr><td>장르</td><td>"+$(this).find("genre").text()
							+ "</td></tr><tr><td>조회수</td><td>"+$(this).find("hits").text()+"</td></tr></table></div>"
							+ '</div><div class="panel-footer"></div></div></div>';
				});
				$(resultId).append(contentStr);
			}
		}
		
		
		$('#recoGenre').change(function() {
			$.ajax({
				url : "${ctx}/genreList.do",
				data : {
					type : "recoGenre",
					genre : $("#recoGenre option:selected").val()
				},
				type : "get",
				dataType : "xml",
				success : function(xml) {
					secessFun(xml,"#recoResult");
				}
			});
		});
		$('#newGenre').change(function() {
			$.ajax({
				url : "${ctx}/genreList.do",
				data : {
					type : "newGenre",
					genre : $("#newGenre option:selected").val()
				},
				type : "get",
				dataType : "xml",
				success : function(xml) {
					secessFun(xml,"#newResult");
				}
			});
		});
	});

</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AuthorWa</title>
</head>
<body>
	<%@ include file="header.jspf"%>


	<hr>

	<!-- 메인 페이지에서 로그인시 작품 저장소 등록 버튼이 보임 -->
	<c:if test="${loginId ne null }">
		<a href="${ctx }/litStorage/register.do">작품 저장소 등록</a>
		<br>
	</c:if>
	<!-- 메인 페이지용 작품 검색창, 검색시 이동함 -->
	<form method="get" action="${ctx }/literature/search.do">
		<label for="type">작품 검색</label> <select name="type" id="type">
			<option value="id">작가 아이디</option>
			<option value="name">작품 이름</option>
		</select><input type="text" name="keyword"> <input type="submit"
			name="search" id="btn" value="검색">
	</form>

	<!-- 장르별 추천작, 장르를 select에서 선택하면 ajax로 controller 들러서 리스트 다시 뿌려줌 -->
	<h2>장르별 추천작</h2>
	<select id="recoGenre">
		<c:forEach items="${genreList }" var="genre">
			<option value="${genre.title }">${genre.title }</option>
		</c:forEach>
	</select>
	<!-- 추천작 결과 목록 뿌려주기 -->
	<div id="recoResult">
		<c:forEach items="${recoLiteratures }" var="literature">
			<div class="literatureBox">
				<table border="1">
					<!-- 이건 지금 안됩니다. 이미지 추가되면 경로 되고나서 될거임~ 되면 주석 지우세용  -->
					<!-- <tr>
			<td><img src="${literature.imagePath }"></td>
			</tr> -->
					<tr>
						<td>작품명</td>
						<td><a
							href="${ctx}/episode/list.do?Literatureid=${literature.id}">${literature.name }</a></td>
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
		</c:forEach>
	</div>

	<!-- 장르별 신작, 추천작이랑 똑같이 동작함 -->
	<h2>장르별 신작</h2>
	<select id="newGenre">
		<c:forEach items="${genreList }" var="genre">
			<option value="${genre.title }">${genre.title }</option>
		</c:forEach>
	</select>
	<!-- 신작 결과 목록 뿌려주기 -->
	<div id="newResult">
		<c:forEach items="${newLiteratures }" var="literature">
			<div class="literatureBox">
				<table border="1">
					<!-- 이건 지금 안됩니다. 이미지 추가되면 경로 되고나서 될거임~ 되면 주석 지우세용  -->
					<!-- <tr>
			<td><img src="${literature.imagePath }"></td>
			</tr> -->
					<tr>
						<td>작품명</td>
						<td><a
							href="${ctx}/episode/list.do?Literatureid=${literature.id}">${literature.name }</a></td>
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
		</c:forEach>
	</div>
	
	<!-- script area -->
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		
	<script>
		/* 장르 선택할때마다 ajax로 갔다와서 장르별로 새로 뿌리기 */

		$(document).ready(function() {
			$('#recoGenre').change(function() {
				$.ajax({
					url : "${ctx}/genreList.do",
					data : {type : "recoGenre",
							genre : $("#recoGenre option:selected").val()},
					type : "get",
					dataType : "xml",
					success : function(xml) {
							var xmlData = $(xml).find("literature");
							var listLength = xmlData.length;
							$("#recoResult").empty();			
							if (listLength) {
								var contentStr = "";
								$(xmlData).each(function() {
									contentStr += "<div class='literatureBox'><table border='1'><tr><td>이름</td><td><a href='${ctx}/litStorage/profile.do?id="+ $(this).find("id").text() + "'>"+ $(this).find("name:first").text()
											+ "</a></td></tr><tr><td>작가</td><td>"+ $(this).find("creator").find("id").text() + "</td></tr><tr>"
											+"<td>소개</td><td>"+ $(this).find("introduce").text()
											+"</td></tr><tr><td>장르</td><td>"+$(this).find("genre").text()
											+ "</td></tr><tr><td>조회수</td><td>"+$(this).find("hits").text()+"</td></tr></table></div>";
								});
								$("#recoResult").append(contentStr);
							}
						}
					});
			});
			$('#newGenre').change(function() {
				$.ajax({
					url : "${ctx}/genreList.do",
					data : {type : "newGenre",
							genre : $("#newGenre option:selected").val()},
					type : "get",
					dataType : "xml",
					success : function(xml) {
							var xmlData = $(xml).find("literature");
							var listLength = xmlData.length;
							$("#newResult").empty();			
							if (listLength) {
								var contentStr = "";
								$(xmlData).each(function() {
									contentStr += "<div class='literatureBox'><table border='1'><tr><td>이름</td><td><a href='${ctx}/litStorage/profile.do?id="+ $(this).find("id").text() + "'>"+ $(this).find("name:first").text()
											+ "</a></td></tr><tr><td>작가</td><td>"+ $(this).find("creator").find("id").text() + "</td></tr><tr>"
											+"<td>소개</td><td>"+ $(this).find("introduce").text()
											+"</td></tr><tr><td>장르</td><td>"+$(this).find("genre").text()
											+ "</td></tr><tr><td>조회수</td><td>"+$(this).find("hits").text()+"</td></tr></table></div>";
								});
								$("#newResult").append(contentStr);
							}
						}
					});
			});
		});

	</script>
</body>
</html>
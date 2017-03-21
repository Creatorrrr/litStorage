<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>epsodeList</title>
<meta charset="utf-8">
<link href="resources/css/bootstrap_modify.css" rel="stylesheet">
<link href="resources/css/layout.css" rel="stylesheet">
<script src="resources/js/jquery-2.1.3.js"></script>

</head>
<body>
	<div>
		<ul class="nav navbar-nav">
			<li><a href="teamList.html">로그인</a></li>
		</ul>
		<ul class="nav navbar-nav">
			<li><a href="teamList.html">회원가입</a></li>
		</ul>


	</div>

	<!-- Main Navigation ================================================================================= -->
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">

			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="teamList.html">자유 게시판</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li><a href="teamList.html">작품 저장소</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li><a href="teamList.html">작품 목록</a></li>
				</ul>
				<div>
					<form action="list.do" method="post">
						<select><option value="literature">작품 명</option>
							<option value="memberName">회원 이름</option>
						</select> <input type="text" name="search" placeholder="입력해주세요."> <input
							class="btn btn-xs btn-default" type="submit" value="검색">
					</form>
				</div>

			</div>
		</div>
	</div>

	<!-- Header ========================================================================================== -->
	<header>
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="jumbotron">
						<h2>작품 목록</h2>
					</div>
				</div>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="teamList.html">프로필</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li><a href="teamList.html">작품 목록</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li><a href="teamList.html">토론장</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li><a href="teamList.html">참가 회원 목록</a></li>
				</ul>
				<ul class="nav navbar-nav">
					<li><a href="teamList.html">회원 초대</a></li>
				</ul>

			</div>
		</div>
	</header>

	<!-- Container ======================================================================================= -->

	<div class="container">
		<div class="row">

			<div class="col-sm-12 col-lg-12">
				<!-- <tr>
					<td><img src="/recipe/image.do?recipeId=" width="128"
						height="123"></td>
				</tr> -->
				<div>
					<h3>연재 글 목록</h3>
				</div>
				<div>
					<img src="/recipe/image.do?recipeId=" width="128" height="123">
					<button
						onclick="location.href='${ctx }/literature/delete.do?literature=;'">작품삭제</button>
					<h3>장르</h3>
					<h4>멜로</h4>
					<h3>작가명</h3>
					<h4>드록바</h4>
					<br>
					<h3>이름</h3>
					<h4>물</h4>
					<h3>아이디</h3>
					<h4>88</h4>
					<br>
					<h3>소개글</h3>
					<h4>가나체크</h4>
					<h3>이메일</h3>
					<h4>
						@
						<h4>
				</div>
				<div>
					<button onclick="location.href='${ctx }/episode/register.do;'">연재글
						등록</button>
				</div>
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover">
						<colgroup>
							<col width="100" />
							<col width="200" />
							<col width="100" />
							<col width="200" />
							<col width="200" />
							<col width="*" />
							<col width="100" />
						</colgroup>
						<thead>
							<tr>
								<th class="text-center">제목</th>
								<th class="text-center">작성자</th>
								<th class="text-center">공개범위</th>
							</tr>
						</thead>
						<tbody>
							<!-- 
							<tr>
								<th colspan="7" class="text-center">등록된 선수 정보가 존재하지 않습니다.</th>
							</tr>
							 -->

							<tr>
								<td class="text-center"><a
									href="episodeDetail.do?episodeId= ">연재 글 이름</a></td>
								<td class="text-center">작성자</td>
								<td class="text-center"><select><option
											value="noAll">저장소공개</option>
										<option value="All">모두 공개</option>
								</select></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<!-- Footer ========================================================================================== -->
		<footer>
			<hr>
			<p>© NamooBaseball 2016.</p>
		</footer>
	</div>
</body>
</html>
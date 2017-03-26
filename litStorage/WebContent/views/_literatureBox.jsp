<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<c:forEach items="${ box_list }" var="literature">
	<div class="literatureBox">
		<table  class="table table-striped table-hover ">
			<tr>
				<td colspan="2"><img class="imagePath img-responsive" src="${literature.imagePath }" style="width:165px;height:239px;"></td>
			</tr>
			<tr>
				<td>작품명</td>
				<td><a class="literature" href="${ctx}/episode/list.do?literatureId=${literature.id}">${literature.name }</a></td>
			</tr>
			<tr>
				<td>작가</td>
				<td class="creatorId">${literature.creator.id }</td>
			</tr>
			<tr>
				<td>장르</td>
				<td class="genre">${literature.genre }</td>
			</tr>
			<tr>
				<td>소개</td>
				<td class="introduce">${literature.introduce }</td>
			</tr>
			<tr>
				<td>조회수</td>
				<td class="hits">${literature.hits }</td>
			</tr>
		</table>
	</div>
</c:forEach>

<%@page import="kr.or.ddit.paging.model.PageVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<c:forEach items="${data.userList}" var="user" varStatus="status">
	<tr class="userTr" data-userid="${user.userId}">
		<td class="userId">${user.userId}</td>
		<td>${user.name}</td>
		<td>${user.alias}</td>
		<td></td>
	</tr>
</c:forEach>

SEPERTATORSEPERTATOR
<!-- 사용자수 : 105건
							페이지네이션 : 11건
						 -->

<c:choose>
	<c:when test="${pageVo.page == 1 }">
		<%-- <c:when test="${pageVo.getPage() == 1 }"> --%>
		<li class="disabled"><span>«</span></li>
	</c:when>
	<c:otherwise>
		<li><a
			href="javascript:userPagingListAjaxHtml(${pageVo.page - 1},${pageVo.pageSize});">«</a>
		</li>
	</c:otherwise>
</c:choose>

<c:forEach begin="1" end="${data.paginationSize}" var="i">
	<c:choose>
		<c:when test="${pageVo.page == i}">
			<li class="active"><span>${i}</span></li>
		</c:when>
		<c:otherwise>
			<li><a
				href="javascript:userPagingListAjaxHtml(${i},${pageVo.pageSize})">${i}</a></li>
		</c:otherwise>
	</c:choose>

</c:forEach>


<c:choose>
	<c:when test="${pageVo.page == paginationSize}">
		<li class="disabled"><span>»</span></li>
	</c:when>
	<c:otherwise>
		<li><a
			href="javascript:userPagingListAjaxHtml(${pageVo.page + 1},${pageVo.pageSize})">»</a>
		</li>

	</c:otherwise>
</c:choose>

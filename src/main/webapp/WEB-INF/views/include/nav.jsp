<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title></title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbor-header">
			<a class="navbar-brand" href="/home.do">Simple Board</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="/home.do"><spring:message code="nav.menu.label.home" /></a></li>
			<li><a href="/board/list.do"><spring:message code="nav.menu.label.board" /></a></li>
			<li><a href="/book/form.do"><spring:message code="nav.menu.label.book" /></a></li>
		</ul>

		
		<ul class="nav navbar-nav navbar-right">
		<c:choose>
			<c:when test="${empty LOGIN_USER }">
				<li><a href="/user/form.do"><spring:message code="nav.menu.label.register" /></a></li>
				<li><a href="/user/login.do"><spring:message code="nav.menu.label.login" /></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="/user/logout.do"><spring:message code="nav.menu.label.logout" /></a></li>
			</c:otherwise>
		</c:choose>
		</ul>
		
		<c:if test="${not empty LOGIN_USER }">
			<p class="navbar-text navbar-right"><strong>${LOGIN_USER.name }</strong><spring:message code="nav.menu.label.msg" /></p>
		</c:if>
	</div>
</nav>
</div>
</body>
</html>
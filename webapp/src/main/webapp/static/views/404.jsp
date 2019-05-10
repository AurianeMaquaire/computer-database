<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/static/css/bootstrap.min.css"/>"
	rel="stylesheet" media="screen">
<link href="<c:url value="/static/css/font-awesome.css"/>"
	rel="stylesheet" media="screen">
<link href="<c:url value="/static/css/main.css"/>" rel="stylesheet"
	media="screen">
</head>
<body>

	<%@include file="header.jsp"%>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				<spring:message code="error404" />
				<br />
				<!-- stacktrace -->
				<div class="exception">
					<strong><c:out value="${exception}" /></strong>
				</div>
			</div>
		</div>
	</section>

	<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/static/js/dashboard.js"/>"></script>

</body>
</html>
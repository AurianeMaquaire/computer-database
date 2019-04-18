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
<link href="static/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand"
				href="<c:url value="/Dashboard?currentPage=0"/>"> Application -
				Computer Database </a>
			<input type="button" onclick="location.href='<c:url value="?lang=en"/>'" value="EN"> 
			<input type="button" onclick="location.href='<c:url value="?lang=fr"/>'" value="FR">
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				<spring:message code="error404" />
				<br />
				<!-- stacktrace -->
			</div>
		</div>
	</section>

	<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/static/js/dashboard.js"/>"></script>

</body>
</html>
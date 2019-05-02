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
				Computer Database </a> <input type="button"
				onclick="location.href='<c:url value="?lang=en"/>'" value="EN">
			<input type="button"
				onclick="location.href='<c:url value="?lang=fr"/>'" value="FR">
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="login" />
					</h1>

					<form action="<c:url value="/LoginProcess" />" method="POST">
						<fieldset>
							<legend>
								<spring:message code="information" />
							</legend>
							<div class="form-group">
								<label for="username"><spring:message code="username" /></label>
								<input type="text" name="username" />
							</div>
							<div class="form-group">
								<label for="password"><spring:message code="password" /></label>
								<input type="password" name="password" />
							</div>
							<div class="form-group">
								<input name="submit" type="submit"
									value="<spring:message code="buttonLogin"/>" />
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</section>

	<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/static/js/dashboard.js"/>"></script>

</body>
</html>
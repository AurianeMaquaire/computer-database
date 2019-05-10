<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	
	<%@include file="header.jsp" %>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="create" />
					</h1>
					<form:form modelAttribute="user" name="createForm" id="createForm"
						action="createAccount" method="POST">
						<fieldset>
							<legend>
								<spring:message code="information" />
							</legend>
							<div class="form-group">
								<form:label path="username" for="username">
									<spring:message code="username" />
								</form:label>
								<form:input path="username" type="text" name="username" />
							</div>
							<div class="form-group">
								<form:label for="password" path="password">
									<spring:message code="password" />
								</form:label>
								<form:input path="password" type="password" name="password" />
							</div>

							<div class="alert alert-danger">
								<strong><c:out value="${exception}" /></strong>
							</div>

							<div class="form-group">
								<input type="submit"
									value="
								<spring:message code="create"/>"
									class="btn btn-primary" name="submit">
								<spring:message code="or" />
								<a href=<c:url value ="/login"/> class="btn btn-default"><spring:message
										code="cancel" /></a>
							</div>
						</fieldset>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/static/js/dashboard.js"/>"></script>

</body>
</html>
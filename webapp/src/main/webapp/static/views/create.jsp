<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
				onclick="location.href='<c:url value="/Dashboard?lang=en"/>'"
				value="EN"> <input type="button"
				onclick="location.href='<c:url value="/Dashboard?lang=fr"/>'"
				value="FR">
		</div>
	</header>

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

							<div class="exception">
								<font color="red" size="+1"> <c:out value="${exception}" />
								</font>
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
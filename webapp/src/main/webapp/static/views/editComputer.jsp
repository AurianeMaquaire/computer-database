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
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.id}</div>
					<h1>
						<spring:message code="editComputer" />
					</h1>

					<form:form method="POST" action="editComputer"
						modelAttribute="computer">
						<form:hidden path="id" value="${computer.id}" />
						<fieldset>
							<div class="form-group">
								<form:label path="name" for="computerName">
									<spring:message code="computerName" />
								</form:label>
								<form:input path="name" type="text" class="form-control"
									id="name" name="name" placeholder="Computer name"
									value="${computer.name}" />
							</div>
							<div class="form-group">
								<form:label path="introduced" for="introduced">
									<spring:message code="introduced" />
								</form:label>
								<form:input path="introduced" type="date" class="form-control"
									id="introduced" name="introduced" placeholder="Introduced date"
									value="${computer.introduced}" />
							</div>
							<div class="form-group">
								<form:label path="discontinued" for="discontinued">
									<spring:message code="discontinued" />
								</form:label>
								<form:input path="discontinued" type="date" class="form-control"
									id="discontinued" name="discontinued"
									placeholder="Discontinued date"
									value="${computer.discontinued}" />
							</div>
							<div class="form-group">
								<form:label path="companyId" for="companyId">
									<spring:message code="company" />
								</form:label>
								<form:select path="companyId" class="form-control"
									id="companyId" name="companyId">

									<option value="${computer.companyId}">${computer.companyName}</option>
									<c:forEach var="company" items="${listCompanies}">
										<form:option value="${company.id}">${company.name}</form:option>
									</c:forEach>

								</form:select>
							</div>
						</fieldset>

						<div class="exception">
							<strong> <c:out value="${exception}" />
							</strong>
						</div>

						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="edit" />"
								class="btn btn-primary">
							<spring:message code="or" />
							<a href="<c:url value="/computers"/>" class="btn btn-default"><spring:message
									code="cancel" /></a>
						</div>
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
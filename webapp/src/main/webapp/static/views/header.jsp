<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand pull-left"
				href="<c:url value="/computers"/>"> Application -
				Computer Database </a> <span class="navbar-brand pull-right"> <input
				type="button" class="btn btn-default"
				onclick="location.href='<c:url value="/computers?lang=en"/>'"
				value="EN"> <input type="button" class="btn btn-default"
				onclick="location.href='<c:url value="/computers?lang=fr"/>'"
				value="FR"> <input type="button"
				onclick="location.href='<c:url value="/login?logout=true"/>'"
				value="<spring:message code="buttonLogout" />"
				class="btn btn-primary">
			</span>
		</div>
	</header>
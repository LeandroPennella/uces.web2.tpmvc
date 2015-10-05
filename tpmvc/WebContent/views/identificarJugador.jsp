<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Identifiquese</title>
<link href='<c:url value="/css/bootstrap.min.css"/>' rel="stylesheet">
<script type="text/javascript"
	src='<c:url value="/js/jquery-1.11.3.min.js" />'></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#nombre").focus();
	});
</script>
</head>
<body role="document"	>
	
	<div class="container theme-showcase" role="main">
		
			<div class="jumbotron">
				<h1>
					<fmt:message key="jugador.label.identifiquese" />
				</h1>
	
				<c:url value="/jugador/validarJugador.do" var="x"></c:url>
				<form:form method="POST" commandName="jugador" action="${x}" >
					<!-- nombre -->
					<form:label path="nombre">
						<fmt:message key="jugador.label.identifiquese" />
					</form:label>
					<form:input path="nombre" />
					<form:errors path="nombre" cssStyle="color: red" />
	
					<!-- idiomas -->
	
					<form:select id="idioma" path="idioma">
						<form:options items="${idiomas}" />
					</form:select>
					<form:button>Comenzar!</form:button>
				</form:form>
			</div>
		
	</div>


</body>
</html>
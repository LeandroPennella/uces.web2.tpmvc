<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Identificar Usuario</title>

</head>
<body>
	<h1>Complete el formulario</h1>
	<form:form method="POST" commandName="jugador" action="validarJugador.do">
		<form:label path="nombre">
			<fmt:message key="jugador.label.identifiquese" />
		</form:label>
		<form:input path="nombre" />
		<form:errors path="nombre" cssStyle="color: red" />
		<form:button>Comenzar!</form:button>
	</form:form>
</body>
</html>
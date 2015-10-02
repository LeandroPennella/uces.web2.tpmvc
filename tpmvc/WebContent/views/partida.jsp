<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src='<c:url value="/script/jquery-1.11.3.min.js" />'></script> 
<script type="text/javascript">
  
$(document).ready(function() {
  $("#valorElegido").focus();
});
</script>
</head>
<body>
	<h1><fmt:message key="partida.titulo"></fmt:message></h1>
	Jugador: ${partida.getJugador().getNombre()}<br/>
	
	<a href="<c:url value="/jugador/iniciarPartida.do" ></c:url>">ReIniciar</a>
	<a href="<c:url value="/identificarJugador.do" ></c:url>">Salir</a>
	<h2>Intentos</h2>
	<c:forEach var="intento" items="${partida.intentos}">
	<c:if test="${intento!=''}">
		${intento.getValorElegido()}>${intento.getDiferencia()}<br/>
	</c:if> 
	</c:forEach>
	<jsp:include page="estadisticas.jsp"></jsp:include>
	
	<c:url value="/partida/procesarIntento.do" var="x"></c:url>

	<form:form method="POST" commandName="intento" action="${x}">
		<form:label path="valorElegido">
			<fmt:message key="partida.label.valorElegido" />
		</form:label>
		<form:input path="valorElegido"  />
		<form:errors path="valorElegido" cssStyle="color: red" />
		<form:button>Intentar!</form:button>
	</form:form>
</body>
</html>
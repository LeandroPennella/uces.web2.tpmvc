<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="bs-example">
	<h2>Estadisticas</h2>
	<table class="table table-striped">
	<tr><td>
		<h4>Mejor marca en esta sesión.</h4>
		</td></tr><tr><td>
		<c:out value="${jugador.getMejorScore()<10?jugador.getMejorScore():'N/A'}"></c:out>
	</td></tr><tr><td>
	 
		<h4>Mejor marca en esta máquina</h4>
		</td></tr><tr><td>
		<c:forEach var="cookies" items="${cookie}">
			<c:if test="${cookies.value.name==jugador.getNombre()}">
				<li><c:out value="${cookies.value.name}" />-<c:out value="${cookies.value.value}" /></li>
			</c:if>
		</c:forEach>
		
	</td></tr><tr><td>
	
		<h4>Mejor jugador en esta máquina </h4>
		</td></tr><tr><td>
		<c:set var="mejorJugadorNumero" scope="session" value="${10}"></c:set>
		<c:set var="mejorJugadorNombre" scope="session" value="${''}"></c:set>
		<c:forEach var="cookies" items="${cookie}">
			<c:if test="${cookies.value.name=='mejorJugadorNombre'}">
				<c:out value="${cookies.value.value}"></c:out>-
			</c:if>
			
			<c:if test="${cookies.value.name=='mejorScoreValor'}">
				<c:out value="${cookies.value.value}"></c:out>
			</c:if>
		</c:forEach>
		<c:out value="${mejorJugadorNumero<10?mejorJugadorNombre - mejorJugadorNumero:'N/A'}"></c:out>
		
	</td></tr><tr><td>
	
		<h4>Mejor jugador del sistema</h4>
		</td></tr><tr><td>
		${mejorJugador.getNombre()} - ${mejorJugador.getMejorScore()}
		
	</td></tr>
	</table>
</div> 
</body>
</html>
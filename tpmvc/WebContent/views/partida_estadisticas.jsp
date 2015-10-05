<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="bs-example">
	<h2><fmt:message key="estadisticas"/></h2>
	<table class="table table-striped">
	<tr><td>
		<b><fmt:message key="estadisticas.label.marcaSesion"/></b>
		</td></tr><tr><td>
		<c:out value="${jugador.getMejorScore()<10?jugador.getMejorScore():'N/A'}"></c:out>
	</td></tr><tr><td>
	 
 		<b><fmt:message key="estadisticas.label.marcaNavegador"/></b>
		</td></tr><tr><td>
		<c:set var="mejorMarcaEnMaquina" scope="session" value="${10}"></c:set>
		<c:forEach var="cookies" items="${cookie}">
			<c:if test="${cookies.value.name!='JSESSIONID'}">
				<c:if test="${cookies.value.value<mejorMarcaEnMaquina}">
					<c:set var="mejorMarcaEnMaquina" scope="session" value="${cookies.value.value}"></c:set>		
				</c:if>
			</c:if>
		</c:forEach>
		<c:out value="${mejorMarcaEnMaquina<10?mejorMarcaEnMaquina :'N/A'}"></c:out>
		
	</td></tr><tr><td>
		<b><fmt:message key="estadisticas.label.jugadorNavegador"/></b>

		</td></tr><tr><td>
		<c:set var="mejorJugadorNumero" scope="session" value="${10}"></c:set>
		<c:set var="mejorJugadorNombre" scope="session" value="${''}"></c:set>

		<c:forEach var="cookies" items="${cookie}">
			<c:if test="${cookies.value.name!='JSESSIONID'}">
				<c:if test="${cookies.value.value<mejorJugadorNumero}">
					<c:set var="mejorJugadorNumero" scope="session" value="${cookies.value.value}"></c:set>
					<c:set var="mejorJugadorNombre" scope="session" value="${cookies.value.name}"></c:set>
				</c:if>
			</c:if>
		</c:forEach>
		
		<c:out value="${mejorJugadorNumero<10?(mejorJugadorNombre.concat('-').concat(mejorJugadorNumero)):'N/A'}"></c:out>
		
	</td></tr><tr><td>
		<b><fmt:message key="estadisticas.label.jugadorSistema"/></b>
		</td></tr><tr><td>
		<c:choose>
		  <c:when test="${mejorJugador.getNombre()!=null}">
			${mejorJugador.getNombre()} - ${mejorJugador.getMejorScore()}
		  </c:when>
		  <c:otherwise>
			N/A
		  </c:otherwise>
		</c:choose>
	</td></tr>
	</table>
</div> 
</body>
</html>
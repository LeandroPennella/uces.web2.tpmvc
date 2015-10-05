<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<nav class="navbar navbar-inverse navbar-fixed-top">
		
		<div class="navbar-header">
 			<img alt="" src="../imagenes/Roulette.gif">
			<a class=" navbar-brand" href="#"><fmt:message key="titulo"></fmt:message></a>
		</div>
		
		<div id="navbar" class="navbar-collapse collapse">
			<div class="navbar-form navbar-right">
				<a class="jugador">${partida.getJugador().getNombre()}</a> 
				<a href="<c:url value="/jugador/iniciarPartida.do" ></c:url>" class="btn btn-success"><fmt:message key="reiniciar"/></a> 
				<a href="<c:url value="/identificarJugador.do" ></c:url>" class="btn btn-success"><fmt:message key="salir"/></a>
			</div>
		</div>
</nav>
 

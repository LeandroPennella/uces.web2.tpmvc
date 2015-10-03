<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="header clearfix">
	<nav>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav nav-pills pull-right">
				<li role="presentation">Jugador: ${partida.getJugador().getNombre()}</li> 
				<li role="presentation"><a href="<c:url value="/jugador/iniciarPartida.do" ></c:url>" class="btn btn-success">ReIniciar</a></li>
				<li role="presentation"><a href="<c:url value="/identificarJugador.do" ></c:url>" class="btn btn-success">Salir</a></li>
			</ul>
		</div>
	</nav>
	<h3 class="text-muted"> <fmt:message key="partida.titulo"></fmt:message></h3>
</div>
	
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2>Intentos</h2>
<table class="table">
<c:forEach var="intento" items="${partida.intentos}">
	<c:if test="${intento!=''}">
	<tr><td>${intento.getValorElegido()}</td><td>${intento.getDiferencia()}</td></tr>
	</c:if>
</c:forEach>
</table>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h2><fmt:message key="intentos"/></h2>
<table class="table">
<c:forEach var="intento" items="${partida.intentos}">
	<c:if test="${intento!=''}">
	<tr><td>${intento.getValorElegido()}</td><td><fmt:message key="${intento.getDiferencia()}"/></td></tr>
	</c:if>
</c:forEach>
<c:if test="${partida.intentos.size()==0}">
	<tr><td>&nbsp;</td></tr>
</c:if>
</table>
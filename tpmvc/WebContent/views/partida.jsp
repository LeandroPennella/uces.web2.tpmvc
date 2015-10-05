<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title><fmt:message key="titulo"/></title>
	<link href='<c:url value="/css/bootstrap.min.css"/>' rel="stylesheet">
	<link href='<c:url value="/ui/bootstrap-sweetalert/lib/sweet-alert.css"/>' rel="stylesheet">
	<link href='<c:url value="/css/partida.css"/>' rel="stylesheet">
	<script type="text/javascript" src='<c:url value="/js/jquery-1.11.3.min.js" />'></script>
	<script type="text/javascript" src='<c:url value="/ui/bootstrap-sweetalert/lib/sweet-alert.min.js" />'></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#valorElegido").focus();
		});
	</script>
<style type="text/css">

</style>
</head>
<body>
	
	<jsp:include page="partida_header.jsp"></jsp:include>
	
	<div class="col-md-3">
		<jsp:include page="partida_intentos.jsp"></jsp:include>
	</div>

	<div class="col-md-6" style="text-align: center;">
    	<div class="panel panel-default">
			<div class="panel-body">
				<c:url value="/partida/procesarIntento.do" var="x"></c:url>
				<form:form method="POST" commandName="intento" action="${x}">
					<form:label path="valorElegido">
						<h2><fmt:message key="partida.label.valorElegido" /></h2>
					</form:label>
					<br>
					<form:input path="valorElegido" size="4" cssStyle=""/>
					<form:errors path="valorElegido" cssStyle="color: red" /><br/><br/>
					
					<form:button><fmt:message key="partida.botonIntentar" /></form:button>
				</form:form>
			</div>
		</div>
	</div>
	
	
	<div class="col-md-3">
		<jsp:include page="partida_estadisticas.jsp"></jsp:include>
	</div>

	<c:if test="${partida.getNumeroADescubrir()==0}">
		<script type="text/javascript">
		$(document).ready(function(){
			  setTimeout(function() {
			    swal({
			      title: "<fmt:message key="partida.ganaste" />",
			      text: "<fmt:message key='partida.ganaste.mensaje' > <fmt:param value='${partida.getIntentos().size()}'/> </fmt:message>",
			      type: "success",
			      confirmButtonText: "Ok"
			    }, function(){
				    window.location.href = "<c:url value='/jugador/iniciarPartida.do' />";
				});
			  }, 500);
			});
		</script>
	</c:if>

	<c:if test="${partida.getIntentos().size()>9}">
		<script type="text/javascript">
		$(document).ready(function(){
			  setTimeout(function() {
			    swal({
			      title: "<fmt:message key="partida.perdiste" />",
			      text: "<fmt:message key='partida.perdiste.mensaje' > <fmt:param value='${partida.getNumeroADescubrir()}'/> </fmt:message>",
			      type: "error",
			      confirmButtonText: "Ok"
			    }, function(){
				    window.location.href = "<c:url value='/jugador/iniciarPartida.do' />";
				});
			 }, 500);
			});
		</script>
	</c:if>	
</body>
</html>
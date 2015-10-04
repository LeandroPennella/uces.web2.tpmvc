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
	<title>Adivine el numero</title>
	<link href='<c:url value="/css/bootstrap.min.css"/>' rel="stylesheet">
	<link href='<c:url value="/ui/bootstrap-sweetalert/lib/sweet-alert.css"/>' rel="stylesheet">
	<script type="text/javascript" src='<c:url value="/js/jquery-1.11.3.min.js" />'></script>
	<script type="text/javascript" src='<c:url value="/ui/bootstrap-sweetalert/lib/sweet-alert.min.js" />'></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#valorElegido").focus();

		});
	</script>
</head>
<body>
	
	<jsp:include page="partida_header2.jsp"></jsp:include>
	
	<div class="col-md-3">
		<jsp:include page="partida_intentos.jsp"></jsp:include>
	</div>

	<div class="col-md-6">
		<c:url value="/partida/procesarIntento.do" var="x"></c:url>

		<form:form method="POST" commandName="intento" action="${x}">
			<form:label path="valorElegido">
				<fmt:message key="partida.label.valorElegido" />
			</form:label><br/>
			<form:input path="valorElegido" />
			<form:errors path="valorElegido" cssStyle="color: red" /><br>
			<form:button>Intentar!</form:button>
		</form:form>
	</div>
	
	<div class="col-md-3">
		<jsp:include page="partida_estadisticas.jsp"></jsp:include>
	</div>

	<c:if test="${partida.getNumeroADescubrir()==0}">
		<script type="text/javascript">
		$(document).ready(function(){
			  setTimeout(function() {
			    swal({
			      title: "Ganaste!",
			      text: "en ${partida.getIntentos().size()} intentos! ",
			      type: "success",
			      confirmButtonText: "Ok"
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
			      title: "Deci alpiste!",
			      text: "el numero era ${partida.getNumeroADescubrir()} ",
			      type: "error",
			      confirmButtonText: "Ok"
			    });
			 }, 500);
			});
		</script>
	</c:if>	
</body>
</html>
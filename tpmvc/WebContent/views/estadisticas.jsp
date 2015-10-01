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
	<h2>Estadisticas e</h2>
	<c:forEach var="cookies" items="${cookie}">
		
			<li><c:out value="${cookies.value.name}" />-<c:out value="${cookies.value.value}" /></li>
		
	</c:forEach>
	
	<%
		Cookie[] cookies = request.getCookies();
		if (cookies!=null) {
			for(int i=0; i<cookies.length;i++){
				out.println("<br/> - "+cookies[i].getName()+ " - "+cookies[i].getValue());			
			}
		}
	%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1 class="text-center">${mensaje}</h1>

<h1>id cursoooo ${curso2}</h1>
<h1>Cantidad de agendas ${agendas2size}</h1>


<c:forEach items="${agendas2}" var="a">

<h1>id agenda ${a}</h1>


</c:forEach>


</body>
</html>
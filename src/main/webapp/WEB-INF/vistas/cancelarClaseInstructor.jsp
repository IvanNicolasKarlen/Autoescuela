<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>Cancelación de clase</title>
	<!-- meta, css, vendor, etc. -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>
<%@ include file="../../parts/meta.jsp" %> 
	<!-- fin del meta, css, vendor, etc -->
</head>
<body class="animsition">

	<!-- Header -->
<%@ include file="../../parts/header.jsp" %> 
	<!-- fin header -->
	<!-- Sidebar -->
<%@ include file="../../parts/sidebar.jsp" %> 
	<!-- fin sidebar -->
	
	
	<section class="section-welcome bg1-pattern p-t-120 p-b-105">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 p-b-30">
					<div class="wrap-text-welcome t-center">
		<h5 class="tit3 text-center">Seleccione el motivo</h5><br><br>
		${mensaje}
	
<form  method="GET" action="${context}/cancelacionDeAgenda">

	<input name="idAgenda" value="${idAgenda}" style="display:none">
		<h4>Estado de la clase</h4><br>
		<select name="idEstadoAgenda">
			<c:forEach items="${estadosDeAgenda}" var="la">
				<option value="${la.id}"> ${la.estado} </option>
			</c:forEach>
		</select><br><br><br>
		
		
	<!-- 		<h4>Detalle de cancelación</h4><br>
		<select name="idDetalle">
			<c:forEach items="${detalleDeAgenda}" var="la">
				<option value="${la.id}"> ${la.detalle} </option>
			</c:forEach>
		</select><br><br><br> -->
		
		
		<!-- <h4>Estado del vehiculo</h4><br>
		<select name="idEstadoDeVehiculo" >			
			<c:forEach items="${estadoDeVehiculo}" var="ev">
				<option value="${ev.id}"> ${ev.estadoActual} </option>
			</c:forEach>
		</select><br><br> -->
		
	<input name="confir" value="${confirmacion}" style="display:none">	
	
		<button class="btn btn-primary" type="submit">Aceptar</button>
	</form>
		
<br><br>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	<!-- Footer -->
<%@ include file="../../parts/footer.jsp" %> 
	<!-- fin footer  -->
</body>
</html>
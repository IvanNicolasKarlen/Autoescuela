<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>Home</title>
	<!-- meta, css, vendor, etc. -->
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
				<div class="col-md-6 p-t-45 p-b-30">
					<div class="wrap-text-welcome t-center">
	

	<table class="table table-hover text-center mt-4" border="1" cellpadding="1" cellspacing="0">
		<h4><b>Actualmente tiene estos alumnos a cargo:<br></h4>
			<thead>
				<tr>
					<th class="enc">Día</th>
					<th class="enc">Horario</th>
					<th class="enc">Nombre</th>
					<th class="enc">Apellido</th>
				</tr>
			</thead>
				
		<tbody>
			<c:forEach items="${listaAgenda}" var="variable" >
					<tr>
						<td class="alt-celda"><h3>${variable.fecha}</h3></td>
						<td class="alt-celda"><h3>${variable.hora}</h3></td>
					</tr>
			</c:forEach>
			
			<c:forEach items="${listaAlumno}" var="variable" >
					<tr>
						<td class="alt-celda"><h2>${variable.nombre}</h2></td>
						<td class="alt-celda"><h2>${variable.apellido}</h2></td>
					</tr>
			</c:forEach>
			
		</tbody>
	</table>
						
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
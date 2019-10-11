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
	

	<center><table class="table table-hover text-center mt-4" border="1" cellpadding="1" cellspacing="0">
		<h4><b>Actualmente tiene estos alumnos a su cargo:<br></h4>
			<thead>
				<tr>
					<th class="enc">Día</th>
					<th class="enc">Horario</th>
					<th class="enc">Nombre</th>
					<th class="enc">Apellido</th>
				</tr>
			</thead>
				
			<tbody>
			<c:forEach items="${listaAgenda}" var="verFechas" >
					<tr>
						<td class="alt-celda"><h3>${verFechas.fecha}</h3></td>
						<td class="alt-celda"><h3>${verFechas.hora}</h3></td>	
						<td class="alt-celda"><h3>${verFechas.inscripcion.alumno.usuario.nombre}</h3></td>
						<td class="alt-celda"><h3>${verFechas.inscripcion.alumno.usuario.apellido}</h3></td>				
					</tr>
			</c:forEach>	
			</tbody>
			</table></center>	
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
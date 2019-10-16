<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
	

	
					<!-- Cuenta la cantidad de alumnos a cargo del instructor. -->
		
   <!-- ******************************MUESTRA CANTIDAD DE FECHAS QUE TIENE TRABAJO****************************** -->	
   			
   <body>
      <c:set var = "string1" value = "${listaAgenda}"/>
      <p><b><h5>· Usted tiene esta cantidad de fechas para dar clases: ${fn:length(string1)}</h5></b></p><br>
   </body>
   
   <!-- ***************************************MUESTRA CANTIDAD DE ALUMNOS SIN REPETIR************************** -->
   
 <table class="table table-hover text-center mt-4" border="1" cellpadding="1" cellspacing="0">
   <body>
      <c:set var = "string1" value = "${traerAlumnos}"/>
      <p><b><h5>· Usted tiene esta cantidad de alumnos inscriptos: ${fn:length(string1)}</h5></b></p>
   </body>
   
  
   <!-- **********************************MUESTRA NOMBRE Y FECHA *********************************************** -->
   <table class="table table-hover text-center mt-4" border="1" cellpadding="1" cellspacing="0">
			<thead>
				<tr><center>
					<th class="enc">Día</th>
					<th class="enc">Horario</th>
					<th class="enc">Nombre</th>
					<th class="enc">Apellido</th></center>
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
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
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
	




	<!-- ******************************BUSCA NOMBRE Y APELLIDO****************************** -->
<form class="form-inline" method="GET"  action="buscadorDeAlumnos">
        <div class="container">
                  <div class="form-group mb-2">
                        	<label for="nombre" class="col-sm-2 col-md-3 control-label"><b>Nombre:</b></label>
                        <div class="col-sm-10 col-md-9">
                            <input type="text" name="nombre" id="nombre" class="form-control">
                        </div>
                    </div><br>
                  
                   <div class="form-group mb-2">
                       	    <label for="apellido" class="col-sm-2 col-md-3 control-label"><b>Apellido:</b></label>
                        <div class="col-sm-10 col-md-9">
                            <input type="text" name="apellido" id="apellido" class="form-control">
                        </div>
                   </div><br>
                     
					<button class="btn btn-primary" type="submit" id="btnBuscar">Buscar <i class="fa fa-search"></i></button>
</div>
</form><br><br>


		
   <!-- ******************************MUESTRA CANTIDAD DE FECHAS QUE TIENE TRABAJO****************************** -->	
   	 
   <body>
      <c:set var = "string1" value = "${listaAgenda}"/>
      	<div class="w3-right-align">		
      <p><b><h5>· Usted tiene esta cantidad de fechas para dar clases: ${fn:length(string1)}</h5></b></p><br>
       </div>
   </body>
  
   
   <!-- ***************************************MUESTRA CANTIDAD DE ALUMNOS SIN REPETIR************************** -->
   
   <body>
      <c:set var = "string1" value = "${traerAlumnos}"/>
      <p><b><h5>· Usted tiene esta cantidad de alumnos inscriptos: ${fn:length(string1)}</h5></b></p>
   </body>
   
  
  
  
  
  
   <!-- **********************************MUESTRA NOMBRE Y FECHA *********************************************** -->

<table class="table table-hover text-center mt-4" border="3" cellpadding="1" cellspacing="0">
			<thead>
				<tr class="w3-red">
					<th><center>Día</center></th>
					<th><center>Horario</center></th>
					<th><center>Nombre</center></th>
					<th><center>Apellido</center></th>
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
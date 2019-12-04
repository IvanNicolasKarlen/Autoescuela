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
				<div class="col-lg-12 p-b-30">
					<div class="t-center">
					<div class="wrap-text-welcome t-center">
	

	<!-- ******************************BUSCA NOMBRE Y APELLIDO****************************** -->

  <form:form method="GET"  action="buscadorDeAlumnos"> 
<h3 class="tit3 text-center">Buscador de alumnos</h3><br><br>
 
 	<div class="container">
	<b class="tit2 text-center m-t-90">Nombre de usuario:</b><label for="dni" ></label>
            <input type="text" name="nombreDeUsuario" id="nombreDeUsuario" class="form-control">
 </div><br><br>
 

		<button class="btn btn-primary" type="submit" name="btnBuscar">Buscar <i class="fa fa-search"></i></button>
			
</form:form><br><br>


<!-- ********************************************************************************************************************* -->

<form class="form-inline" method="GET"  action="buscadorDeAlumnos">
<table class="table table-hover text-center mt-4" border="3" cellpadding="1" cellspacing="0">
         <tbody>
         ${mensaje}
<c:if test="${ocultar == 'mensaje' }">		

           	<thead>       
				<tr class="w3-red">
					<th class="enc"><center>Nombre</center></th>
					<th class="enc"><center>Apellido</center></th>	
					<th class="enc"><center>Dni</center></th>	
					<th class="enc"><center>Fecha</center></th>	
					<th class="enc"><center>Hora</center></th>	
					<th class="enc"><center>Cancelación</center></th>	
				</tr>
			</thead>
</c:if>
               <c:forEach items="${buscarAlumnos}" var="verFechas" >
					<tr>
						<td class="alt-celda"><h3>${verFechas.inscripcion.alumno.usuario.nombre}</h3></td>				
						<td class="alt-celda"><h3>${verFechas.inscripcion.alumno.usuario.apellido}</h3></td>
						<td class="alt-celda"><h3>${verFechas.inscripcion.alumno.usuario.dni}</h3></td>		
						<td class="alt-celda"><h3>${verFechas.fecha}</h3></td>
						<td class="alt-celda"><h3>${verFechas.hora}</h3></td>	
						<td><a href="seleccionarMotivo/${verFechas.id}">Cancelar</a></td>						
					</tr>
			</c:forEach>	
			
        </tbody>
   </table>  
  </form>
<br><br>

   <!-- ******************************MUESTRA CANTIDAD DE FECHAS QUE TIENE TRABAJO****************************** -->	

<c:if test="${ocultar == 'mensaje'}">		
      <c:set var = "string1" value = "${listaAgenda}"/>	
      <p><b><h5>· Usted tiene en total ${fn:length(string1)} fechas para dar clases</h5></b></p><br>
</c:if>

   
   <!-- ***************************************MUESTRA CANTIDAD DE ALUMNOS SIN REPETIR************************** -->
<c:if test="${ocultar == 'mensaje'}">		
      <c:set var = "string1" value = "${traerAlumnos}"/>
      <p><b><h5>· Usted tiene en total ${fn:length(string1)} alumnos inscriptos</h5></b></p>
</c:if>
    
					</div>	
				</div>
			</div>
		</div>	</div>
</section>
	

	<!-- Footer -->
<%@ include file="../../parts/footer.jsp" %> 
	<!-- fin footer  -->
</body>
</html>
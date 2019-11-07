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


<form method="GET"  action="buscadorDeAlumnos">
  
        <div class="container">
         
<table class="table table-hover text-center mt-4" border="3" cellpadding="1" cellspacing="0">
			<thead>
				<tr class="w3-red"><center>
					<th class="enc"><center>Nombre</center></th>
					<th class="enc"><center>Apellido</center></th>	
				</tr>
			</thead>
         
         <tbody>
               <c:forEach items="${buscarAlumnos}" var="verFechas" >
					<tr>
						<td class="alt-celda"><h3>${verFechas.usuario.nombre}</h3></td>				
						<td class="alt-celda"><h3>${verFechas.usuario.apellido}</h3></td>
					</tr>
			</c:forEach>	
        </tbody>
   </table>     

        </form>

 <a href="AlumnosDelInstructor" class="btn btn-primary">Salir</a>

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
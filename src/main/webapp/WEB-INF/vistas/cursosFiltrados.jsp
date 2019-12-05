<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
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


	<!-- Our Story -->
	<section class="bg1-pattern p-t-120 p-b-105">
		
		
<c:if test="${num == 0 }">		
		
		<div class="container">
		<div class="tit2 text-center m-t-80">
			 No estas realizando ningun curso en este momento
		</div>
<a href="listadoCursos" class="btn3 flex-c-m size13 text-center txt11 trans-0-4 m-l-r-auto m-t-20">
					Anotarme a un curso
				</a>
	<!--	<h3 class="tit3 t-center m-b-35 m-t-5">
			Seccion Principal
		</h3>
-->
		
					<p class="t-center m-b-22 size3 m-l-r-auto m-b-70 m-t-15">
					¿Que estás esperando? Anotate y aprendé a manejar junto a nuestros instructores.
						</p>
		
		
		
</c:if>
		
		
			<!-- Delicious -->
			<div class="row">
				<div class="col-md-12 p-t-45 p-b-30 ">
					<div class="wrap-text-welcome t-center">
						
<c:if test="${num > 0 }">

						<h3 class="tit3 text-center m-b-35 m-t-5">
							Calendario de Clases
						</h3>
		
	<h1> Filtros ${tamlistadoDeFiltros} </h1>
  	<h1> Clases ${tamlistadoDeClases} </h1>
		
		
	<form:form action="cursosFiltrados" moddelAtribute="curso" method="post">	
	<c:forEach items="${listadoDeFiltros}" var="la">

<label class="checkbox-inline">
  <input type="checkbox" id="checkboxEnLinea1" value="opcion_1"> ${la.curso.especialidad.tipo}
</label>
</c:forEach>	
	
		<button type="button" class="btn3 flex-c-m txt11 trans-0-4 m-l-r-auto btn-sm">
          <span class="glyphicon glyphicon-pencil"> </span> Buscar 
        </button>
</form:form>		
		
		
		
<div class="row">
<c:forEach items="${listadoDeClases}" var="la">


<div class="col-md-4 ">

<input name="idAgendas[${la.id}]" type="text"  value="${la.id}"/>

 <c:set var="i" value="${ i+1}"/>
		 <h5 class="card-subtitle p-t-10 mb-2 text-center mb-2 bg-primary text-white">Nro <c:out value="${i}"/></h5>
		          <h2 class="text-center color0-hov trans-0-4 bg-primary text-white">Curso de ${la.inscripcion.curso.especialidad.tipo}</h2>
		
		 <p class="card-text text-center"><b class="color0-hov trans-0-4">Fecha</b>: ${la.fecha}<br>
	<b class="color0-hov trans-0-4 text-center">Hora:</b> ${la.hora}<br>
		
		<b class="color0-hov trans-0-4 text-center">Instructor:</b> ${la.instructorVehiculoEspecialidad.instructor.usuario.nombre} ${la.instructorVehiculoEspecialidad.instructor.usuario.apellido}<br>
		
		<b class="color0-hov trans-0-4 text-center">Vehiculo:</b> ${la.instructorVehiculoEspecialidad.vehiculo.modelo} ${la.instructorVehiculoEspecialidad.vehiculo.patente}</p>
		
		
		<br>
<button type="button" class="btn3 flex-c-m txt11 trans-0-4 m-l-r-auto btn-sm">
          <span class="glyphicon glyphicon-pencil"> </span> EDITAR 
        </button>
		
</div>

<a href="listadoFechas" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					Ver Todas Las Clases Juntas
  </a>

</c:forEach>
</div>	
</c:if>
						
						
						
						
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
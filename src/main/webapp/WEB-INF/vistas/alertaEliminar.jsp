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
	<!-- Header -->
<%@ include file="../../parts/header.jsp" %> 
	<!-- fin header -->
	<!-- Sidebar -->
<%@ include file="../../parts/sidebar.jsp" %> 
	<!-- fin sidebar -->

	</head>
	
	<body>
	
	
	
	<!-- Welcome -->
	<section class="section-welcome bg1-pattern p-t-120 p-b-105 m-t-50">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="wrap-text-welcome t-center">
						

						<h3 class="tit3 t-center m-b-35 m-t-5">
							${mensaje}
						</h3>

<c:if test="${bandera == 1 }">								
<h4 class="tit2 t-center m-b-35 m-t-5">
							Quiero eliminar todas mis clases del curso de ${nombreEspecialidad.curso.especialidad.tipo}
						</h4>
						
						<p class="text-center">Esta acción generará que se te eliminen todas las clases de este curso sin finalizarlo.</p>
<div class="btn-group m-t-40">

<a href="listadoFechas" class="btn3 flex-c-m size13 txt11  trans-0-4 m-l-r-auto ">
					Cancelar
</a>

<form:form  method="POST" modelAttribute="agenda" action="eliminarClase">

	<input name="idCurso" type="hidden" value="${nombreEspecialidad.curso.id}"></input>
	 
  <button type="submit" class="btn3 flex-c-m size13 txt11  trans-0-4 bg-danger text-white m-l-r-auto">
					Aceptar
	</button>
	
	
</form:form> 
</div>
</c:if>



<c:if test="${bandera == 2 }">								
	<div class="row">



  		<h2 class=" col-md-4 m-l-r-auto text-center color0-hov trans-0-4  bg-danger text-white">   Curso de ${agenda.inscripcion.curso.especialidad.tipo}   </h2>
		
			<div class="col-md-12 ">
		
	<p class="card-text text-center m-t-8"><b class="color0-hov trans-0-4">Fecha</b>: ${agenda.fecha}<br>
	
	<c:if test="${agenda.hora < 1000 }">
	
	<b class="color0-hov trans-0-4 text-center">Hora:</b> ${agenda.hora.toString().substring(0,1)}:${agenda.hora.toString().substring(1,3)}<br>
	
	</c:if>
   
   <c:if test="${agenda.hora >= 1000 }">
  		
  		 <b class="color0-hov trans-0-4 text-center">Hora:</b> ${agenda.hora.toString().substring(0,2)}:${agenda.hora.toString().substring(2,3)}${agenda.hora.toString().substring(2,3)}<br>
	</c:if>
	<b class="color0-hov trans-0-4 text-center m-t-8">Instructor:</b> ${agenda.instructorVehiculoEspecialidad.instructor.usuario.nombre} ${agenda.instructorVehiculoEspecialidad.instructor.usuario.apellido}<br>
		
	<b class="color0-hov trans-0-4 text-center m-t-8">Vehiculo:</b> ${agenda.instructorVehiculoEspecialidad.vehiculo.modelo} ${agenda.instructorVehiculoEspecialidad.vehiculo.patente}</p>
       
 			</div>
      							
 </div>	
 
 						
<div class="btn-group m-t-40">

<a href="listadoFechas" class="btn3 flex-c-m size13 txt11  trans-0-4 m-l-r-auto ">
					Cancelar
</a>




<form:form  method="POST" modelAttribute="agenda" action="eliminarClase">

	<input name="idAgendaSeleccionada" type="hidden" value="${agenda.id}"></input>
	 
  <button type="submit" class="btn3 flex-c-m size13 txt11  trans-0-4 bg-danger text-white m-l-r-auto">
					Aceptar
	</button>
	
	
</form:form> 

</div>
						
</c:if>						
						

						
						
						

<c:if test="${bandera == 3 }">								
<h4 class="tit2 t-center m-b-35 m-t-5">
							Quiero finalizar mi curso de ${inscripcion.curso.especialidad.tipo} 
						
						<p class="text-center">Esta acción generará que se te eliminen todas las clases de este curso.</p>
<div class="btn-group m-t-40">

<a href="listadoFechas" class="btn3 flex-c-m size13 txt11  trans-0-4 m-l-r-auto ">
					Cancelar
</a>

<form:form  method="POST" modelAttribute="agenda" action="finalizado">

	<input name="idCurso" type="hidden" value="${inscripcion.curso.id}"></input>
	 
  <button type="submit" class="btn3 flex-c-m size13 txt11  trans-0-4 bg-danger text-white m-l-r-auto">
					Aceptar
	</button>
	
	
</form:form> 
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
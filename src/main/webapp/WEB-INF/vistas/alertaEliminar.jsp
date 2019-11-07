<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
							Quiero desincribirme de mi curso de ${nombreEspecialidad.curso.especialidad.tipo} ${nombreEspecialidad.curso.id}
						</h4>
						
						<p class="text-center">Esta acción generará que se te eliminen todas las clases de este curso.</p>
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
	
	<b class="color0-hov trans-0-4 text-center m-t-8">Hora:</b> ${agenda.hora}<br>
		
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

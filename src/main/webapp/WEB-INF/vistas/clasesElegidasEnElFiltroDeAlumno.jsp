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
<body class="animsition">

	


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
							Mis cursos
						</h3>
		
	
  		
	
	
	<p class="text-center">Eliminar todas juntas mis clases de: </p>
	<c:forEach items="${listaCursos}" var="la">

		
<form:form  method="POST" modelAttribute="agenda" action="mostrarAlerta">
<label class="checkbox-inline">
${la.curso.especialidad.tipo}
</label>


<input name="idEspecialidad" type="hidden" value="${la.curso.especialidad.id}"></input>
    
    <button type="submit" class="btn3 flex-c-m txt11 trans-0-4 m-l-r-auto btn-sm m-b-20 ">
          Eliminar 
    </button>
        
</form:form>      
    </c:forEach>	
	












						<h3 class="tit3 text-center m-b-35 m-t-5">
							Calendario de Clases
						</h3>
		
		
			
	<form:form action="clasesDelCurso" moddelAtribute="curso" method="post">
	<p class="text-center">Solo ver mis clases de: </p>	
	<c:forEach items="${listaCursos}" var="la">

		
<label class="checkbox-inline">

  <input type="radio"  class="m-l-15" name="id" value="${la.curso.especialidad.id}"/> ${la.curso.especialidad.tipo}
  </label>
</c:forEach>	
	
		<button type="submit" class="btn3 flex-c-m txt11 trans-0-4 m-l-r-auto btn-sm m-b-20">
          <span class="glyphicon glyphicon-pencil"> </span> Buscar 
        </button class="m-b-40">
</form:form>		
		
		<h4 class="t-center text-danger">${error}</h4>
		<c:set var = "i" value = "0" />
<div class="row">
<c:forEach items="${listadoDeClases}" var="la">


<div class="col-md-4 ">



		          <h2 class="text-center color0-hov trans-0-4 bg-primary text-white">Curso de ${la.inscripcion.curso.especialidad.tipo}</h2>
		<c:set var="i" value="${ i+1}"/>
		 <h5 class="card-subtitle p-t-10 mb-2 text-center text-info">Clase <c:out value="${i}"/></h5>
	
		 <p class="card-text text-center"><b class="color0-hov trans-0-4">Fecha</b>: ${la.fecha}<br>
	<c:if test="${la.hora < 1000 }">
	
	<b class="color0-hov trans-0-4 text-center">Hora:</b> ${la.hora.toString().substring(0,1)}:${la.hora.toString().substring(1,3)}<br>
	
	</c:if>
   
   <c:if test="${la.hora >= 1000 }">
  		
  		 <b class="color0-hov trans-0-4 text-center">Hora:</b> ${la.hora.toString().substring(0,2)}:${la.hora.toString().substring(2,3)}${la.hora.toString().substring(2,3)}<br>
	
	</c:if>	
		<b class="color0-hov trans-0-4 text-center">Instructor:</b> ${la.instructorVehiculoEspecialidad.instructor.usuario.nombre} ${la.instructorVehiculoEspecialidad.instructor.usuario.apellido}<br>
		
		<b class="color0-hov trans-0-4 text-center">Vehiculo:</b> ${la.instructorVehiculoEspecialidad.vehiculo.modelo} ${la.instructorVehiculoEspecialidad.vehiculo.patente}</p>
		
		<c:if test="${la.estadoDeAgenda.estado == 'Ocupada' }">
		<b class="color0-hov trans-0-4 text-center text-primary">Estado de la clase:</b> Aún no cursó</p>
		</c:if>
		
		<c:if test="${la.estadoDeAgenda.estado == 'Finalizado' }">
		<b class="color0-hov trans-0-4 text-center text-success">Estado de la clase:</b> Clase realizada</p>
		</c:if>
		<c:if test="${(la.estadoDeAgenda.estado != 'Finalizado') && (la.estadoDeAgenda.estado != 'Ocupada') }">
		<b class="color0-hov trans-0-4 text-center text-danger">Estado de la clase:</b> ${la.estadoDeAgenda.estado}</p>
		</c:if>
		
		
		
		
		
<c:if test="${(la.estadoDeAgenda.id == 2) }">	
								  <!-- BOTON ELIMINAR -->
     <br> 
<form:form  method="POST" modelAttribute="agenda" action="mostrarAlerta">

			<input name="idAgendaSeleccionada" type="hidden" value="${la.id}"></input>
		
	   <button type="submit" class="btn3 flex-c-m txt11 trans-0-4 m-l-r-auto btn-sm">
         ELIMINAR 
        </button>
	
	
</form:form> 
								<!--FIN  BOTON ELIMINAR -->
		</c:if>
		
</div>





<c:set var="cantClasesPracticas" value="${la.inscripcion.curso.cantClasesPracticas}"/>
<c:set var="cantDeClasesCursa" value="${cantDeClasesCursando}"/>

<c:set var="Anulado" value="Anulado"/>



</c:forEach>
<c:if test="${botonFinalizarAnulado != Anulado}">
<c:if test="${cantClasesPracticas > cantDeClasesCursa}">
	<form method="POST" modelAttribute="agendasViewModel" action="seleccionarClaseAgregar">
	
	<c:forEach items="${listadoDeClases}" var="la">
	
			<input name="cantClasesPracticas" type="hidden" value="${la.inscripcion.curso.cantClasesPracticas}"></input>
			<input name="cantDeClasesCursando" type="hidden" value="${cantDeClasesCursando}"></input>
			
			
		
			
			<input name="idAgendas[${la.id}]" type="hidden"  value="${la.id}"/>

			<input name="idCurso" type="hidden"  value="${la.inscripcion.curso.id}"/>
			
		  	
		
		</c:forEach>
		<div >
		<br>
		 <button type="submit" class="m-t-20 m-l-30 btn3 flex-c-m txt11 trans-0-4  btn-sm">
	         AGREGAR CLASE+ 
	       </button>
	       </div>	
	</form>	
</c:if>

</c:if>









</div>	

<c:set var="Anulado" value="Anulado"/>

<c:if test="${botonFinalizarAnulado != Anulado}">

<form:form  method="POST" modelAttribute="agenda" action="finalizarCursoAlerta">				

<c:forEach items="${listadoDeClases}" var="la">

<input name="idCurso" type="hidden" value="${la.inscripcion.curso.id}"></input>


</c:forEach>
		<button type="submit" href="mostrarAlerta" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto m-t-35">
		Finalizar Curso
		</button>
						
</form:form>
</c:if>						
					</div>
				</div>

				
			</div>


			
		</div>
	</section>
	
</c:if>	
<!-- Footer -->
<%@ include file="../../parts/footer.jsp" %> 
	<!-- fin footer  -->

</body>
</html>
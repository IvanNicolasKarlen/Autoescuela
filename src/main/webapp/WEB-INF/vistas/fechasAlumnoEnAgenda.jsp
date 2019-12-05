<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="../../parts/meta.jsp" %> 
	<!-- fin del meta, css, vendor, etc -->
</head>
	<!-- Header -->
<%@ include file="../../parts/header.jsp" %> 
	<!-- fin header -->
	<!-- Sidebar -->
<%@ include file="../../parts/sidebar.jsp" %> 
	<!-- fin sidebar -->
<body class="animsition">

	
	
	<!-- Welcome -->
	<section class="section-welcome bg1-pattern p-t-120 p-b-105 m-t-50">	
	
		<h4 class="tit2 t-center m-b-35 m-t-2">
                 <label>${mensaje}</label>
					</h4>
		 
		

<c:set var = "i" value = "0" />

<form:form class="wrap-form-reservation size22 m-l-r-auto" method="POST" modelAttribute="agendasViewModel" action="inscripcion">

<input name="idCurso" path=idCurso type="text" style="display:none" value="${cursoSeleccionado.id}"/>

	
<div class="row">
<c:forEach items="${listaAgendas}" var="la">
<div class="col-md-4 ">

<input name="idAgendas[${la.id}]" type="text"  value="${la.id}" style="display:none"/>

<h2 class="text-center color0-hov trans-0-4 bg-info text-white">Curso de ${la.instructorVehiculoEspecialidad.especialidad.tipo}</h2>
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
		
		
		<br>

		
</div>

</c:forEach>
</div>	
	
	</br>
<div class="card-body">
  <button type="submit" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					ACEPTAR INSCRIPCION
  </button>
  </div>
</form:form>
  		
  	<form:form  method="POST" modelAttribute="agendasViewModel" action="seleccionarAgenda">
	
		<c:forEach items="${listaAgendas}" var="la">
			
			<input name="idAgendas[${la.id}]" type="hidden" value="${la.id}"></input>
			
		</c:forEach>
		<input  name="idCurso" type="hidden" value="${cursoSeleccionado.id}"></input>
	
	<div class="card-body">
	  <button type="submit" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
						MODIFICAR
	  </button>
	</div>
	
</form:form>	
  		
  		
  		<a href="listadoCursos" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					CANCELAR
	</a>
  		
  		
  		
  		
	</section>
	
	
	
<!-- Footer --> 
<%@ include file="../../parts/footer.jsp"%>
	<!-- fin footer  -->

	<!-- Back to top -->
	<div class="btn-back-to-top bg0-hov" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="fa fa-angle-double-up" aria-hidden="true"></i>
		</span>
	</div>

	<!-- Container Selection1 -->
	<div id="dropDownSelect1"></div>



<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/bootstrap/js/popper.js"></script>
	<script type="text/javascript" src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/daterangepicker/moment.min.js"></script>
	<script type="text/javascript" src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/slick-custom.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/parallax100/parallax100.js"></script>
	<script type="text/javascript">
        $('.parallax100').parallax100();
	</script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/lightbox2/js/lightbox.min.js"></script>
<!--===============================================================================================-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAKFWBqlKAGCeS1rMVoaNlwyayu0e0YRes"></script>
	<script src="js/map-custom.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>
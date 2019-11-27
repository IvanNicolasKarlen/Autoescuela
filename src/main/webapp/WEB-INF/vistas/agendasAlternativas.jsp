<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page session="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="en">
<head>
	<title>Contact</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- ============================================================================================== -->
	
<!--===============================================================================================-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="images/icons/favicon.png"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/themify/themify-icons.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/lightbox2/css/lightbox.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->


</head>
<body class="animsition">

	<!-- Header -->
<%@ include file="../../parts/header.jsp" %> 
	<!-- fin header -->
	<!-- Sidebar -->
<%@ include file="../../parts/sidebar.jsp" %> 
	<!-- fin sidebar -->
	
	<!-- Welcome -->
	<section class="section-welcome bg1-pattern p-t-120 p-b-105 m-t-50">	
	
		<h4 class="tit2 t-center m-b-35 m-t-2">
                 <label>${mensaje}</label>
					</h4>
							 
		
<c:set var = "i" value = "0" ></c:set>


<form:form  method="POST" modelAttribute="agendasViewModel" action="modificarAgenda">

<input  name="idCurso"  type="hidden" value="${cursoSeleccionado.id}"></input>
<input  name="idAgendaEditar" type="hidden"  value="${agen}"></input>



<div class="row">
	<c:forEach items="${agendasAlternativas}" var="al">
		<div class="col-md-4 ">
		
		 <c:set var="i" value="${ i+1}"/>
		 		<h2 class="text-center color0-hov trans-0-4 bg-info text-white">Clase nro <c:out value="${i}"/> </h2>
		 
				<p class="card-text text-center"><b class="color0-hov trans-0-4">Fecha</b>: ${al.fecha}<br>
			<c:if test="${al.hora < 1000 }">
	
	<b class="color0-hov trans-0-4 text-center">Hora:</b> ${al.hora.toString().substring(0,1)}:${al.hora.toString().substring(1,3)}<br>
	
	</c:if>
   
   <c:if test="${al.hora >= 1000 }">
  		
  		  	 <b class="color0-hov trans-0-4 text-center">Hora:</b> ${al.hora.toString().substring(0,2)}:${al.hora.toString().substring(2,3)}${al.hora.toString().substring(2,3)}<br>
	</c:if>
   
		
		<b class="color0-hov trans-0-4 text-center">Instructor:</b> ${al.instructorVehiculoEspecialidad.instructor.usuario.nombre} ${al.instructorVehiculoEspecialidad.instructor.usuario.apellido}<br>
		
		<b class="color0-hov trans-0-4 text-center">Vehiculo:</b> ${al.instructorVehiculoEspecialidad.vehiculo.modelo} ${al.instructorVehiculoEspecialidad.vehiculo.patente}</p>
		<br> 
				<!-- editar -->  
		
		<div class="card-body">
			  <input type="radio" name="idAgendaSeleccionada" value="${al.id}">
								ELEGIR
			  </input>
		 </div>
		
</div>
</c:forEach>


<c:forEach items="${listaAgendas}" var="id">
			<div class="col-md-4 ">
			<input name="idAgendas[${id}]" type="hidden" value="${id}"></input>
			</div>
</c:forEach>



</div>	

	</br>
	</br>
<div class="card-body">
  <button type="submit" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					MODIFICAR
  </button>
  </div>
	
	
  		
  		<a href="listadoCursos" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					CANCELAR
	</a>
  		
  		</form:form>
  		
  		
  		
  		  		
  		
  		
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

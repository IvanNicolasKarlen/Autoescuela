<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>
	<title>Confirmarr</title>
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
	
	<!-- Welcome -->
	<section class="section-welcome bg1-pattern p-t-120 p-b-105 m-t-50">

	<!-- En este h4 se muestra el error si es que hay alguno -->
		<section class="section-reservation bg1-pattern p-t-100 p-b-113">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 p-b-30">
						<div class="t-center">

		<h2 class="t-center text-danger">${confirmacion}</h2><br><br>
<center>
<form  method="GET" action="${context}/cancelacionDeAgenda">

	<input name="idAgenda" value="${idAgenda}" style="display:none">
	<input name="idEstadoAgenda" value="${idEstadoAgenda}" style="display:none">	
	<input name="idEstadoDeVehiculo" value="${estadoId}" style="display:none">	
<input name="idV" value="${idV}" style="display:none">		
	<input value="si" style="display:none">	
			
		
		<button name="confir" class="btn3 flex-c-m size13 txt11 trans-0-4" value="si">Si, cancelar</button><br>
		<button name="confir" class="btn3 flex-c-m size13 txt11 trans-0-4" value="no">No cancelar</button>
			
			

	
</form></center>

			
						</div>
					</div>
				</div>
			</div>
		</section> 
	</section>



<!-- Footer -->
	<%@ include file="../../parts/footer.jsp"%>
	<!-- fin footer  -->
</body>
</html>
<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<head>
	<title>Home</title>
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
		<h4 class="t-center text-danger">${mensaje}</h4>
		<h4 class="t-center text-danger">${error}</h4>
		<section class="section-reservation bg1-pattern p-t-100 p-b-113">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 p-b-30">
                <div class="t-center">

					<form action="seleccionarVehiculoParaInstructor" method="post">
						<label class="txt9"> Seleccione Especialidad para la que desea agregar el Vehiculo: </label> 
						<select class="bo-rad-10 sizefull txt10 p-l-20" id="eps"
							name="eps">

							<c:forEach items="${listaEspecialidades}" var="les">
								<option value="${les.id}">${les.tipo}</option>
							</c:forEach>

						</select> 
						<input style="display:none" name="idInstructor" value="${ins.id}"> 
						<div class="wrap-btn-booking flex-c-m m-t-6">
							<button type="submit"
								class=" m-t-50 btn3 flex-c-m size13 txt11 trans-0-4">
								Continuar</button>
						</div>
					</form>

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


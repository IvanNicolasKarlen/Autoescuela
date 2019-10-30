<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>
<title>Agregar Vehiculo - Instructor</title>
<!-- meta, css, vendor, etc. -->
<%@ include file="../../parts/meta.jsp"%>
<!-- fin del meta, css, vendor, etc -->
</head>
<body class="animsition">

	<!-- Header -->
	<%@ include file="../../parts/header.jsp"%>
	<!-- fin header -->
	<!-- Sidebar -->
	<%@ include file="../../parts/sidebar.jsp"%>

	<section class="section-reservation bg1-pattern p-t-100 p-b-113">
	<div class="container">
		<div class="row">
		<h3>${mensaje}</h3>
					<h3>${error}</h3>
			<div class="col-lg-12 p-b-30">
				<div class="t-center">
					<hr>
					<hr>
					<form action="${context}/agregarIVE" method="post">
						
						<select name="idVehiculo">
						<c:forEach items="${listaVehiculos}" var="lv">
							<option value="${lv.id}">${lv.modelo} - ${lv.patente}</option>
							</c:forEach>
						</select>
						
						<input style="display:none" value="${especialidadId}" name="especialidadId">
						<input style="display:none" value="${idInstructor}" name="idInstructor">
						<div class="wrap-btn-booking flex-c-m m-t-6">
							<button type="submit"
								class=" m-t-50 btn3 flex-c-m size13 txt11 trans-0-4">
								Seleccionar y guardar</button>
						</div>
					</form>
				</div>
				<div class="t-center">
					
				
				</div>
			</div>
		</div>
	</div>
	</section>

	<!-- Footer -->
	<%@ include file="../../parts/footer.jsp"%>
	<!-- fin footer  -->
</body>
</html>
</body>
</html>
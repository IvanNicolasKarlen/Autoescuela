<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>
<title>Agregar Tipo de Vehiculo</title>
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
	<!-- fin sidebar -->



	<!-- En este h4 se muestra el error si es que hay alguno -->
	<h4 class="t-center text-danger">${mensaje}</h4>
	<h4 class="t-center text-danger">${error}</h4>
	<section class="section-reservation bg1-pattern p-t-100 p-b-113">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 p-b-30">
				<div class="t-center">
					<h1>Agregar Especialidad</h1>
					<hr/>
					<form method="get" action="AgregarEspecialidad">
						<label class="txt9"> Nombre de Especialidad: </label>
						<input type="text" name="tipo" id="tipo"></input>
						<div class="wrap-btn-booking flex-c-m m-t-6">
							<input type="submit"></input>
						</div>
					</form>
					<a href="index">Volver a Inicio</a>
				</div>
			</div>
		</div>
	</div>
	</section> <!-- Footer --> <%@ include file="../../parts/footer.jsp"%>
	<!-- fin footer  -->
</body>
</html>


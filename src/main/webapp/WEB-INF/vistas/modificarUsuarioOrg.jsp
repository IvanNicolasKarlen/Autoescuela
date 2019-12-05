<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<c:set var="context" value="${pageContext.request.contextPath}">
</c:set>
<title>Datos de Usuarios</title>
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
	<section class="bg-title-page flex-c-m p-t-60 p-b-10 p-l-15 p-r-15" style="background-image: url(${context}/images/index.jpg);">
	</section>
	<div class="container">
		<div class="t-center">
			<h2>Datos de Usuario</h2>
			<p>Nombre: ${usuarioBuscado.nombre}</p>
			<p>Apellido: ${usuarioBuscado.apellido}</p>
			<p>Nombre de Usuario: ${usuarioBuscado.nombreDeUsuario}</p>
			<p>DNI: ${usuarioBuscado.dni}</p>
			<c:if test="${usuarioBuscado.rol=='Instructor'}">
			<form method="post" action="${context}/agregarVehiculoEspecialidad">
				<input style="display:none" name="idIns" value="${usuarioBuscado.instructor.id}" />
				<button type="submit" class="label-gallery txt26 trans-0-4">Agregar Vehiculo y Especialidad</button>
			</form>
			</c:if>
		</div>
	
	
	</div>
	<!-- Footer -->
	<%@ include file="../../parts/footer.jsp"%>
	<!-- fin footer  -->
	<script src="${context}/js/mainCande.js"></script>
</body>
</html>
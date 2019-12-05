<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<c:set var="context" value="${pageContext.request.contextPath}">
</c:set>
<title>Buscar Usuarios</title>
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

	<div class="container">

		<div class="t-center">
			<h3 class="t3">Búsqueda de Usuarios</h3>
		</div>
		<div class="section-signup bg1-pattern p-t-85 p-b-85">
			<form class="row justify-content-center" method="GET" action="${context}/busquedaUsuarios">
				<div class="col-md-1"></div>
				<div class="size17 col-md-3">
					<label>Buscar por...</label>
					<div class="wrap-inputtime size12 bo2 bo-rad-10 m-t-3 m-b-23">
						<!-- Select2 -->
						<select class="selection-1" name="buscarPor" id="buscarPor">
							<option value="">Seleccione uno...</option>
							<option value="Nombre">Nombre</option>
							<option value="Apellido">Apellido</option>
							<option value="dni">DNI</option>
							<option value="nombreUsuario">Nombre de usuario</option>
						</select>
					</div>
				</div>
				<div class="size17 col-md-4">
					<label>Ingrese su busqueda:</label>
					<div class="wrap-input-signup size12 bo2 bo-rad-10 m-t-3 m-b-23">
						<input class="bo-rad-10 sizefull txt10 p-l-20" type="text"
							id="inputBusqueda" placeholder="Buscar...">
					</div>
				</div>
				<div class="size17 col-md-3">
					<label>Traer...</label>
					<div class="wrap-inputtime size12 bo-rad-10 m-t-3 m-b-23">
						<select class="selection-1" name="traer">
							<option value="Alumno">Alumnos</option>
							<option value="Instructor">Instructores</option>
							<option value="Todo">Todo</option>
						</select>
					</div>
				</div>
		</div>

		<div class="col-md-12 text-center">
			<button type="submit" class="btn3 size18 txt11 trans-0-4 m-10">Buscar</button>
		</div>
		</form>
	</div>
	<c:if test="${not empty listaUsuarios }">
		<div class="container">
		<table class="table table-striped">
			<th>Nombre de Usuario</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>DNI</th>
			<th>Rol</th>
			<th>Agendas</th>
			<c:forEach items="${listaUsuarios}" var="lu">
				<tr>
					<td><a href="${context}/modificarUsuario/${lu.nombreDeUsuario}">${lu.nombreDeUsuario}</a></td>
					<td>${lu.nombre}</td>
					<td>${lu.apellido}</td>
					<td>${lu.dni}</td>
					<td>${lu.rol}</td>
					<td><a href="${context}/buscarAgendasOrg/${lu.nombreDeUsuario}"><i
									class="fa fa-search"></i></a></td>
				</tr>
			</c:forEach>
		</table>

	</div>
	</c:if>
	
	<div class="t-center">
			<h3 class="t3 text-danger">${error}</h3>
		</div>

	<hr />
	


	<!-- Footer -->
	<%@ include file="../../parts/footer.jsp"%>
	<!-- fin footer  -->
	<script src="${context}/js/mainCande.js"></script>
</body>
</html>

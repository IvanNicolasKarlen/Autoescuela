<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<c:set var="context" value="${pageContext.request.contextPath}">
</c:set>
<title>Ver Cursos</title>
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

	<!-- Welcome -->
	<section class="section-welcome bg1-pattern p-t-120 p-b-105 m-t-50">

	<!-- En este h4 se muestra el error si es que hay alguno --> <section
		class="section-reservation bg1-pattern p-t-100 p-b-113">

	<div class="container">
		<h4 class="t-center text-info">${mensaje}</h4>
		<h4 class="t-center text-danger">${error}</h4>
		<div class="row">
			<div class="col-md-12">
				<form class="row justify-content-center" method="GET"
					action="${context}/buscarAgendasOrg/${user.nombreDeUsuario}">
					<div class="col-md-1"></div>
					<div class="size17 col-md-3">
						<label>Buscar por fecha...</label>
						<div
							class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
							<input class="my-calendar bo-rad-10 sizefull txt10 p-l-20"
								name="fecha"> <i
								class="btn-calendar fa fa-calendar ab-r-m hov-pointer m-r-18"
								aria-hidden="true"></i>
						</div>
					</div>
					<div class="size17 col-md-3">
						<button type="submit" class="btn3 size18 txt11 trans-0-4 m-10">Buscar</button>
					</div>
				</form>
			</div>
		</div>
		<hr>
		<hr>
		<hr>
		<div class="container">
		
			<table class="table table-striped">
				<th>Fecha</th>
				<th>Hora</th>
				<th>Instructor</th>
				<th>Vehiculo</th>
				<th>Especialidad</th>
				<th>Curso</th>
				<th>Clase pagada</th>
				<th>Estado del turno</th>
				<th>Modificar Estado</th>
				<th>Modificar</th>


				<c:forEach items="${listaAgenda}" var="la">
					<tr>
						<td>${la.fecha}</td>
						<td>${la.hora}</td>
						<td>${la.instructorVehiculoEspecialidad.instructor.usuario.nombre}
							${la.instructorVehiculoEspecialidad.instructor.usuario.apellido}</td>
						<td>${la.instructorVehiculoEspecialidad.vehiculo.modelo}-
							${la.instructorVehiculoEspecialidad.vehiculo.patente}</td>
						<td>${la.instructorVehiculoEspecialidad.especialidad.tipo}</td>
						<td>${la.inscripcion.curso.titulo}</td>

						<td><c:if test="${la.clasePagada == true}">
								<i class="fa fa-check"></i>
							</c:if> <c:if test="${la.clasePagada == false}">
								<i class="fa fa-times t-center"></i>
							</c:if></td>
						<td>${la.estadoDeAgenda.estado}</td>
						
						<form method="post" action="${context}/modificarTurnoOrg">
						<td><select name="estadoAgenda" class="selection-1">
								<c:forEach items="${listaEstadosAgenda}" var="ea">
									<option value="${ea.id}">${ea.estado}</option>
								</c:forEach>
						</select></td>
						<input name="idAgenda" value="${la.id}" style="display: none"></input>
						<input name="nombreUser" value="${user.nombreDeUsuario}"
							style="display: none"></input>
						<td>
							<button type="submit">
								<i class="fa fa-edit"></i>
							</button>
						</td>
						</form>
					
					</tr>
				</c:forEach>
			</table>
		</div>


	</div>
	</section> </section>
	<!-- Footer -->
	<%@ include file="../../parts/footer.jsp"%>
	<!-- fin footer  -->
</body>
</html>
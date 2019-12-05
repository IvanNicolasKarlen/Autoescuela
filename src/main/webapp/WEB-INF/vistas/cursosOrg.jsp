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
	<section class="bg-title-page flex-c-m p-t-60 p-b-10 p-l-15 p-r-15"
		style="background-image: url(${context}/images/index.jpg);">
	</section>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 p-b-30">

				<div class="col-md-4 pull-right">
					<div class="sidebar2 p-t-80 p-b-80 p-l-20 p-l-0-md p-t-0-md">
						<div class="search-sidebar2 size12 bo2 pos-relative">

							<form method="get" action="${context}/verCursos">
								<select name="espFiltro" class="selection-1">
									<c:forEach items="${listaesp}" var="les">
										<option class="input-search-sidebar2 txt10 p-l-20 p-r-55">${les.tipo}</option>
									</c:forEach>
									<option class="input-search-sidebar2 txt10 p-l-20 p-r-55"
										value="">Reiniciar</option>
								</select>
								<div class="text-right" style="margin-top: 10px">
									<button class="btn3 size13 txt11 trans-0-4">Filtrar</button>
								</div>

							</form>
						</div>
					</div>
				</div>
				<h4 class="t-center text-info">${mensaje}</h4>
				<h4 class="t-center text-danger">${error}</h4>
				<table class="table table-striped">
					<th>Nombre</th>
					<th>Estado</th>
					<th>Descripcion</th>
					<th>Precio</th>
					<th>Clases</th>
					<th>Especialidad</th>
					<th>Modificar</th>
					<th>Eliminar</th>
					<c:forEach items="${listaCursos}" var="lc">
						<tr>
							<td>${lc.titulo}</td>
							<td>${lc.estadoDelCurso.estadoDelCurso}</td>
							<td>${lc.descripcion}</td>
							<td>$${lc.precio}</td>
							<td>${lc.cantClasesPracticas}</td>
							<td>${lc.especialidad.tipo}</td>
							<td><a href="${context}/modificarCurso/${lc.id}"><i
									class="fa fa-edit"></i></a></td>
							<td><a href="${context}/eliminarCurso/${lc.id}"><i
									class="fa fa-trash"></i></a></td>
						</tr>
					</c:forEach>
				</table>

				<a href="${context}/agregarCurso">Agregar nuevo Curso</a>
			</div>
		</div>
	</div>





	<!-- Footer -->
	<%@ include file="../../parts/footer.jsp"%>
	<!-- fin footer  -->
</body>
</html>

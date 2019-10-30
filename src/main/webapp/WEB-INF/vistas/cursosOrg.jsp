<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>
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

	<!-- En este h4 se muestra el error si es que hay alguno -->
		
		<section class="section-reservation bg1-pattern p-t-100 p-b-113">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 p-b-30">
						<div class="t-center">
						<h4 class="t-center text-danger">${mensaje}</h4>
						<h4 class="t-center text-danger">${error}</h4>
						<table class="table table-striped">
							<th>Nombre</th>
							<th>Estado</th>
							<th>Descripcion</th>
							<th>Precio</th>
							<th>Clases</th>
							<th>Modificar</th>
							<th>Eliminar</th>
							<c:forEach items="${listaCursos}" var="lc">
							<tr>
									<td>${lc.titulo}</td>
									<td>${lc.estadoDelCurso.estadoDelCurso}</td>
									<td>${lc.descripcion}</td>
									<td>$${lc.precio}</td>
									<td>${lc.cantClasesPracticas}</td>
									<td><a href="${context}/modificarCurso/${lc.id}"><i class="fa fa-edit"></i></a></td>
									<td><a href="${context}/eliminarCurso/${lc.id}"><i class="fa fa-trash"></i></a></td>
							</tr>
							</c:forEach>	
						</table>
						
						<a href="${context}/agregarCurso">Agregar nuevo Curso</a>
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


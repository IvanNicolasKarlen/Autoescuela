<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title>Curso</title>
<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>
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
			<div class="col-lg-12 p-b-30">
				<div class="t-center">
					
					<h3>${mensaje}</h3>
					<h3>${error}</h3>
					<hr>
					<p>
						<a href="${context}/verCursos">Ver cursos</a>
					</p>
					<p>
						<a href="${context}/index">Volver al inicio</a>
					</p>
					<p>
						<a href="${context}/agregarCurso">Agregar nuevo curso</a>
					</p>
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
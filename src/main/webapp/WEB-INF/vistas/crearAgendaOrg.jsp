<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<head>
<title>Home</title>
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
	
	<section class="section-reservation bg1-pattern p-t-100 p-b-113">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 p-b-30">
				<div class="t-center">
					<h4 class="t-center text-danger">${mensaje}</h4>
	<h4 class="t-center text-danger">${error}</h4>

					
					<form action="validarAgenda" method="post">
					
					
						<div class=" t-center m-b-5 m-t-20">
						<label for="cursoId">Seleccione el curso para el q desea crear la agenda:</label>
							<select name="cursoId">
								<c:forEach items="${listaCursos}" var="curso">
									<option value="${curso.id}">${curso.titulo}</option>
								</c:forEach>
							</select>
						</div>
						<div class=" t-center m-b-5 m-t-20">
							<label class="txt9">Seleccione a que horario comenzaran a
								impartirse las clases: </label>
							<input name="horaComienzo" type="number" min="9" max="17" />
						</div>
						<div class=" t-center m-b-5 m-t-20">
							<label class="txt9">Seleccione a que horario terminaran
								las clases: </label>
							<input name="horaFinal" type="number" min="10" max="18" />
						</div>
						<p class="m-t-10">El horario de inicio no debe ser mayor al
							horario final</p>
						<div class="wrap-btn-booking flex-c-m m-t-6">
							<!-- Button3 -->
							<button type="submit"
								class=" m-t-50 btn3 flex-c-m size13 txt11 trans-0-4">
								Continuar</button>
						</div>

					</form>

					<a href="index">Volver a Inicio</a>
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


<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<head>
<title>Agregar Curso</title>
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
	<h4 class="t-center text-danger">${mensaje}</h4>
	<section class="section-reservation bg1-pattern p-t-100 p-b-113">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 p-b-30">
				<div class="t-center">

					<h4 class="tit3 t-center m-b-35 m-t-2">Agregar nuevo curso</h4>
				</div>

				<form:form modelAttribute="curso" action="validarCurso"
					method="post" class="wrap-form-reservation size22 m-l-r-auto">
					<div class="row">
						<div class="col-md-4">
							<label class="txt9"> Titulo </label>

							<div class="wrap-inputname size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<form:input class="bo-rad-10 sizefull txt10 p-l-20" type="text"
									path="titulo" id="titulo"></form:input>
							</div>
						</div>
						<div class="col-md-4">
							<label class="txt9"> Cantidad de clases: </label>

							<div class="wrap-inputname size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<form:input type="number" min="1" path="cantClasesPracticas"
									id="cantClasesPracticas"
									class="bo-rad-10 sizefull txt10 p-l-20"></form:input>
							</div>
						</div>


						<div class="col-md-4">
							<!-- People -->
							<label class="txt9"> Especialidad </label>

							<div class="wrap-inputpeople size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<select class="selection-1 select2-hidden-accessible"
									tabindex="-1" aria-hidden="true" name="especialidadId">
									<c:forEach items="${listaEspecialidades}" var="especialidades">
										<option value="${especialidades.id}">${especialidades.tipo}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-4">
							<label class="txt9"> Precio </label>
							<div class="wrap-inputpeople size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<form:input class="bo-rad-10 sizefull txt10 p-l-20"
									type="number" path="precio" id="precio"></form:input>
							</div>
						</div>


						<div class="col-8">

							<label class="txt9"> Descripcion </label>
							<form:textarea
								class="bo-rad-10 size35 bo2 txt10 p-l-20 p-t-15 m-b-10 m-t-3"
								path="descripcion" id="descripcion"></form:textarea>
						</div>
					</div>


					<div class="wrap-btn-booking flex-c-m m-t-6">
						<!-- Button3 -->
						<button type="submit" class="btn3 flex-c-m size13 txt11 trans-0-4">
							Crear</button>
					</div>
				</form:form>

			</div>
		</div>
	</div>
	</section> <!-- Footer --> <%@ include file="../../parts/footer.jsp"%>
	<!-- fin footer  -->
</body>
</html>
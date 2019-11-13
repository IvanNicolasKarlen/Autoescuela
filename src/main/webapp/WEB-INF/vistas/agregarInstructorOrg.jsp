<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>
<title>Agregar Instructor</title>
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

					<p>
						<i>Los campos marcados con * son obligatorios</i>
					</p>
					<h4 class="t-center text-warning">${error}</h4>
				</div>

				<form:form class="wrap-form-reservation size22 m-l-r-auto"
					method="POST" modelAttribute="usuario" action="${context}/agregarInstructor2">
					<div class="row">
						<div class="col-md-4">
							<!-- Date -->
							<span class="txt9"> Nombre* </span>

							<div
								class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<form:input class="bo-rad-10 sizefull txt10 p-l-20" type="text"
									id="nombre" path="nombre"></form:input>
							</div>
						</div>
						<div class="col-md-4">
							<!-- Date -->
							<span class="txt9"> Apellido* </span>

							<div
								class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<form:input class="bo-rad-10 sizefull txt10 p-l-20" type="text"
									id="apellido" path="apellido"></form:input>
							</div>
						</div>
						<div class="col-md-4">
							<!-- Date -->
							<span class="txt9"> DNI* </span>

							<div
								class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<form:input class="bo-rad-10 sizefull txt10 p-l-20"
									type="number" max-lenght="8" min-lenght="8" id="dni" path="dni"></form:input>
							</div>
						</div>
						<div class="col-md-4">
							<!-- Date -->
							<span class="txt9"> Email </span>

							<div
								class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<form:input class="bo-rad-10 sizefull txt10 p-l-20" type="email"
									id="email" path="email"></form:input>
							</div>
						</div>
						<div class="col-md-4">
							<!-- Date -->
							<span class="txt9"> Nombre de Usuario* </span>

							<div
								class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<form:input class="bo-rad-10 sizefull txt10 p-l-20" type="text"
									id="nombreDeUsuario" path="nombreDeUsuario"></form:input>
							</div>
						</div>
						<div class="col-md-4">
							<!-- Date -->
							<span class="txt9"> Contraseña* </span>

							<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<form:input class="bo-rad-10 sizefull txt10 p-l-20"
									type="password" id="password" path="password"></form:input>
							</div>
						</div>
						<div class="col-md-4">
							<span class="txt9"> Repita la contraseña* </span>

							<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<input class="bo-rad-10 sizefull txt10 p-l-20" type="password"
									id="pass2" name="pass2"></input>
							</div>
						</div>
						
					</div>

					<div class="wrap-btn-booking flex-c-m m-t-6">
						<!-- Button3 -->
						<button type="submit" class="btn3 flex-c-m size13 txt11 trans-0-4">Agregar
							Instructor</button>
					</div>
				</form:form>

			</div>

		</div>
	</div>
	</div>
	</div>
	</section> <!-- Footer --> <%@ include file="../../parts/footer.jsp"%>
	<!-- fin footer  -->
</body>
</html>
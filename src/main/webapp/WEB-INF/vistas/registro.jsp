<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrarse</title>
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

	<!-- Title Page -->
	<section class="bg-title-page flex-c-m p-t-160 p-b-80 p-l-15 p-r-15"
		style="background-image: url(images/registrobanner.jpg);">
	<h2 class="tit6 t-center">Regístrese</h2>
	</section>


	<!-- Reservation -->
	<section class="section-reservation bg1-pattern p-t-100 p-b-113">
	<div class="container">
		<div class="row">
			<div class="col-lg-12 p-b-30">
				<div class="t-center">
					<span class="tit2 t-center"> Registro </span>

					<h3 class="tit3 t-center m-b-35 m-t-2">Ingrese sus datos</h3>
					<p>
						<i>Los campos marcados con * son obligatorios</i>
					</p>
					<h4 class="t-center text-warning">${error}</h4>
				</div>

				<form:form class="wrap-form-reservation size22 m-l-r-auto"
					method="POST" modelAttribute="usuario" action="realizarRegistro">
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
							<span class="txt9"> Nombre de Usuario </span>

							<div
								class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<form:input class="bo-rad-10 sizefull txt10 p-l-20" type="text"
									id="nombreDeUsuario" path="nombreDeUsuario"></form:input>
							</div>
						</div>
						
						<div class="col-md-4">
							<!-- Date -->
							<span class="txt9"> Contraseña* </span>

							<div
								class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<form:input class="bo-rad-10 sizefull txt10 p-l-20"
									type="password" id="password" path="password"></form:input>
							</div>
						</div>
						<div class="col-md-4">
							<!-- Date -->
							<span class="txt9"> Repita la contraseña* </span>

							<div
								class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
								<input class="bo-rad-10 sizefull txt10 p-l-20" type="password"
									id="pass2" name="pass2"></input>
							</div>
						</div>

					</div>

					<div class="wrap-btn-booking flex-c-m m-t-6">
						<!-- Button3 -->
						<button type="submit" class="btn3 flex-c-m size13 txt11 trans-0-4">
							Registrarme</button>
					</div>
				</form:form>
				<a href="login" class="text-center">Ya tengo usuario</a>
			</div>

		</div>

		<div class="info-reservation flex-w p-t-80">
			<div class="size23 w-full-md p-t-40 p-r-30 p-r-0-md">
				<h4 class="txt5 m-b-18">Reserve by Phone</h4>

				<p class="size25">
					Donec quis euismod purus. Donec feugiat ligula rhoncus, varius nisl
					sed, tincidunt lectus. <span class="txt25">Nulla vulputate</span> ,
					lectus vel volutpat efficitur, orci <span class="txt25">lacus
						sodales</span> sem, sit amet quam: <span class="txt24">(001) 345
						6889</span>
				</p>
			</div>

			<div class="size24 w-full-md p-t-40">
				<h4 class="txt5 m-b-18">For Event Booking</h4>

				<p class="size26">
					Donec feugiat ligula rhoncus: <span class="txt24">(001) 345
						6889</span> , varius nisl sed, tinci-dunt lectus sodales sem.
				</p>
			</div>

		</div>
	</div>
	</section>


	<!-- Footer -->
<%@ include file="../../parts/footer.jsp" %> 
	<!-- fin footer  -->
</body>
</html>
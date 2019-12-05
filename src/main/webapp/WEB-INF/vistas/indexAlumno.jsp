<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>Home</title>
	<!-- meta, css, vendor, etc. -->

<%@ include file="../../parts/meta.jsp" %> 
	<!-- fin del meta, css, vendor, etc -->
</head>
<body class="animsition">

	<!-- Header -->
<%@ include file="../../parts/header.jsp" %> 
	<!-- fin header -->
	<!-- Sidebar -->
<%@ include file="../../parts/sidebar.jsp" %> 
	<!-- fin sidebar -->
	

	<!-- Title Page -->
	<section class="bg-title-page flex-c-m p-t-160 p-b-80 p-l-15 p-r-15" style="background-image: url(images/index14.jpg);">
		<h1 class="tit4 t-center">
			Bienvenido ${usuario.nombre}
		</h1>
	</section>


	<!-- Our Story -->
	<section class="bg1-pattern p-t-120 p-b-105">
		<div class="container">
		<div class="tit2 text-center">
			Me alegro que regresaste
		</div>

	<!--	<h3 class="tit3 t-center m-b-35 m-t-5">
			Seccion Principal
		</h3>
-->
		
					<p class="t-center m-b-22 size3 m-l-r-auto m-b-70">
					En esta seccion podrás encontrar todo el contenido necesario para llevar a cabo tu cursada.
						</p>
		
			<!-- Delicious -->
			<div class="row">
				<div class="col-md-6 p-t-45 p-b-30 ">
					<div class="wrap-text-welcome t-center">
						
						<span class="tit2 t-center">
							¿Como me inscribo?
						</span>
						<h3 class="tit3 t-center m-b-35 m-t-5">
							Inscripción
						</h3>

						<p class="t-center m-b-22 size3 m-l-r-auto">
							Dirigite a nuestro calendario para reservar tus dias y horarios.
						</p>

						<a href="listadoCursos" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					Anotarme
				</a>
					</div>
				</div>

				<div class="col-md-6 p-b-30">
					<div class="wrap-pic-welcome size2 bo-rad-10 hov-img-zoom m-l-r-auto">
						<img src="images/index4.jpg" alt="IMG-OUR">
					</div>
				</div>
			</div>


			<!-- Romantic -->
			<div class="row p-t-170">
				<div class="col-md-6 p-b-30">
					<div class="wrap-pic-romantic size2 bo-rad-10 hov-img-zoom m-l-r-auto">
						<img src="images/index5.jpg" alt="IMG-OUR">
					</div>
				</div>

				<div class="col-md-6 p-t-45 p-b-30">
					<div class="wrap-text-romantic t-center">
						<span class="tit2 t-center">
							¿Que dias tengo que cursar?
						</span>

						<h3 class="tit3 t-center m-b-35 m-t-5">
							Dias de Cursada
						</h3>

						<p class="t-center m-b-22 size3 m-l-r-auto">
							Dirigite a nuestra seccion para ver tus fechas, horarios, y todos los datos que queres saber
						</p>
						
						
						<a href="listadoFechas" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					Ver Dias y Horarios
						</a>
					
					</div>
				</div>
			</div>
			
			
			<div class="row p-t-170">
				<div class="col-md-6 p-t-45 p-b-30 ">
					<div class="wrap-text-welcome t-center">
						
						<span class="tit2 t-center">
							¿Donde veo todo lo que ya cursé?
						</span>
						<h3 class="tit3 t-center m-b-35 m-t-5">
							Historial de Clases
						</h3>

						<p class="t-center m-b-22 size3 m-l-r-auto">
							Dirigite a nuestro historial para llevar un control sobre los cursos ya realizados.
						</p>

						<a href="historial" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					Ver Historial
				</a>
					</div>
				</div>

				<div class="col-md-6 p-b-30">
					<div class="wrap-pic-welcome size2 bo-rad-10 hov-img-zoom m-l-r-auto">
						<img src="images/index8.jpg" alt="IMG-OUR">
					</div>
				</div>
			</div>
			
			
			
		</div>
	</section>
	<!-- Footer -->
<%@ include file="../../parts/footer.jsp" %> 
	<!-- fin footer  -->
</body>
</html>
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
	
	
	
	
	<!-- Welcome -->
	<section class="section-welcome bg1-pattern p-t-120 p-b-105 m-t-50">
		<div class="container">
			<div class="row">
				<div class="col-md-6 p-t-45 p-b-30">
					<div class="wrap-text-welcome t-center">
						

						<h3 class="tit3 t-center m-b-35 m-t-5">
							Aun no te has anotado
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
		</div>
	</section>
	











<section class="section-ourmenu bg2-pattern p-t-115 p-b-120">
		<div class="container">
			<div class="title-section-ourmenu t-center m-b-22">
				<span class="tit2 t-center">
					
				</span>

				<h3 class="tit5 t-center m-t-2">
					Tus clases
				</h3>
			</div>

			
			
			
			
			
			
			
			<div class="card text-center">
  <div class="card-header">
    <div class="wrap-text-blo1  text-center ">
								<h4 class="txt5 color0-hov trans-0-4 m-b-13">
									Clase 1 
								</h4>
	</div>
  </div>
  <div class="card-body">
   <p class="m-b-20">
								<b>Fecha:</b> 21/09/2019 
								</p>
								<p class="m-b-20">
								<b>Horario:</b> 14:00 hrs
								</p>
								<p class="m-b-20">
								<b>Instructor:</b> Cesar Millan
								</p>
								<p class="m-b-20">
								<b>Auto:</b> Vento color Rojo
								</p>
								
    <button href="reservation.html" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					Modificar
				</button>
  </div>
  
</div>
<a href="fechas" class="btn3 flex-c-m size13 txt11 m-t-20 trans-0-4 m-l-r-auto">
					Agregar clases
				</a>
		</div>
	</section>
	
	
	
	
	





	<!-- Footer -->
<%@ include file="../../parts/footer.jsp" %> 
	<!-- fin footer  -->
</body>
</html>
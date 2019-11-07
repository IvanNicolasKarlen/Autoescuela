<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>Home</title>
	<!-- meta, css, vendor, etc. -->
	<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>
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
	
	<!-- Slide1 -->
	<section class="section-slide">
		<div class="wrap-slick1">
			<div class="slick1">
				<div class="item-slick1 item1-slick1" style="background-image: url(images/index.jpg);">
					<div class="wrap-content-slide1 sizefull flex-col-c-m p-l-15 p-r-15 p-t-150 p-b-170">
						<h2 class="caption2-slide1 tit1 t-center animated visible-false m-b-37" data-appear="fadeInUp">
							Bienvenidx Organizadxr
							
						</h2>
							<div class="wrap-btn-slide1 animated visible-false" data-appear="zoomIn">
								<a href="${context}/verCursos" class="btn1 flex-c-m size1 txt3 trans-0-4">
								Ver cursos
								</a>
							</div>
							<div class="wrap-btn-slide1 animated visible-false" data-appear="zoomIn">
								<a href="${context}/agregarCurso" class="btn1 flex-c-m size1 txt3 trans-0-4">
								Agregar nuevo curso
								</a>
							</div>
					</div>
				</div>	
			</div>
		</div>
	</section>

	<!-- Welcome -->
	<section class="section-welcome bg1-pattern p-t-120 p-b-105">
		<div class="container">
			<div class="row">
				<div class="col-md-6 p-t-45 p-b-30">
					<div class="wrap-text-welcome t-center">
						<span class="tit2 t-center">
							Curso de Autoescuela
						</span>

						<h3 class="tit3 t-center m-b-35 m-t-5">
							Bienvenido
						</h3>

						<p class="t-center m-b-22 size3 m-l-r-auto">
							Comenza a tomar tus clases de manejo con nosotros, te estamos esperando.
						</p>

						<a href="nosotros" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
								Conocenos
							</a>
					</div>
				</div>

				<div class="col-md-6 p-b-30">
					<div class="wrap-pic-welcome size2 bo-rad-10 hov-img-zoom m-l-r-auto">
						<img src="${context}/images/index2.jpg" alt="IMG-OUR">
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
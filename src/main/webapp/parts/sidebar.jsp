<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<aside class="sidebar trans-0-4">
		<!-- Button Hide sidebar -->
		<button class="btn-hide-sidebar ti-close color0-hov trans-0-4"></button>

		<!-- - -->
		<ul class="menu-sidebar p-t-95 p-b-70">
			<c:if test="${usuarioId!=null}">
			<a href="cuenta" class="btn3 flex-c-m size13  txt11 trans-5-4 m-b-10 m-l-r-auto">
					Mi Cuenta
				</a>
			</c:if>
			<c:if test="${usuarioId==null}">
				<a href="login" class="btn3 flex-c-m size13  txt11 trans-5-4 m-b-10 m-l-r-auto">
					Ingresar
				</a>
			<li class="t-center m-b-13">
				<a href="index" class="txt19">Inicio</a>
			</li>

			<li class="t-center m-b-13">
				<a href="cursos" class="txt19">Cursos</a>
			</li>

			<li class="t-center m-b-13">
				<a href="gallery.html" class="txt19">Gallery</a>
			</li>

			<li class="t-center m-b-13">
				<a href="nosotros" class="txt19">Nosotros</a>
			</li>

			<li class="t-center m-b-13">
				<a href="contacto" class="txt19">Contacto</a>
			</li>

			<li class="t-center ">
			</c:if>
			<!-- FIN DEL IF usuarioID==null -->
			<!-- if rol=='alumno' -->
			<c:if test="${rol=='Alumno'}">
									
			</c:if>
			<!-- FIN ALUMNO -->
			<!-- Instructor -->
			<c:if test="${rol=='Instructor'}">
									
			</c:if>
							
			<!-- Fin instructor -->
		<!-- Organizador -->

		<c:if test="${rol=='Organizador'}">
			<li><a href="index">Inicio</a></li>
			<li><a href="agregarVehiculo">Agregar Vehiculo</a></li>
			<li><a href="agregarCurso">Agregar Curso</a></li>
			<li><a href="crearAgenda">Crear Agenda</a></li>
			<li><a href="agregarInstructor-1">Agregar Instructor</a></li>
		</c:if>

		<!-- Fin organizador- -->
		<c:if test="${usuarioId==null}">
				<a href="registro" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					Registrarse
				</a>
			</c:if>
			<c:if test="${usuarioId!=null}">
				<a href="cerrarSesion" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					Salir
				</a>
			</c:if>
			</li>
		</ul>

		<!-- - -->
		<div class="gallery-sidebar t-center p-l-60 p-r-60 p-b-40">
			<!-- - -->
			<h4 class="txt20 m-b-33">
				Gallery
			</h4>

			<!-- Gallery -->
			<div class="wrap-gallery-sidebar flex-w">
				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-01.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-01.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-02.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-02.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-03.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-03.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-05.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-05.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-06.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-06.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-07.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-07.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-09.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-09.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-10.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-10.jpg" alt="GALLERY">
				</a>

				<a class="item-gallery-sidebar wrap-pic-w" href="images/photo-gallery-11.jpg" data-lightbox="gallery-footer">
					<img src="images/photo-gallery-thumb-11.jpg" alt="GALLERY">
				</a>
			</div>
		</div>
	</aside>
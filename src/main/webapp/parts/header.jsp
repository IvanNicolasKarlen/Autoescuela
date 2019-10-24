<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<header>
		<!-- Header desktop -->
		<div class="wrap-menu-header gradient1 trans-0-4">
			<div class="container h-full">
				<div class="wrap_header trans-0-3">
					<!-- Logo -->
					<div class="logo">
						<a href="index.html">
							<img src="images/logo.png" alt="IMG-LOGO" data-logofixed="images/logo.png">
						</a>
					</div>

					<!-- Menu -->
					<div class="wrap_menu p-l-45 p-l-0-xl">
						<nav class="menu">
							<ul class="main_menu">
							<!-- Estos son los links q se muestran sin usuario-->
							<c:if test="${usuarioId==null}">
								<li>
									<a href="index">Inicio</a>
								</li>
								<li>
									<a href="cursos">Cursos</a>
								</li>
								<li>
								<!-- Aca no se q poner -->
									<a href="gallery.html">Gallery</a>
									<!-- xD -->
								</li>
								<li>
									<a href="nosotros">Nosotros</a>
								</li>
								<li>
									<a href="contacto">Contacto</a>
								</li>
							</c:if>
							<!-- Fin del los links sin user-->
							<!-- Estos se van a mostrar si sos alumno -->
								<c:if test="${rol=='Alumno'}">
									<li>
									<a href="index">Inicio</a>
								</li>
								<li>
									<a href="listadoCursos">Anotarme</a>
								</li>
								<li>
									<a href="listadoFechas">Mis clases</a>
								</li>
								</c:if>
							<!-- fin alumno -->
							<!-- Instructor -->
								<c:if test="${rol=='Instructor'}">
									
								</c:if>
							
							<!-- Fin instructor -->
							<!-- Organizador -->
							<!-- Organizador -->
							<c:if test="${rol=='Organizador'}">
								<li><a href="index">Inicio</a></li>
								<li><a href="agregarVehiculo">Agregar Vehiculo</a></li>
								<li><a href="agregarCurso">Agregar Curso</a></li>
								<li><a href="crearAgenda">Crear Agenda</a></li>
								<li><a href="agregarInstructor">Agregar Instructor</a></li>
							</c:if>
							<!-- Fin organizador- -->
							</ul>
						</nav>
					</div>

					<!-- Social -->
					<div class="social flex-w flex-l-m p-r-20">
					<!-- IF: Si el ID esta vacio muestra INGRESAR, sino muestra MI CUENTA -->
						<c:if test="${usuarioId!=null}">
							<a href="cuenta"><b class="text-danger">Mi cuenta</b></a>
						</c:if>
						<c:if test="${usuarioId==null}">
							<a href="login"><b class="text-danger">Ingresar</b></a>
						</c:if>	
					<!-- fin del IF -->	

						<button class="btn-show-sidebar m-l-33 trans-0-4"></button>
					</div>
				</div>
			</div>
		</div>
	</header>
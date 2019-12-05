<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<header>
		<!-- Header desktop -->
		<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>
		<div class="wrap-menu-header gradient1 trans-0-4">
			<div class="container h-full">
				<div class="wrap_header trans-0-3">
					<!-- Logo -->
					<div class="logo">
						<a href="${context}/index">
							<img src="${context}/images/logo.png" alt="IMG-LOGO" data-logofixed="${context}/images/logo.png">
						</a>
					</div>

					<!-- Menu -->
					<div class="wrap_menu p-l-45 p-l-0-xl">
						<nav class="menu">
							<ul class="main_menu">
							<!-- Estos son los links q se muestran sin usuario-->
							<c:if test="${ empty rol}">
								<li>
									<a href="${context}/index">Inicio</a>
								</li>
								<li>
									<a href="${context}/cursos">Cursos</a>
								</li>
								<li>
								<!-- Aca no se q poner -->
									<a href="${context}/gallery.html">Gallery</a>
									<!-- xD -->
								</li>
								<li>
									<a href="${context}/nosotros">Nosotros</a>
								</li>
								<li>
									<a href="${context}/contacto">Contacto</a>
								</li>
							</c:if>
							<!-- Fin del los links sin user-->
							<!-- Estos se van a mostrar si sos alumno -->
								<c:if test="${rol=='Alumno'}">
									<li>
									<a class="text-center" href="${context}/indexAlumno">Inicio</a>
								</li>
								<li>
									<a class="text-center" href="${context}/listadoCursos">Anotarme</a>
								</li>
								<li>
									<a class="text-center" href="${context}/listadoFechas">Mis clases</a>
								</li>
								<li>
									<a class="text-center" href="${context}/historial">Historial</a>
								</li>
								</c:if>
							<!-- fin alumno -->
							<!-- Instructor -->
								<c:if test="${rol=='Instructor'}">
							
									<li>
										<a href="${context}/index">Inicio</a>
									</li>
									
									<li>
										<a href="${context}/AlumnosDelInstructor">Buscador de Alumnos</a>
									</li>
									
									<li>
										<a href="${context}/horasTrabajadas">Horas trabajadas</a>
									</li>
							
								</c:if>
							
							<!-- Fin instructor -->
							<!-- Organizador -->
							<!-- Organizador -->
							<c:if test="${rol=='Organizador'}">
								<li><a href="${context}/index">Inicio</a></li>
								<li><a href="${context}/agregarCurso">Agregar Curso</a></li>
								<li><a href="${context}/crearAgenda">Crear Agenda</a></li>
								<li><a href="${context}/agregarInstructor">Agregar Instructor</a></li>
								<li><a href="${context}/busquedaUsuarios">Buscar Usuarios</a></li>	
							</c:if>
							<!-- Fin organizador- -->
							</ul>
						</nav>
					</div>

					<!-- Social -->
					<div class="social flex-w flex-l-m p-r-20">
					<!-- IF: Si el ROL esta vacio muestra INGRESAR, sino muestra NOMBRE Y APELLIDO -->
						<c:if test="${not empty rol}">
							<a><b class="text-danger">Mi cuenta </b></a>
							<a href="${context}/notificaciones"><b class="text-danger"><span class="fa fa-bell" aria-hidden="true">
								<c:if test="${notiSize > 0}">
									<b style="color:yellow;font-size:20px"> ${notiSize}</b>
								</c:if> 
							</span></b></a>
						</c:if>
						<c:if test="${empty rol}">
							<a href="${context}/login"><b class="text-danger">Ingresar</b></a>
						</c:if>	
					<!-- fin del IF -->	

						<button class="btn-show-sidebar m-l-33 trans-0-4"></button>
					</div>
				</div>
			</div>
		</div>
	</header>
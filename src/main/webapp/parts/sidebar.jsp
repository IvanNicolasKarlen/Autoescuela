<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<aside class="sidebar trans-0-4">
		<!-- Button Hide sidebar -->
		<button class="btn-hide-sidebar ti-close color0-hov trans-0-4"></button>

		<!-- - -->
		<ul class="menu-sidebar p-t-95 p-b-70">
			<c:if test="${rol!=null}">
			<a href="cuenta" class="btn3 flex-c-m size13  txt11 trans-5-4 m-b-10 m-l-r-auto">
					Mi Cuenta
				</a>
			</c:if>
			<c:if test="${rol==null}">
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
<<<<<<< HEAD
			<li class="t-center m-b-13"><a  href="${context}/indexAlumno">Inicio</a></li>
			<li class="t-center m-b-13"><a  href="${context}/listadoCursos">Anotarme</a></li>
			<li class="t-center m-b-13"><a  href="${context}/listadoFechas">Mis clases</a></li>
			<li class="t-center m-b-13"><a href="${context}/historial">Historial</a></li>
=======
			<li><a href="index">Inicio</a></li>
			<li><a href="listadoCursos">Anotarme</a></li>
			<li><a href="listadoFechas">Mis clases</a></li>
>>>>>>> Diana
		</c:if>
		<!-- FIN ALUMNO -->
			<!-- Instructor -->
		<c:if test="${rol=='Instructor'}">
			<li><a href="index">Inicio</a></li>
			<li><a href="AlumnosDelInstructor">Mis Alumnos</a></li>
			<li><a href="buscadorDeAlumnos">Buscador de Alumnos</a></li>
		</c:if>

		<!-- Fin instructor -->
			<!-- Organizador -->

		<c:if test="${rol=='Organizador'}">
			<li class="t-center m-b-13"><a href="index">Inicio</a></li>
			<li class="t-center m-b-13"><a href="agregarVehiculo">Agregar Vehiculo</a></li>
			<li class="t-center m-b-13"><a href="agregarCurso">Agregar Curso</a></li>
			<li class="t-center m-b-13"><a href="agregarEspecialidad">Agregar Especialidad</a></li>
			<li class="t-center m-b-13"><a href="agregarTipoVehiculo">Agregar Tipo de Vehiculo</a></li>
			<li class="t-center m-b-13"><a href="crearAgenda">Crear Agenda</a></li>
			<li class="t-center m-b-13"><a href="agregarInstructor-1">Agregar Instructor</a></li>
		</c:if>

		<!-- Fin organizador- -->
		<c:if test="${rol==null}">
				<a href="registro" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					Registrarse
				</a>
			</c:if>
			<c:if test="${rol!=null}">
				<a href="cerrarSesion" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					Salir
				</a>
			</c:if>
			</li>
		</ul>

		<!-- - -->

	</aside>
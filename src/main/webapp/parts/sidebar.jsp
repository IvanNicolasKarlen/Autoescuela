<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<aside class="sidebar trans-0-4">
		<!-- Button Hide sidebar -->
		<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>
		<button class="btn-hide-sidebar ti-close color0-hov trans-0-4"></button>

		<!-- - -->
		<ul class="menu-sidebar p-t-95 p-b-70">
<<<<<<< HEAD
			<c:if test="${rol!=null}">
			<a href="cuenta" class="btn3 flex-c-m size13  txt11 trans-5-4 m-b-10 m-l-r-auto">
					Mi Cuenta
				</a>
			</c:if>
			<c:if test="${rol==null}">
				<a href="login" class="btn3 flex-c-m size13  txt11 trans-5-4 m-b-10 m-l-r-auto">
=======
			<c:if test="${not empty rol}">
			<a href="${context}/cuenta" class="btn3 flex-c-m size13  txt11 trans-5-4 m-b-10 m-l-r-auto">
					Mi Cuenta
				</a>
			</c:if>
			<c:if test="${empty rol}">
				<a href="${context}/login" class="btn3 flex-c-m size13  txt11 trans-5-4 m-b-10 m-l-r-auto">
>>>>>>> d91836232065313d4af5c21b979648917e9a0719
					Ingresar
				</a>
			<li class="t-center m-b-13">
				<a href="${context}/index" class="txt19">Inicio</a>
			</li>

			<li class="t-center m-b-13">
				<a href="${context}/cursos" class="txt19">Cursos</a>
			</li>

			<li class="t-center m-b-13">
				<a href="${context}/gallery.html" class="txt19">Gallery</a>
			</li>

			<li class="t-center m-b-13">
				<a href="${context}/nosotros" class="txt19">Nosotros</a>
			</li>

			<li class="t-center m-b-13">
				<a href="${context}/contacto" class="txt19">Contacto</a>
			</li>

			<li class="t-center ">
			</c:if>
			<!-- FIN DEL IF usuarioID==null -->
			<!-- if rol=='alumno' -->
		<c:if test="${rol=='Alumno'}">
<<<<<<< HEAD
			<li><a href="index">Inicio</a></li>
			<li><a href="listadoCursos">Anotarme</a></li>
			<li><a href="listadoFechas">Mis clases</a></li>
=======
			<li><a href="${context}/index">Inicio</a></li>
			<li><a href="${context}/listadoCursos">Anotarme</a></li>
			<li><a href="${context}/listadoFechas">Mis clases</a></li>
>>>>>>> d91836232065313d4af5c21b979648917e9a0719
		</c:if>
		<!-- FIN ALUMNO -->
			<!-- Instructor -->
		<c:if test="${rol=='Instructor'}">
<<<<<<< HEAD
			<li><a href="index">Inicio</a></li>
			<li><a href="AlumnosDelInstructor">Mis Alumnos</a></li>
			<li><a href="buscadorDeAlumnos">Buscador de Alumnos</a></li>
=======
			<li><a href="${context}/index">Inicio</a></li>
			<li><a href="${context}/AlumnosDelInstructor">Mis Alumnos</a></li>
			<li><a href="${context}/buscadorDeAlumnos">Buscador de Alumnos</a></li>
>>>>>>> d91836232065313d4af5c21b979648917e9a0719
		</c:if>

		<!-- Fin instructor -->
			<!-- Organizador -->

		<c:if test="${rol=='Organizador'}">
			<li class="t-center m-b-13"><a href="${context}/index">Inicio</a></li>
			<li class="t-center m-b-13"><a href="${context}/agregarVehiculo">Agregar Vehiculo</a></li>
			<li class="t-center m-b-13"><a href="${context}/agregarCurso">Agregar Curso</a></li>
			<li class="t-center m-b-13"><a href="${context}/busquedaUsuarios">Buscar Usuarios</a></li>
			<li class="t-center m-b-13"><a href="${context}/agregarEspecialidad">Agregar Especialidad</a></li>
			<li class="t-center m-b-13"><a href="${context}/agregarTipoVehiculo">Agregar Tipo de Vehiculo</a></li>
			<li class="t-center m-b-13"><a href="${context}/crearAgenda">Crear Agenda</a></li>
			<li class="t-center m-b-13"><a href="${context}/agregarInstructor">Agregar Instructor</a></li>
		</c:if>

		<!-- Fin organizador- -->
<<<<<<< HEAD
		<c:if test="${rol==null}">
				<a href="registro" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					Registrarse
				</a>
			</c:if>
			<c:if test="${rol!=null}">
				<a href="cerrarSesion" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
=======
		<c:if test="${empty rol}">
				<a href="${context}/registro" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					Registrarse
				</a>
			</c:if>
			<c:if test="${not empty rol}">
				<a href="${context}/cerrarSesion" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
>>>>>>> d91836232065313d4af5c21b979648917e9a0719
					Salir
				</a>
			</c:if>
			</li>
		</ul>

		<!-- - -->

	</aside>
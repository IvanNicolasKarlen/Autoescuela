<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<head>
	<title>Agregar Curso</title>
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
	
	<!-- Welcome -->
	<section class="section-welcome bg1-pattern p-t-120 p-b-105 m-t-50">
	
	<!-- En este h4 se muestra el error si es que hay alguno -->
		<h4 class="t-center text-danger">${mensaje}</h4>
		<section class="section-reservation bg1-pattern p-t-100 p-b-113">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 p-b-30">
                <div class="t-center">
	<c:if test="${estado == 'cursando'}">
					<label class="txt9">
						Titulo del curso: ${curso.titulo}
					</label>
				 	<label class="txt9">
						Descripcion: ${curso.descripcion}
					</label>
				 	<label class="txt9">
						Cantidad de clases: ${curso.cantClasesPracticas}
					</label>
					<label class="txt9">
						Precio: ${curso.precio}
					</label>
					<label class="txt9">
						Especialidad: ${curso.especialidad.tipo}
					</label>
					<label>
						Estado del Curso: 
					</label>
					<select name="estadoTipo" id="estadoTipo">
						<c:forEach items="${estadosDeCursos}" var="edc">
							<option>${edc.estadoDelCurso}</option>
						</c:forEach>	
					</select>
				 	<div class="wrap-btn-booking flex-c-m m-t-6">
                        <a id="linkModificacion" href="${context}/modificarCurso/${curso.id}">Modificar Estado del Curso</a>  
                    </div> 
	</c:if>
	<c:if test="${empty estado}">
		<form:form modelAttribute="curso" action="${context}/modificarCurso-2" method="post">
					<label class="txt9">
						Titulo del curso: ${curso.titulo}
					</label>
					<p></p>
				 	<label class="txt9">
						Descripcion:
					</label>
					<form:textarea path="descripcion" id="descripcion"></form:textarea>
					<form:input type="text" path="titulo" id="titulo" value="${curso.titulo}" style="display:none"></form:input>

				 	<label class="txt9">
						Cantidad de clases:
					</label>
					<form:input type="number" min="1" path="cantClasesPracticas" id="cantClasesPracticas"></form:input>
					<label class="txt9">
						Precio:
					</label>
					<form:input type="number" path="precio" id="precio"></form:input>
					<label class="txt9">
						Especialidad: ${curso.especialidad.tipo}
					</label>
					
					<select name="estadoId" id="estadoId">
						<c:forEach items="${estadosDeCursos}" var="edc">
							<option value="${edc.id}">${edc.estadoDelCurso}</option>
						</c:forEach>	
					</select>
					<form:input path="id" value="${curso.id}" style="display:none" />
					<input name="cursoId" value="${curso.id}" style="display:none" />
					<input name="especialidadId" value="${curso.especialidad.id}" style="display:none" />
				 	<div class="wrap-btn-booking flex-c-m m-t-6">
                        <button type="submit" class=" m-t-50 btn3 flex-c-m size13 txt11 trans-0-4">
                            Continuar
                        </button>   
                    </div> 
        </form:form>
     </c:if>   
				</div>
			</div>
		</div>
	</div>	
	</section>

	
	

	<!-- Footer -->
<%@ include file="../../parts/footer.jsp" %> 
<script src="${context}/js/mainCande.js"></script>
	<!-- fin footer  -->
</body>
</html>


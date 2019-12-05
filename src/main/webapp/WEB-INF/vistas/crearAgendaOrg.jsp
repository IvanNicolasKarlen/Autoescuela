<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<head>
<title>Crear Agenda</title>
<c:set var="context" value="${pageContext.request.contextPath}">
</c:set>
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
	<!-- En este h4 se muestra el error si es que hay alguno -->

	<section class="section-reservation bg1-pattern p-t-100 p-b-113">


	<div class="container">
		<h3 class="tit7 t-center p-b-62 p-t-105">Crear Agenda</h3>


		<form action="${context}/validarAgenda" method="post"
			class="wrap-form-reservation size22 m-l-r-auto">
			<div class="row">
				<div class="col-md-3">
					<!-- Name -->
					<label class="txt9"> Seleccione la especialidad </label>
					<div class="wrap-inputtime size12 bo2 bo-rad-10 m-t-3 m-b-23">
						<!-- Select2 -->
						<select class="selection-1" name="especialidadId">
							<c:forEach items="${listaEspecialidades}" var="esp">
								<option value="${esp.id}">${esp.tipo}</option>
							</c:forEach>
						</select>
					</div>
				</div>


				<div class="col-md-3">
					<!-- Name -->
					<label class="txt9"> Seleccione el horario de comienzo de
						clases </label>

					<div class="wrap-inputtime size12 bo2 bo-rad-10 m-t-3 m-b-23">
						<!-- Select2 -->
						<select class="selection-1" name="horaComienzo">
						<% for(int i=9; i<=17; i++){ %>
							
								<option value="<%= i*100 %>"><%=i%>:00</option>
						<% } %>	
						</select>
					</div>
				</div>
				<div class="col-md-3">
					<!-- Name -->
					<label class="txt9"> Seleccione el horario de finalización de
						clases </label>

					<div class="wrap-inputtime size12 bo2 bo-rad-10 m-t-3 m-b-23">
						<!-- Select2 -->
						<select class="selection-1" name="horaFinal">
						<% for(int i=10; i<=18; i++){ %>
							
								<option value="<%= i*100 %>"><%=i%>:00</option>
						<% } %>	
						</select>
					</div>
				</div>

				<div class="col-md-3">
					<!-- Phone -->
					<label class="txt9"> Introduzca para cuantos dias desea crear
						agendas </label>

					<div class="wrap-inputphone size12 bo2 bo-rad-10 m-t-3 m-b-23">
						<input class="bo-rad-10 sizefull txt10 p-l-20" name="hastaD"
							type="number" min="1" max="60">
					</div>
				</div>
			</div>


			<div class="wrap-btn-booking flex-c-m m-t-13">
				<!-- Button3 -->
				<button type="submit" class="btn3 flex-c-m size36 txt11 trans-0-4">
					Crear Agenda</button>
			</div>
		</form>
	</div>
	</section>
	</section>
	<!-- Footer -->
	<%@ include file="../../parts/footer.jsp"%>
	<!-- fin footer  -->
</body>
</html>

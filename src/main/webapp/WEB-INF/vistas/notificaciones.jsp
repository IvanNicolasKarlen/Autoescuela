<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<c:set var="context" value="${pageContext.request.contextPath}">
</c:set>
<title>Notificaciones</title>
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
	<section class="bg-title-page flex-c-m p-t-160 p-b-80 p-l-15 p-r-15"
		style="background-image: url(images/index.jpg);">
	<h2 class="tit6 t-center">...</h2>
	</section>
	<div class="container">
		<div
			class="wrap-label-gallery filter-tope-group size27 flex-w flex-sb-m m-l-r-auto flex-col-c-sm p-l-15 p-r-15 m-b-60">
			<a class="label-gallery txt26 trans-0-4"
				href="${context}/notificaciones?filter=noleidas">No Leidas </a> <a
				href="${context}/notificaciones?filter=leidas"
				class="label-gallery txt26 trans-0-4">Leidas</a> <a
				class="label-gallery txt26 trans-0-4"
				href="${context}/notificaciones?filter=todas">Todas </a>
		</div>
		<span><a href="${context}/notificaciones?leidas=true">Marcar
				todas como leidas</a></span>
		<c:if test="${empty notificacion}">
			<ul>
				<c:forEach items="${notificaciones}" var="noti">
					<li>
						<div class="size-28">
							<b>${noti.mensaje}</b> <a
								href="${context}/notificaciones?id=${noti.id}"><span>Ver
									detalles...</span></a>
						</div>
					</li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${not empty notificacion}">
			<p>Mensaje: ${notificacion.mensaje}</p>
			<span>Fecha: ${notificacion.fecha}</span>
			<p>Detalles:</p>
			<p>${notificacion.detalles}</p>
			<p></p>
		</c:if>
	</div>
	<!-- Footer -->
	<%@ include file="../../parts/footer.jsp"%>
	<!-- fin footer  -->
</body>
</html>
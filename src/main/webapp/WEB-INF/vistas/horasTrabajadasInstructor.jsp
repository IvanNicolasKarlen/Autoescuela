<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:set var="context" value="${pageContext.request.contextPath}"> </c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>Home</title>
	<!-- meta, css, vendor, etc. -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@ include file="../../parts/meta.jsp" %> 
	<!-- fin del meta, css, vendor, etc -->
	<script type="text/javascript" src="vendor/jquery/jquery-3.2.1.min.js"></script>
	
	<script src="js/Chart.bundle.js"></script>
    <script src="js/Chart.bundle.min.js"></script>
    <script src="js/Chart.js"></script>
    <script src="js/Chart.min.js"></script>

	
</head>
<body class="animsition">

	<!-- Header -->
<%@ include file="../../parts/header.jsp" %> 
	<!-- fin header -->
	<!-- Sidebar -->
<%@ include file="../../parts/sidebar.jsp" %> 
	<!-- fin sidebar -->
		
	<section class="section-welcome bg1-pattern p-t-120 p-b-105">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 p-b-30">
					<div class="t-center">
						<div class="wrap-text-welcome t-center">
						
						
  <form:form method="GET"  action="horasTrabajadas"> 
<h3 class="tit3 text-center">Cantidad de horas trabajadas</h3><br><br>
	
	<table class="table table-hover text-center mt-4 tabla-ancho" border="1" cellpadding="1" cellspacing="0">
		<tbody>
					
			<thead>       
				<tr class="w3-red">
					<th class="enc"><center>Mes</center></th>
					<th class="enc"><center>Cantidad de horas trabajadas</center></th>	
				</tr>
			</thead>
		
			<c:forEach var="meses" items="${listaMeses}">
				<tr>
					<td class="alt-celda"><h3>${meses.key}</h3></td>
					<td class="alt-celda"><h3>${meses.value}</h3></td>
				</tr>
			</c:forEach>
		</tbody>
	</table><br><br>
	
				<a href="${context}/grafico">Ver gráfico</a>

	
</form:form>
    					</div>	
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
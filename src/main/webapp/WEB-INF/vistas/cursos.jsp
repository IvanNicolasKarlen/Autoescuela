<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head><%@ include file="../../parts/meta.jsp" %> 
	<!-- fin del meta, css, vendor, etc -->
</head>
<body class="animsition">

	<!-- Header -->
<%@ include file="../../parts/header.jsp" %> 
	<!-- fin header -->
	<!-- Sidebar -->
<%@ include file="../../parts/sidebar.jsp" %> 
	<!-- fin sidebar -->
	
	<form modelAtributte="curso" action="cursoElegido" method="post">
	

	
	<!-- Welcome -->
	<section class="section-welcome bg1-pattern p-t-120 p-b-105 m-t-50">
	
	<h4 class="tit2 t-center m-b-35 m-t-2">
                 <label>Selecciona un curso</label>
					</h4>
	<h4 class="t-center text-danger">${error}</h4>
<div class="row">
 

<c:forEach items="${lista}" var="lc">


	<div class="card col-xs-6 col-md-4" style="width: 18rem;">
  <div class="card-body">
    <h5 class="card-title text-center bg-info text-white">${lc.cantClasesPracticas} CLASES</h5>
    <p class="card-text text-center">Clases de 1 hora</p>
	<p class="card-text text-center  bg-light text-dark">${lc.especialidad.tipo}</p>
  </div>
  <ul class="list-group list-group-flush"> 
    <li class="list-group-item">${lc.descripcion}</li>
    <li class="list-group-item">${lc.titulo}</li>
    <li class="list-group-item">Posibilidad de elegir los dias</li>
    <li class="list-group-item">Precio $${lc.precio}</li>
  </ul>
 		 <div class="card-body">
  <button type="submit" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto" path="id" name="id" value="${lc.id}">
					ANOTARME
				</button>
  		</div>
	</div>
	
	
	
</c:forEach>
		
</div>
		
	

	</section>
	</form>
	
	
<!-- Footer -->
<%@ include file="../../parts/footer.jsp" %> 
	<!-- fin footer  -->

</body>
</html>
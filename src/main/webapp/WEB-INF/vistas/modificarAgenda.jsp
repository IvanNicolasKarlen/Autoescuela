<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>Home</title>
	<!-- meta, css, vendor, etc. -->

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
	
		<h4 class="tit2 t-center m-b-35 m-t-2">
                 <label>${mensaje}</label>
					</h4>
							 
		
<c:set var = "i" value = "0" ></c:set>


<form:form  method="POST" modelAttribute="agendasViewModel" action="modificarAgenda">


<div class="row">
	<c:forEach items="${listaAgendas}" var="la">
		<div class="col-md-4 ">
		
		<h2 class="text-center color0-hov trans-0-4 bg-info text-white">agenda ${la}</h2>
		 <c:set var="i" value="${ i+1}"/>
				
				<!-- editar -->  
	
</div>
</c:forEach>
</div>	

	</br>
	</br>
<div class="card-body">
  <button type="submit" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					MODIFICAR
  </button>
  </div>
	
	
  		
  		<a href="listadoCursos" class="btn3 flex-c-m size13 txt11 trans-0-4 m-l-r-auto">
					CANCELAR
	</a>
  		
  		</form:form>
  		
  		
  		
  		  		
  		
  		
	</section>
	
	
	
<!-- Footer --> 
<%@ include file="../../parts/footer.jsp"%>

</body>
</html>

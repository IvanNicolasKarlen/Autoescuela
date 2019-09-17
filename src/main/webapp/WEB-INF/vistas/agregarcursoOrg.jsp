<%@ page language="java" contentType="text/html"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
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
	
	<!-- En este h4 se muestra el error si es que hay alguno -->
		<h4 class="t-center text-danger">${mensaje}</h4>
		<section class="section-reservation bg1-pattern p-t-100 p-b-113">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 p-b-30">
                <div class="t-center">

		<form:form modelAttribute="curso" action="validarCurso" method="post">
					<label class="txt9">
						Titulo del curso:
					</label>
					<form:input type="text" path="titulo" id="titulo"></form:input>
				 	<label class="txt9">
						Descripcion:
					</label>
					<form:input type="textarea" path="descripcion" id="descripcion"></form:input>
				 	<label class="txt9">
						Cantidad de clases:
					</label>
					<form:input type="number" path="cantClasesPracticas" id="cantClasesPracticas"></form:input>
					<label class="txt9">
						Precio:
					</label>
					<form:input type="number" path="precio" id="precio"></form:input>
				 	<div class="wrap-btn-booking flex-c-m m-t-6">
                        <button type="submit" class=" m-t-50 btn3 flex-c-m size13 txt11 trans-0-4">
                            Continuar
                        </button>   
                    </div> 
        </form:form>
        
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


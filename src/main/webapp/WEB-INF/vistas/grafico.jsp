	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>Home</title>
	<!-- meta, css, vendor, etc. -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
	
	<section class="section-welcome bg1-pattern p-t-120 p-b-105">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 p-b-30">
					<div class="t-center">
					<div class="wrap-text-welcome t-center">
	
	<!--*************************************** EMPEZANDO GRAFICO ************************************************-->
    		<script type="text/javascript" src="vendor/jquery/jquery-3.2.1.min.js"></script>
<h3 class="tit3 text-center">Gráfico de horas trabajadas</h3><br><br>
		 <canvas id="myChart"></canvas>

	<script src="js/Chart.bundle.js"></script>
	 <script>
        var ctx = document.getElementById('myChart').getContext('2d');
        var chart = new Chart(ctx, {
            type: 'pie',
            data:{
        	datasets: [{
                data:["${listaMeses.enero}",
                	  "${listaMeses.febrero}",
                	  "${listaMeses.marzo}",
                	  "${listaMeses.abril}",
                	  "${listaMeses.mayo}",
                	  "${listaMeses.junio}",
                	  "${listaMeses.julio}",
                	  "${listaMeses.agosto}",
                	  "${listaMeses.septiembre}",
                	  "${listaMeses.octubre}",
                	  "${listaMeses.noviembre}",
                	  "${listaMeses.diciembre}"],

        		backgroundColor: ['#42a5f5', 'red', 'green','blue','violet','green','yellow','black','brown','orange','pink','42a5f5'],
        		label: 'Comparacion de navegadores'}],
        		labels: ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre']},
            options: {responsive: true}
        });
        </script>

    
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
				
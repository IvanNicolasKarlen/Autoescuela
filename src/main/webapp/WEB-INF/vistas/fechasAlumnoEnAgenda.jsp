<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Contact</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
<!--===============================================================================================-->
	<link rel="icon" type="image/png" href="images/icons/favicon.png"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/themify/themify-icons.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/slick/slick.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/lightbox2/css/lightbox.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
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
                 <label>Has quedado anotado en estos dias</label>
					</h4>
				 
		
	<div class="row">
<div class="col-md-4 ">
<h2 class="text-center color0-hov trans-0-4 bg-info text-white">Curso de Auto</h2>
		 <h5 class="card-subtitle p-t-10 mb-2 text-center text-info">Clase 1</h5>
		
		 <p class="card-text text-center"><b class="color0-hov trans-0-4">Fecha</b>: 12/10/2019<br>
	<b class="color0-hov trans-0-4 text-center">Hora:</b> 16:00<br>
		
		<b class="color0-hov trans-0-4 text-center">Instructor:</b> Miguel Robledo<br>
		
		<b class="color0-hov trans-0-4 text-center">Vehiculo:</b> Suran 2016 roja</p>
		
</div>		
<div class="col-md-4 ">
<h2 class="text-center color0-hov trans-0-4 bg-info text-white">Curso de Auto</h2>
		 <h5 class="card-subtitle p-t-10 mb-2 text-center text-info">Clase 2</h5>
		
		 <p class="card-text text-center"><b class="color0-hov trans-0-4">Fecha</b>: 15/10/2019<br>
	<b class="color0-hov trans-0-4 text-center">Hora:</b> 14:00<br>
		
		<b class="color0-hov trans-0-4 text-center">Instructor:</b> Miguel Robledo<br>
		
		<b class="color0-hov trans-0-4 text-center">Vehiculo:</b> Suran 2016 roja</p>
		
</div>
<div class="col-md-4 b-22">
<h2 class="text-center color0-hov trans-0-4 bg-info text-white">Curso de Auto</h2>
		 <h5 class="card-subtitle p-t-10 mb-2 text-center text-info">Clase 3</h5>
		
		 <p class="card-text text-center"><b class="color0-hov trans-0-4">Fecha</b>: 16/10/2019<br>
	<b class="color0-hov trans-0-4 text-center">Hora:</b> 13:00<br>
		
		<b class="color0-hov trans-0-4 text-center">Instructor:</b> Miguel Robledo<br>
		
		<b class="color0-hov trans-0-4 text-center">Vehiculo:</b> Suran 2016 roja</p>
		
</div>

		
	</div>
	</section>
	
	
	
<!-- Footer --> 
<%@ include file="../../parts/footer.jsp"%>
	<!-- fin footer  -->

	<!-- Back to top -->
	<div class="btn-back-to-top bg0-hov" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="fa fa-angle-double-up" aria-hidden="true"></i>
		</span>
	</div>

	<!-- Container Selection1 -->
	<div id="dropDownSelect1"></div>



<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/bootstrap/js/popper.js"></script>
	<script type="text/javascript" src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/daterangepicker/moment.min.js"></script>
	<script type="text/javascript" src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/slick/slick.min.js"></script>
	<script type="text/javascript" src="js/slick-custom.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/parallax100/parallax100.js"></script>
	<script type="text/javascript">
        $('.parallax100').parallax100();
	</script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script type="text/javascript" src="vendor/lightbox2/js/lightbox.min.js"></script>
<!--===============================================================================================-->
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAKFWBqlKAGCeS1rMVoaNlwyayu0e0YRes"></script>
	<script src="js/map-custom.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>

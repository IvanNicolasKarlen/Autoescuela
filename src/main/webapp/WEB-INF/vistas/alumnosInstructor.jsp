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
	
	<section class="section-welcome bg1-pattern p-t-120 p-b-105">
		<div class="container">
			<div class="row">
				<div class="col-md-6 p-t-45 p-b-30">
					<div class="wrap-text-welcome t-center">
	
	
<form:form action="buscarAlumnos" method="GET">
					
					<table class="table table-hover text-center mt-4" border="1" cellpadding="1" cellspacing="0">
					<h4><b>Actualmente tiene estos alumnos a cargo:<br></h4>
						<thead>
							<tr>
								<th class="enc">Nombre</th>
								<th class="enc">Apellido</th>
								<th class="enc">Horario</th>
								<th class="enc"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="alum" items="${alumnos}">
								<tr>
									<td width="650" class="alt-celda margina-izq">${alum.nombre}</td>	
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br>
					<br>
					<br>

							<input class=" btn btn-success"	type="submit" value="Confirmar y continuar" />
							<a><input class=" btn btn-danger" type="button" value="Cancelar" /></a>
				</form:form>
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
						
						</div>
						
						
						
						
						
						
						
						
						
						
						
						
						
					</div>
				</div>

				
			</div>
		</div>
	</section>


	
	<!--									NOS PUEDE SERVIR PARA LA PARTE DEL REGISTRAR FECHA
	<section class="section-booking bg1-pattern p-t-100 p-b-110">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 p-b-30">
					<div class="t-center">
						<span class="tit2 t-center">
							Reservation
						</span>
						<h3 class="tit3 t-center m-b-35 m-t-2">
							Book table
						</h3>
					</div>
					<form class="wrap-form-booking">
						<div class="row">
							<div class="col-md-6">
								
								<span class="txt9">
									Date
								</span>
								<div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<input class="my-calendar bo-rad-10 sizefull txt10 p-l-20" type="text" name="date">
									<i class="btn-calendar fa fa-calendar ab-r-m hov-pointer m-r-18" aria-hidden="true"></i>
								</div>
								
								<span class="txt9">
									Time
								</span>
								<div class="wrap-inputtime size12 bo2 bo-rad-10 m-t-3 m-b-23">
									
									<select class="selection-1" name="time">
										<option>9:00</option>
										<option>9:30</option>
										<option>10:00</option>
										<option>10:30</option>
										<option>11:00</option>
										<option>11:30</option>
										<option>12:00</option>
										<option>12:30</option>
										<option>13:00</option>
										<option>13:30</option>
										<option>14:00</option>
										<option>14:30</option>
										<option>15:00</option>
										<option>15:30</option>
										<option>16:00</option>
										<option>16:30</option>
										<option>17:00</option>
										<option>17:30</option>
										<option>18:00</option>
									</select>
								</div>
								
								<span class="txt9">
									People
								</span>
								<div class="wrap-inputpeople size12 bo2 bo-rad-10 m-t-3 m-b-23">
									
									<select class="selection-1" name="people">
										<option>1 person</option>
										<option>2 people</option>
										<option>3 people</option>
										<option>4 people</option>
										<option>5 people</option>
										<option>6 people</option>
										<option>7 people</option>
										<option>8 people</option>
										<option>9 people</option>
										<option>10 people</option>
										<option>11 people</option>
										<option>12 people</option>
									</select>
								</div>
							</div>
							<div class="col-md-6">
								
								<span class="txt9">
									Name
								</span>
								<div class="wrap-inputname size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<input class="bo-rad-10 sizefull txt10 p-l-20" type="text" name="name" placeholder="Name">
								</div>
								
								<span class="txt9">
									Phone
								</span>
								<div class="wrap-inputphone size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<input class="bo-rad-10 sizefull txt10 p-l-20" type="text" name="phone" placeholder="Phone">
								</div>
								
								<span class="txt9">
									Email
								</span>
								<div class="wrap-inputemail size12 bo2 bo-rad-10 m-t-3 m-b-23">
									<input class="bo-rad-10 sizefull txt10 p-l-20" type="text" name="email" placeholder="Email">
								</div>
							</div>
						</div>
						<div class="wrap-btn-booking flex-c-m m-t-6">
							
							<button type="submit" class="btn3 flex-c-m size13 txt11 trans-0-4">
								Book Table
							</button>
						</div>
					</form>
				</div>
				<div class="col-lg-6 p-b-30 p-t-18">
					<div class="wrap-pic-booking size2 bo-rad-10 hov-img-zoom m-l-r-auto">
						<img src="images/booking-01.jpg" alt="IMG-OUR">
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Review -->
	


	<!-- Footer -->
<%@ include file="../../parts/footer.jsp" %> 
	<!-- fin footer  -->
</body>
</html>
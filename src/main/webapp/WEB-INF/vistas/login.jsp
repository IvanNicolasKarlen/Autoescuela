<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ingresar</title>
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


<!-- Title Page -->
<section class="bg-title-page flex-c-m p-t-160 p-b-80 p-l-15 p-r-15"
         style="background-image: url(images/registrobanner.jpg);">
    <h2 class="tit6 t-center">
        BIENVENIDX DE NUEVO
    </h2>
</section>


<!-- Reservation -->
<section class="section-reservation bg1-pattern p-t-100 p-b-113">
    <div class="container">
        <div class="row">
            <div class="col-lg-12 p-b-30">
                <div class="t-center">
						<span class="tit2 t-center">
							Bienvenidx
						</span>

                    <h3 class="tit3 t-center m-b-35 m-t-2">
                        Ingrese sus datos
                    </h3>
                    <h4 class="t-center text-warning">${mensaje}</h4>
                    <h4 class="t-center text-warning">${error}</h4>
                </div>

                <form:form class="wrap-form-reservation size22 m-l-r-auto" method="POST" modelAttribute="usuario" action="validar-login">
                    <div class="row">
                        
                        <div class="col-md-4">
                            <span class="txt9">
									DNI
								</span>

                            <div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
                                <form:input class="bo-rad-10 sizefull txt10 p-l-20"
                                       type="number" id="dni" path="dni"></form:input>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <span class="txt9">
									Contraseña
								</span>

                            <div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
                                <form:input class="bo-rad-10 sizefull txt10 p-l-20"
                                       type="password" id="password" path="password"></form:input>
                            </div>
                        </div>
                         <div class="col-md-4">
                         <span class="txt9">
                         	Usted es...
                         </span>
                        	  <div class="wrap-inputdate pos-relative txt10 size12 bo2 bo-rad-10 m-t-3 m-b-23">
                                <select class="bo-rad-10 sizefull txt10 p-l-20"
                                       id="rol" name="rol">
                                 <option value="Alumno" >Soy Alumno</option>
                                 <option value="Instructor" >Soy Instructor</option>  
                                 <option value="Organizador" >Soy Organizador</option>  
                                 </select>
                            </div>
                       </div>

                    <div class="wrap-btn-booking flex-c-m m-t-6">
                        <!-- Button3 -->
                        <button type="submit" class="btn3 flex-c-m size13 txt11 trans-0-4">
                            Ingresar
                        </button>   
                    </div> 
                </form:form> 
                <a href="registro" class="text-center">No tengo usuario</a>
            </div>
   
        </div>

        <div class="info-reservation flex-w p-t-80">
            <div class="size23 w-full-md p-t-40 p-r-30 p-r-0-md">
                <h4 class="txt5 m-b-18">
                    Reserve by Phone
                </h4>

                <p class="size25">
                    Donec quis euismod purus. Donec feugiat ligula rhoncus, varius nisl sed,
                    tincidunt lectus.
                    <span class="txt25">Nulla vulputate</span>
                    , lectus vel volutpat efficitur, orci
                    <span class="txt25">lacus sodales</span>
                    sem, sit amet quam:
                    <span class="txt24">(001) 345 6889</span>
                </p>
            </div>

            <div class="size24 w-full-md p-t-40">
                <h4 class="txt5 m-b-18">
                    For Event Booking
                </h4>

                <p class="size26">
                    Donec feugiat ligula rhoncus:
                    <span class="txt24">(001) 345 6889</span>
                    , varius nisl sed, tinci-dunt lectus sodales sem.
                </p>
            </div>

        </div>
    </div>
</section>


<!-- Footer -->
<footer class="bg1">
    <div class="container p-t-40 p-b-70">
        <div class="row">
            <div class="col-sm-6 col-md-4 p-t-50">
                <!-- - -->
                <h4 class="txt13 m-b-33">
                    Contact Us
                </h4>

                <ul class="m-b-70">
                    <li class="txt14 m-b-14">
                        <i class="fa fa-map-marker fs-16 dis-inline-block size19"
                           aria-hidden="true"></i>
                        8th floor, 379 Hudson St, New York, NY 10018
                    </li>

                    <li class="txt14 m-b-14">
                        <i class="fa fa-phone fs-16 dis-inline-block size19" aria-hidden="true"></i>
                        (+1) 96 716 6879
                    </li>

                    <li class="txt14 m-b-14">
                        <i class="fa fa-envelope fs-13 dis-inline-block size19"
                           aria-hidden="true"></i>
                        contact@site.com
                    </li>
                </ul>

                <!-- - -->
                <h4 class="txt13 m-b-32">
                    Opening Times
                </h4>

                <ul>
                    <li class="txt14">
                        09:30 AM - 11:00 PM
                    </li>

                    <li class="txt14">
                        Every Day
                    </li>
                </ul>
            </div>

            <div class="col-sm-6 col-md-4 p-t-50">
                <!-- - -->
                <h4 class="txt13 m-b-33">
                    Latest twitter
                </h4>

                <div class="m-b-25">
						<span class="fs-13 color2 m-r-5">
							<i class="fa fa-twitter" aria-hidden="true"></i>
						</span>
                    <a href="#" class="txt15">
                        @colorlib
                    </a>

                    <p class="txt14 m-b-18">
                        Activello is a good option. It has a slider built into that displays the
                        featured image in the slider.
                        <a href="#" class="txt15">
                            https://buff.ly/2zaSfAQ
                        </a>
                    </p>

                    <span class="txt16">
							21 Dec 2017
						</span>
                </div>

                <div>
						<span class="fs-13 color2 m-r-5">
							<i class="fa fa-twitter" aria-hidden="true"></i>
						</span>
                    <a href="#" class="txt15">
                        @colorlib
                    </a>

                    <p class="txt14 m-b-18">
                        Activello is a good option. It has a slider built into that displays
                        <a href="#" class="txt15">
                            https://buff.ly/2zaSfAQ
                        </a>
                    </p>

                    <span class="txt16">
							21 Dec 2017
						</span>
                </div>
            </div>

            <div class="col-sm-6 col-md-4 p-t-50">
                <!-- - -->
                <h4 class="txt13 m-b-38">
                    Gallery
                </h4>

                <!-- Gallery footer -->
                <div class="wrap-gallery-footer flex-w">
                    <a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-01.jpg"
                       data-lightbox="gallery-footer">
                        <img src="images/photo-gallery-thumb-01.jpg" alt="GALLERY">
                    </a>

                    <a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-02.jpg"
                       data-lightbox="gallery-footer">
                        <img src="images/photo-gallery-thumb-02.jpg" alt="GALLERY">
                    </a>

                    <a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-03.jpg"
                       data-lightbox="gallery-footer">
                        <img src="images/photo-gallery-thumb-03.jpg" alt="GALLERY">
                    </a>

                    <a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-04.jpg"
                       data-lightbox="gallery-footer">
                        <img src="images/photo-gallery-thumb-04.jpg" alt="GALLERY">
                    </a>

                    <a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-05.jpg"
                       data-lightbox="gallery-footer">
                        <img src="images/photo-gallery-thumb-05.jpg" alt="GALLERY">
                    </a>

                    <a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-06.jpg"
                       data-lightbox="gallery-footer">
                        <img src="images/photo-gallery-thumb-06.jpg" alt="GALLERY">
                    </a>

                    <a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-07.jpg"
                       data-lightbox="gallery-footer">
                        <img src="images/photo-gallery-thumb-07.jpg" alt="GALLERY">
                    </a>

                    <a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-08.jpg"
                       data-lightbox="gallery-footer">
                        <img src="images/photo-gallery-thumb-08.jpg" alt="GALLERY">
                    </a>

                    <a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-09.jpg"
                       data-lightbox="gallery-footer">
                        <img src="images/photo-gallery-thumb-09.jpg" alt="GALLERY">
                    </a>

                    <a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-10.jpg"
                       data-lightbox="gallery-footer">
                        <img src="images/photo-gallery-thumb-10.jpg" alt="GALLERY">
                    </a>

                    <a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-11.jpg"
                       data-lightbox="gallery-footer">
                        <img src="images/photo-gallery-thumb-11.jpg" alt="GALLERY">
                    </a>

                    <a class="item-gallery-footer wrap-pic-w" href="images/photo-gallery-12.jpg"
                       data-lightbox="gallery-footer">
                        <img src="images/photo-gallery-thumb-12.jpg" alt="GALLERY">
                    </a>
                </div>

            </div>
        </div>
    </div>

    <div class="end-footer bg2">
        <div class="container">
            <div class="flex-sb-m flex-w p-t-22 p-b-22">
                <div class="p-t-5 p-b-5">
                    <a href="#" class="fs-15 c-white"><i class="fa fa-tripadvisor"
                                                         aria-hidden="true"></i></a>
                    <a href="#" class="fs-15 c-white"><i class="fa fa-facebook m-l-18"
                                                         aria-hidden="true"></i></a>
                    <a href="#" class="fs-15 c-white"><i class="fa fa-twitter m-l-18"
                                                         aria-hidden="true"></i></a>
                </div>

                <div class="txt17 p-r-20 p-t-5 p-b-5">
                    Copyright &copy; 2018 All rights reserved | This template is made with <i
                        class="fa fa-heart"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                </div>
            </div>
        </div>
    </div>
</footer>


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
<script src="js/main.js"></script>

</body>
</html>
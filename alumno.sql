

/*								ALUMNO					*/

use tallerwebtpfinal;



select * from clase;

select * from alumno;

select * from curso;

select * from agenda;

select * from tablacursoalumno;

select * from estadodelcurso;

select * from instructor;

select * from usuario;

select * from especialidad;



/*****************************Para el alumno ejecutar todos estos insert******************/

  Insert into Instructor (usuario_id) values (1);

 Insert into especialidad (tipo) values ("Auto"),("Moto");
 
Insert into estadodelcurso(estadodelcurso) values ("Cursando");


Insert into curso (
	 cantClasesPracticas,
	 descripcion,
     precio,
     titulo,
     especialidad_id) values (2,"Curso A", 100,"Curso Para aprender a Manejar Auto" , 1);

Insert into curso (
	 cantClasesPracticas,
	 descripcion,
     precio,
     titulo,
     especialidad_id) values (1,"Curso B", 100,"Curso Para aprender a Manejar Auto" , 1);

Insert into curso (
	 cantClasesPracticas,
	 descripcion,
     precio,
     titulo,
     especialidad_id) values (2,"Curso C", 100,"Curso Para aprender a Manejar Moto" , 2);

Insert into agenda (fecha, hora) 
values("18/09/2019", 1800),
("19/09/2019", 1300),
("20/09/2019", 1200),
("21/09/2019", 1000);

Insert into agenda (fecha, hora,alumno_id) values
("22/09/2019", 1600,1);

Insert into agenda (fecha, hora) 
values("23/09/2019", 0900),
("24/09/2019", 0900),
("25/09/2019", 0900);

/**************************************Hasta aca***************************/








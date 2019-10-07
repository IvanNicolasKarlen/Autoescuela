SELECT * FROM tallerwebtpfinal.consignaopcion;



SELECT * FROM tallerwebtpfinal.usuario;



use tallerwebtpfinal;




select * from alumno;

select * from curso;

select * from agenda;

select * from tablacursoalumno;

select * from estadodelcurso;

select * from instructor;

select * from usuario;

select * from especialidad;

select * from vehiculo;

select * from inscripcion;

select * from estadoinscripcion;


select * from estadodevehiculo;


select * from instructorvehiculoespecialidad;
/* Guardar al menos un curso*/


  Insert into Instructor (usuario_id) values (1);
 Insert into especialidad (tipo) values ("Auto"),("Moto");


insert into estadodevehiculo(estadoActual) values ("Disponible");
insert into vehiculo( modelo ,estadoDeVehiculo_id) values("Marca A",1),("Marca B",1);
insert into instructorvehiculoespecialidad(especialidad_id, instructor_id, vehiculo_id)
values(1,1,1),(1,1,2),(2,1,2);     
 
/*Insert into estadodelcurso(estadodelcurso) values ("Cursando");*/
Insert into estadoInscripcion(estado) values ("Cursando");

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



     /*Para probar que ande si finalizo el curso, correr esta sentencia*/


/*Update tablacursoalumno set estado= "Finalizado" where id=1;*/

Insert into agenda (fecha, hora,instructorvehiculoespecialidad_id) 
values("18/09/2019", 1800,1),
("19/09/2019", 1300,1),
("20/09/2019", 1200,2),
("21/09/2019", 1000,1);

Insert into agenda (fecha, hora,instructorvehiculoespecialidad_id) values
("22/09/2019", 1600,1);

Insert into agenda (fecha, hora,instructorvehiculoespecialidad_id) 
values("23/09/2019", 0900,1),
("24/09/2019", 0900,2),
("25/09/2019", 0900,3);

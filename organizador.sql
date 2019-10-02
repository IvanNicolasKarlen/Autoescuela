






/************************************************************************/
/*									ORGANIZADOR				*/
/*Esta parte es del organizador, ejecutar PRIMERO todos estos inserts*/

insert into Organizador(id)
values (3);

insert into usuario(id,apellido,dni,nombre,password,rol,organizador_id)
values  (1,"Delgado",12345678,"Candelaria",123456,"Organizador",3);
	
select * from usuario;

insert into estadodevehiculo(id, estadoActual)
VALUES	(1,"Funcionando");


/*************************** Hasta Aca *******************************/





/*Despues esto: es del ORGANIZADOR tambien*/


insert into instructor(id)
values	(1),
		(2);
        
insert into usuario(apellido,dni,nombre,password,rol,instructor_id)
values	("Diaz",24123852,"Maria",123,"Instructor",1),
		("Herrera",35987456,"Jorge",123,"Instructor",2);
        
select * from vehiculo;
select * from especialidad;
select * from instructor;

insert into instructorvehiculoespecialidad(instructor_id,vehiculo_id,especialidad_id)
VALUES	(1,1,1);
select * from instructorvehiculoespecialidad;
select * from curso;
select * from agenda;



/**********************************Fin*/
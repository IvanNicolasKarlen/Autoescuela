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
VALUES	(1,1,1),
		(2,2,2);
select * from instructorvehiculoespecialidad;
select * from curso;
select * from agenda;
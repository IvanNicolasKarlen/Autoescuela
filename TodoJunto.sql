insert into Organizador(id)
values (125);

insert into usuario(apellido,dni,nombre,password,rol,organizador_id,nombreDeUsuario)
values  ("Delgado",40123456,"Candelaria",123456,"Organizador",125,"candekn");
update organizador set usuario_id=1 where id=125;
insert into estadodevehiculo(estadoActual)
VALUES	("Funcionando");

insert into especialidad(tipo)
VALUES	("Moto"),("Auto");

insert into estadoDelCurso(estadoDelCurso)
VALUES	("Disponible"),("En Pausa");

insert into tipoDeVehiculo(tipo,especialidad_id)
VALUES	("Camioneta",2),
		("Motocicleta",1);
        
insert into vehiculo(patente,modelo,tipoDeVehiculo_id,estadoDeVehiculo_id)
VALUES	("XYZ-123","Honda 2003",2,1),
		("FGH-978","Mercedes Benz 4x4",1,1),
        ("NKJ-978","Mercedes Benz 500x",1,1),
        ("GRF-987","Scooter x32",2,1);

 Insert into estadodeagenda(estado,detalle) 
 values("Disponible","El alumno puede inscribirse"), /*1*/
 ("Ocupada","Ya hay un alumno inscripto"),/*2*/
 ("Cancelado por Alumno","El alumno cancelo la clase"),/*3*/
 ("Cancelado por Instructor","El instructor decidio cancelar la clase"),/*4*/
 ("Cancelado por Organizador","El organizador decidio cancelar la clase"),/*5*/
 ("Finalizado","El alumno ya curso esta clase"),/*6*/
 ("Clase perdida","El alumno no se presento a la clase"),/*7*/
("Cancelado por Rotura de Vehiculo","El instructor aviso que el vehiculo se rompio"),
 ("Abandonada","El alumno abandono el curso");
 
Insert into estadoInscripcion(estado) values ("Cursando"), ("Finalizado"), ("Eliminado por Alumno");
insert into instructor(id)
VALUES	(60),(70);
insert into usuario(apellido,dni,nombre,password,rol,instructor_id,nombreDeUsuario)
values 	 	("Flores",32456456,"Clara",123,"Instructor",60,"claraFlores"),
			("Paez",33156456,"Hector",123,"Instructor",70,"HectorP");
update instructor set usuario_id=2 where id=60;
update instructor set usuario_id=3 where id=70;
insert into instructorvehiculoespecialidad(especialidad_id,instructor_id,vehiculo_id)
VALUES	(1,60,1),
		(2,70,2);

insert into alumno(id) values(1);

insert into usuario(apellido,dni,nombre,password,rol,alumno_id,nombreDeUsuario)
values  ("Karlen",12345678,"Ivan","a","Alumno",1,"Ivo");
update alumno set usuario_id=4 where id=1;



/*
SELECT * FROM instructorvehiculoespecialidad;
select * from usuario;
select * from instructor;
select * from curso;
select * from estadodeagenda;
select * from alumno;
select * from agenda;
select * from especialidad;
select * from inscripcion;
select * from notificacion;
*/

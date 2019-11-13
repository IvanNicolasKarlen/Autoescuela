insert into Organizador(id)
values (125);

insert into usuario(apellido,dni,nombre,password,rol,organizador_id,nombreDeUsuario)
values  ("Delgado",40123456,"Candelaria",123456,"Organizador",125,"candekn");
	

insert into estadodevehiculo(estadoActual)
VALUES	("Disponible");

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

 Insert into estadodeagenda(estado) 
 values("Disponible"), /*1*/
 ("Ocupada"),/*2*/
 ("Cancelado por Alumno"),/*3*/
 ("Cancelado por Instructor"),/*4*/
 ("Cancelado por Organizador"),/*5*/
 ("Finalizado"),/*6*/
 ("Clase perdida"),/*7*/
 ("Abandonada");
 
Insert into estadoInscripcion(estado) values ("Cursando"), ("Finalizado");
insert into instructor(id)
VALUES	(60),(70);
insert into usuario(apellido,dni,nombre,password,rol,instructor_id,nombreDeUsuario)
values 	 	("Flores",32456456,"Clara",123,"Instructor",60,"claraFlores"),
			("Paez",33156456,"Hector",123,"Instructor",70,"HectorP");

select * from instructor;
insert into instructorvehiculoespecialidad(especialidad_id,instructor_id,vehiculo_id)
VALUES	(1,60,1),
		(2,70,2);
SELECT * FROM instructorvehiculoespecialidad;
select * from usuario;
select * from curso;
select * from estadodeagenda;
select * from alumno;

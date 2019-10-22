insert into Organizador(id)
values (125);

insert into usuario(apellido,dni,nombre,password,rol,organizador_id,nombreDeUsuario)
values  ("Delgado",40123456,"Candelaria",123456,"Organizador",125,"candekn");
	

insert into estadodevehiculo(estadoActual)
VALUES	("Funcionando");

insert into especialidad(tipo)
VALUES	("Moto"),("Auto");

insert into estadoDelCurso(estadoDelCurso)
VALUES	("Disponible"),("En Pausa");

insert into asistencia(estado)
VALUES 	("Presente"),("Ausente"),("En espera"); 


insert into tipoDeVehiculo(tipo,especialidad_id)
VALUES	("Camioneta",2),
		("Motocicleta",1);
        

insert into vehiculo(patente,modelo,tipoDeVehiculo_id,estadoDeVehiculo_id)
VALUES	("XYZ-123","Honda 2003",2,1),
		("FGH-978","Mercedes Benz 4x4",1,1),
        ("GRF-987","Scooter x32",2,1);


insert into instructor(id)
VALUES	(60),(70);
select * from especialidad;
select * from vehiculo;
select * from instructor;
insert into instructorvehiculoespecialidad(especialidad_id,instructor_id,vehiculo_id)
VALUES	(1,60,1),
		(2,70,2);
SELECT * FROM instructorvehiculoespecialidad;

select * from curso;
select * from asistencia where estado="en espera";
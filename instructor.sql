create schema tallerwebtpfinal;
use tallerwebtpfinal;

INSERT INTO instructor (id) values (1);

INSERT INTO usuario (apellido,dni,email,nombre,nombreDeUsuario,password,rol,instructor_id) 
VALUES('Pardo', 12345678, 'pardo@hotmail.com','Sebastian','SebastianPardo','123','Instructor',1);

insert into estadoDeVehiculo(estadoActual)
VALUES	("Averiado");

insert into especialidad(tipo)
VALUES	("Moto"),("Auto");

insert into tipoDeVehiculo(tipo,especialidad_id)
VALUES	("Camioneta",2),
		("Motocicleta",1);
        
insert into vehiculo(patente,modelo,tipoDeVehiculo_id,estadoDeVehiculo_id)
VALUES	("XYZ-123","Honda 2003",2,1),
		("FGH-978","Mercedes Benz 4x4",1,1),
        ("GRF-987","Scooter x32",2,1);


INSERT INTO instructorvehiculoespecialidad (especialidad_id,instructor_id,vehiculo_id)  values (1,1,1);

INSERT INTO alumno (id) values (1);
INSERT INTO alumno (id) values (2);
INSERT INTO alumno (id) values (3);
INSERT INTO alumno (id) values (4);
INSERT INTO alumno (id) values (5);

INSERT INTO inscripcion (alumno_id) values (1);
INSERT INTO inscripcion (alumno_id) values (2);
INSERT INTO inscripcion (alumno_id) values (3);
INSERT INTO inscripcion (alumno_id) values (4);
INSERT INTO inscripcion (alumno_id) values (5);


INSERT INTO alumno_inscripcion (Alumno_id,inscripciones_id) values (1,1);
INSERT INTO alumno_inscripcion (Alumno_id,inscripciones_id) values (2,2);
INSERT INTO alumno_inscripcion (Alumno_id,inscripciones_id) values (3,3);
INSERT INTO alumno_inscripcion (Alumno_id,inscripciones_id) values (4,4);
INSERT INTO alumno_inscripcion (Alumno_id,inscripciones_id) values (5,5);

INSERT INTO estadoDeAgenda (estado) values ("Disponible"),
                                           ("Cancelado por el instructor"),
                                           ("Vehiculo averiado");

INSERT INTO agenda (fecha,hora,estadoDeAgenda_id,inscripcion_id,instructorVehiculoEspecialidad_id) 
                                                                                 values ('2019-02-02',1200,2,2,1),
                                                                                        ('2019-07-02',1300,1,3,1),
                                                                                        ('2019-07-03',1600,1,4,1),
                                                                                        ('2019-07-02',1400,1,5,1);
/*INSERT INTO inscripcion_agenda (Inscripcion_id,agendas_id) values (1,1);
INSERT INTO inscripcion_agenda (Inscripcion_id,agendas_id) values (2,2);*/

INSERT INTO usuario (apellido,dni,email,nombre,nombreDeUsuario,password,rol,alumno_id)
       VALUES('Iracheta', 39625638, 'juli@hotmail.com','Julieta','JulietaIracheta','123','Alumno',2),
             ('Iracheta', 11111111, 'daysi@hotmail.com','Daysi','DaysiIracheta','123','Alumno',3),
             ('Acosta', 39055655, 'alan@hotmail.com','Alan','AlanAcosta','123','Alumno',4),
             ('Iracheta', 39625638, 'julii@hotmail.com','Julieta','JulietaIracheta','123','Alumno',5);
/* ---------------------------------------------------------------------------------------------*/
       UPDATE alumno 
       set usuario_id = 2
       where id = 2;
       
       UPDATE alumno 
       set usuario_id = 3
       where id = 3;
       
       UPDATE alumno 
       set usuario_id = 4
       where id = 4;
       
       UPDATE alumno 
       set usuario_id = 5
       where id = 5;
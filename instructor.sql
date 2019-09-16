create schema tallerwebtpfinal;

insert into instructorvehiculoespecialidad (id,agenda_id,especialidad_id,instructor_id,vehiculo_id) values (1,1,1,1,1);
insert into instructorvehiculoespecialidad (id,agenda_id,especialidad_id,instructor_id,vehiculo_id) values (2,2,2,2,2);
insert into instructorvehiculoespecialidad (id,agenda_id,especialidad_id,instructor_id,vehiculo_id) values (3,3,3,3,3instructor);

insert into instructor (id,usuario_id) values (1,1);
insert into instructor (id,usuario_id) values (2,2);
insert into instructor (id,usuario_id) values (3,3);

insert into especialidad (id,tipo,instructor_id) values(1, 'tipo1',1);
insert into especialidad (id,tipo,instructor_id) values(2, 'tipo2',2);
insert into especialidad (id,tipo,instructor_id) values(3, 'tipo3',3);

insert into vehiculo (id,estado,patente,tipo,instructor_id) values (1, 'nuevo','ASIT','tipoP',1);
insert into vehiculo (id,estado,patente,tipo,instructor_id) values (2, 'nuevo','ASITD','tipoA',2);
insert into vehiculo (id,estado,patente,tipo,instructor_id) values (3, 'nuevo','ASITA','tipoB',3);

insert into agenda (id,fecha,hora) values (1,'11-02-19', 1200);
insert into agenda (id,fecha,hora) values (1,'12-02-19', 1300);
insert into agenda (id,fecha,hora) values (1,'13-02-19', 1400);

insert into alumno (id,usuario_id) values (1,1);
insert into alumno (id,usuario_id) values (2,2);
insert into alumno (id,usuario_id) values (2,2);

insert into organizador (id,usuario_id) values (1,1);
insert into organizador (id,usuario_id) values (2,2);
insert into organizador (id,usuario_id) values (3,3);

insert into usuario (id,apellido,dni,email,nombre,password,rol,alumno_id,instructor_id,organizador_id) 
values (1, 'Pardo',12345678,'sebas@hotmail.com','Sebastian', 'contra1','Instructor',1,1,1);

insert into usuario (id,apellido,dni,email,nombre,password,rol,alumno_id,instructor_id,organizador_id) 
values (2, 'Borgeat',14725863,'andres@hotmail.com','Andres', 'contra2','Instructor',2,2,2);




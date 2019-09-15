create schema tallerwebtpfinal;

insert into instructorvehiculoespecialidad (id,agenda_id,especialidad_id,instructor_id,vehiculo_id) values (1,1,1,1,1);
insert into instructorvehiculoespecialidad (id,agenda_id,especialidad_id,instructor_id,vehiculo_id) values (2,2,2,2,2);
insert into instructorvehiculoespecialidad (id,agenda_id,especialidad_id,instructor_id,vehiculo_id) values (3,3,3,3,3);

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
create schema tallerwebtpfinal;
use tallerwebtpfinal;

-- insert into especialidad (tipo) values('tipo1');
-- insert into especialidad (tipo) values('tipo2');
-- insert into especialidad (tipo) values('tipo3');

-- insert into tipodevehiculo (tipo, especialidad_id) values ("tipo1",1);

-- insert into curso (cantClasesPracticas,cantClasesTeoricas,descripcion,precio,titulo,especialidad_id)
-- values (5,5,"descripcion1",1500,"titulo1",1);

-- insert into estadodevehiculo (estadoActual) values ("estado1");

insert into alumno (usuario_id) values (1);
insert into alumno (usuario_id) values (2);
insert into alumno (usuario_id) values (3);

insert into instructor (usuario_id) values (1);
insert into instructor (usuario_id) values (2);

-- insert into vehiculo (estado,patente,tipo,instructor_id,modelo,estadoDeVehiculo_id,tipoDeVehiculo_id) 
-- values ('nuevo','ASIT','tipoP',null,'model1',null,null);


insert into instructorvehiculoespecialidad (especialidad_id,instructor_id,vehiculo_id) values (null,1,null);
insert into instructorvehiculoespecialidad (especialidad_id,instructor_id,vehiculo_id) values (null,null,null);
insert into instructorvehiculoespecialidad (especialidad_id,instructor_id,vehiculo_id) values (null,null,null);

insert into agenda (fecha, hora, instructorVehiculoEspecialidad_id) values ("2019/11/11", 1400,1),
                                        ("2019/12/12", 1500,2),
                                        ("2019/10/10", 1700,3);

Insert into agenda (fecha, hora,alumno_id) values
("2019/10/11", 1600,1);

insert into usuario (apellido,dni,email,nombre,password,rol,alumno_id,instructor_id,organizador_id) 
             values ('Pardo',12345678,'sebas@hotmail.com','Sebastian', 'contra1','Instructor',null,null,null);

insert into usuario (apellido,dni,email,nombre,password,rol,alumno_id,instructor_id,organizador_id) 
values ('Borgeat',14725863,'andres@hotmail.com','Andres', 'contra2','Instructor',null,null,null);

insert into usuario (apellido,dni,email,nombre,password,rol,alumno_id,instructor_id,organizador_id) 
             values ('Iracheta',39625638,'juli@hotmail.com','Julieta', 'qa','Organizador',null,null,null);
             

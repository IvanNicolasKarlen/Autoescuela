create schema tallerwebtpfinal;
use tallerwebtpfinal;



insert into usuario (apellido,dni,email,nombre,password,rol) 
             values ('Iracheta',39625638,'juli@hotmail.com','Julieta', 'qa','Alumno');

insert into alumno (id,usuario_id) values (1,1);

insert into usuario (apellido,dni,email,nombre,password,rol,alumno_id) 
             values ('Pardo',12345678,'sebas@hotmail.com','Sebastian', 'contra1','Instructor',1);

insert into instructor (id,usuario_id) values (1,1);

insert into instructorvehiculoespecialidad (instructor_id) values (1);

insert into agenda (fecha, hora, instructorVehiculoEspecialidad_id) values ("2019/11/11", 1400,1),
                                        ("2019/12/12", 1500,1);

Insert into agenda (fecha, hora,alumno_id) values
("2019/10/11", 1600,1);
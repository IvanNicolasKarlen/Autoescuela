create schema tallerwebtpfinal;
use tallerwebtpfinal;

INSERT INTO instructor (id) values (1);
INSERT INTO usuario (apellido,dni,email,nombre,nombreDeUsuario,password,rol,instructor_id)
    VALUES('Pardo', 12345678, 'pardo@hotmail.com','Sebastian','SebastianPardo','123','Instructor',1);

INSERT INTO instructorvehiculoespecialidad (instructor_id)  values (1);
INSERT INTO agenda (fecha,hora,instructorVehiculoEspecialidad_id) values ('2019-02-02',1200,1),
                                                                         ('2019-02-02',1300,1),
                                                                         ('2019-05-06',1700,1);

/*ALUMNO*/

INSERT INTO alumno (id) values (1);

INSERT INTO usuario (apellido,dni,email,nombre,nombreDeUsuario,password,rol,alumno_id,instructor_id)
    VALUES('Iracheta', 39625638, 'juli@hotmail.com','Julieta','JulietaIracheta','123','Alumno',1,1);



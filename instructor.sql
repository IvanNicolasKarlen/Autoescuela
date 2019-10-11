create schema tallerwebtpfinal;
use tallerwebtpfinal;

INSERT INTO instructor (id) values (1);

INSERT INTO usuario (apellido,dni,email,nombre,nombreDeUsuario,password,rol,instructor_id) 
VALUES('Pardo', 12345678, 'pardo@hotmail.com','Sebastian','SebastianPardo','123','Instructor',1);

INSERT INTO instructorvehiculoespecialidad (instructor_id)  values (1);

INSERT INTO alumno (id) values (1);
INSERT INTO alumno (id) values (2);
INSERT INTO alumno (id) values (3);
INSERT INTO alumno (id) values (4);

INSERT INTO inscripcion (alumno_id) values (1);
INSERT INTO inscripcion (alumno_id) values (2);
INSERT INTO inscripcion (alumno_id) values (3);
INSERT INTO inscripcion (alumno_id) values (4);


INSERT INTO alumno_inscripcion (Alumno_id,inscripciones_id) values (1,1);
INSERT INTO alumno_inscripcion (Alumno_id,inscripciones_id) values (2,2);
INSERT INTO alumno_inscripcion (Alumno_id,inscripciones_id) values (3,3);
INSERT INTO alumno_inscripcion (Alumno_id,inscripciones_id) values (4,4);

INSERT INTO agenda (fecha,hora,inscripcion_id,instructorVehiculoEspecialidad_id) values ('2019-02-02',1200,2,1),
                                                                                        ('2019-07-02',1300,3,1),
                                                                                        ('2019-07-02',1600,4,1);
/*INSERT INTO inscripcion_agenda (Inscripcion_id,agendas_id) values (1,1);
INSERT INTO inscripcion_agenda (Inscripcion_id,agendas_id) values (2,2);*/

INSERT INTO usuario (apellido,dni,email,nombre,nombreDeUsuario,password,rol,alumno_id)
       VALUES('Iracheta', 39625638, 'juli@hotmail.com','Julieta','JulietaIracheta','123','Alumno',2),
             ('Iracheta', 11111111, 'daysi@hotmail.com','Daysi','DaysiIracheta','123','Alumno',3),
             ('Acosta', 39055655, 'alan@hotmail.com','Alan','AlanAcosta','123','Alumno',4);
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
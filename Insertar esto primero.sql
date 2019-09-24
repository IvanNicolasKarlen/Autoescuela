insert into Organizador(id)
values (3);

insert into usuario(id,apellido,dni,nombre,password,rol,organizador_id)
values  (1,"Delgado",12345678,"Candelaria",123456,"Organizador",3);
	
select * from usuario;

insert into estadodevehiculo(id, estadoActual)
VALUES	(1,"Funcionando");

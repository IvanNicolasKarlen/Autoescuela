# Autoescuela
Aplicacion Web para Autoescuelas

Estado: [![Build Status](https://travis-ci.org/IvanNicolasKarlen/Autoescuela.svg?branch=master)](https://travis-ci.org/IvanNicolasKarlen/Autoescuela)


App que permite que los usuarios puedan tomar clases de manejo.
Este sistema esta compuesto por los roles de:

 Organizador: Capaz de poder de alta los instructores, vehiculos, especialidades que contiene la autoescuela, crea cursos, los da de baja, crea clases semanales, mensuales o anuales, ve los alumnos que están registrados en nuestra aplicación, entre demás acciones que puede realizar.

Instructor: Capaz de llevar un seguimiento de los alumnos que tiene que darle clases, puede ver tanto el dia, horario, y con que vehiculo fue asignado a dicha clase. Tambien cuenta con estadisticas que evaluan la cantidad de horas trabajadas mensualmente y con un grafico para dicho tema.

Alumno: Capaz de anotarse a un curso de manejo y poder seleccionar los dias y horarios que quiere asistir si es que no le satisface las clases que el sistema previamente le ofrece como recomendación. Cuenta con un cronograma de los dias de cursada, puede ver solo los dias de cada curso, o, ver todo el calendario completo. Además, cuenta con un historial que le señala todas las clases que ha realizado anteriormente. Y por ultimo, cuenta con la opción de finalizar un curso, por más que no lo haya terminado de cursar, y también, poder agregar clases cada vez que cancela alguna de las cuales fue anotado.

Cada rol cuenta con la opcion de cancelar una clase, dicho asunto lo deberá de especificar luego de proceder con esta acción, y en base a esto queda un registro en la base de datos que señala quien ha cancelado esa clase (es decir, dice si fue el alumno, instructor, u organizador). Y, posteriormente y de manera automatica, se crea una clase con los mismos datos para que otro usuario pueda inscribirse en ella.

Cuenta con validaciones para que un alumno no vea lo mismo que un organizador, ni viceversa, aplicado a todos los roles existentes.

Y además, contamos con validaciones de seguridad que permiten evitar posibles intentos de ataques.

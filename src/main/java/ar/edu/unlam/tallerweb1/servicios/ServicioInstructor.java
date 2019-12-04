package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioInstructor {
	Long agregarInstructor(Instructor instructor);
	Instructor buscarInstructorPorId(Long id);
	
	
	/********************************INSTRUCTOR**********************************************/
	Instructor buscarInstructor(Long idInstructor);


	Usuario buscarUsuario(Long idInstructor);
	
}

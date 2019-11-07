package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioInstructor {
	Long agregarInstructor(Instructor instructor);
	Instructor buscarInstructorPorId(Long id);
}

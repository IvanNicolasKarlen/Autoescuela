package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface OrganizadorInstructorDao {
	Long agregarInstructor(Instructor instructor);
	Instructor buscarInstructorPorId(Long id);
}

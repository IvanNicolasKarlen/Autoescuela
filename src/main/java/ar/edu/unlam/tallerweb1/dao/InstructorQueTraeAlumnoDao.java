package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface InstructorQueTraeAlumnoDao {
	
	List<Alumno> buscarAlumnosDeInstructor(Long idInstructor,String nombre, String apellido);

	List<Usuario> buscarAlumnosDeInstructor2(Long idInstructor,String nombre, String apellido);

	
}

package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;

public interface InstructorQueTraeAlumnoDao {
	
	List<Alumno> buscarAlumnosDeInstructor(Long idInstructor);
}

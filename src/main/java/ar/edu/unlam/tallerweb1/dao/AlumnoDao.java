package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Alumno;

public interface AlumnoDao {

	/****************** INSTRUCTOR ************************/
	List<Alumno> buscarAlumnos(String nombre,String apellido);
	
	/***************************************************/
	
	Alumno buscarAlumno(Long idAlumno);

}

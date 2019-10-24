package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Alumno;

public interface ServicioAlumno {

	
	/************************************** INSTRUCTOR ****************************/
	
	List<Alumno> buscarAlumnos(String nombre,String apellido);
	
	
	/****************************************************************************/
	Alumno buscarAlumno(Long idAlumno);

}

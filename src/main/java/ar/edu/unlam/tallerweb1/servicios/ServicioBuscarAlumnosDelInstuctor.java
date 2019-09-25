package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioBuscarAlumnosDelInstuctor {
	
	List <Alumno> buscarAlumnosDeInstructor (Long idInstructor,String nombre, String apellido);
	
}

package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface ServicioOrganizadorInscripcion {
	List <Inscripcion> traerInscripcionesDeUnCurso(Curso curso);
	
}

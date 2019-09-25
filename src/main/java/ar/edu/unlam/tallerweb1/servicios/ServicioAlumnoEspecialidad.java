package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;

public interface ServicioAlumnoEspecialidad {

	Especialidad consultarEspecialidadCursoElegido(Curso cursoElegido);

}

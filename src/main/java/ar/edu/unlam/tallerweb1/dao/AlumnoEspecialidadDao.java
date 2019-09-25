package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;

public interface AlumnoEspecialidadDao {

	Especialidad consultarEspecialidadCursoElegido(Curso cursoElegido);

}

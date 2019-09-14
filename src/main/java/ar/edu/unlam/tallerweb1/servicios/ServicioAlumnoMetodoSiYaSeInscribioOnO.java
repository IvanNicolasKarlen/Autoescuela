package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

public interface ServicioAlumnoMetodoSiYaSeInscribioOnO {

	List<TablaCursoAlumno> consultarSiYaSeInscribioAUnCurso(Alumno alumno, Curso cursoElegido);

}

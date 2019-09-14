package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

public interface ServicioAlumnoMetodoGuardaCurso {

	void guardarCurso(Alumno alumno, Curso cursoElegido, TablaCursoAlumno cursoAlumno);

}

package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

public interface ServicioAlumnoMetodoFinalizoCurso {

	TablaCursoAlumno consultarSiFinalizoSuCurso(Alumno alumno, Curso cursoElegido);

}

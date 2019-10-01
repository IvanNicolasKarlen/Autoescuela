package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface ServicioAlumnoCurso {

	List<Curso> buscarCursos();

	Curso buscarCurso(Curso cursoElegido);

	List<Inscripcion> consultarSiYaSeInscribioAUnCurso(Long idAlumno, EstadoDelCurso estado,
			Especialidad especialidad);

	void guardarCurso(Alumno alumno, Curso cursoElegido, Inscripcion cursoAlumno, EstadoDelCurso estado);

}

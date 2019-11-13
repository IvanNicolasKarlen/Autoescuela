package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface ServicioCurso {


	
	Long agregarCurso(Curso curso);
	Curso buscarCurso(Curso curso);
	List<Curso> traerListaDeCursos();
	Curso buscarCursoPorId(Long cursoid);
	void eliminarCurso(Curso curso);
	void modificarCurso(Curso curso);
	List<Curso> traerCursosPorEspecialidad(String especialidad);
}
package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface CursoDao {

	List<Curso> buscarCursos();

	Curso buscarCurso(Long cursoElegido);

	


}

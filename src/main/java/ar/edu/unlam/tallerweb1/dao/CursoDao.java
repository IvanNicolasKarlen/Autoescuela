package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Curso;

public interface CursoDao {

	List<Curso> buscarCursos();

	Curso buscarCurso(Long cursoElegido);

}

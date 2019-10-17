package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Curso;

public interface ServicioCurso {

	List<Curso> buscarCursos();

	Curso buscarCurso(Long id);

}

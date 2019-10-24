package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Curso;

public interface OrganizadorCursoDao {

	
	/************************************ORGANIZADOR***************************/
	Long agregarCurso(Curso curso);

	Curso buscarCurso(Curso curso);
	
	List<Curso> traerListaDeCursos();

	Curso buscarCursoPorId(Long cursoid);
	
	void eliminarCurso(Curso curso);}

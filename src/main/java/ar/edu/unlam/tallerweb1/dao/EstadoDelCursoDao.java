package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;

public interface EstadoDelCursoDao {
	List<EstadoDelCurso> traerListaDeEstadosDelCurso();
	EstadoDelCurso traerEstadoDelCursoPorNombre(String estado);
	EstadoDelCurso traerEstadoDelCursoPorId(Long id);
}
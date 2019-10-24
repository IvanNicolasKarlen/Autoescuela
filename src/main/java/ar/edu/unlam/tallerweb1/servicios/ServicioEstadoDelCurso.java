package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;

public interface ServicioEstadoDelCurso {
	List<EstadoDelCurso> traerListaDeEstadoDeLosCursos();
	EstadoDelCurso traerEstadoDelCursoPorNombre(String estado);
	EstadoDelCurso traerEstadoDelCursoPorId(Long id);
}

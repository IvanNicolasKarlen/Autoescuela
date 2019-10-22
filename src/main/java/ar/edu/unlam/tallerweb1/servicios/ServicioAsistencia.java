package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Asistencia;

public interface ServicioAsistencia {
	List<Asistencia> traerListaDeAsistencia();
	Asistencia traerAsistenciaPorNombre(String estado);
	Asistencia traerAsistenciaPorId(Long id);
}

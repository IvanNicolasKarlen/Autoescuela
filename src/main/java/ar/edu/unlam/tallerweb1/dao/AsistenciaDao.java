package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Asistencia;

public interface AsistenciaDao {
	List<Asistencia> traerListaDeAsistencia();
	Asistencia traerAsistenciaPorNombre(String estado);
	Asistencia traerAsistenciaPorId(Long id);
}

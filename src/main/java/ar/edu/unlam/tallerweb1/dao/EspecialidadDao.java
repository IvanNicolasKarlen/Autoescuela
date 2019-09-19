package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;

public interface EspecialidadDao {
	List<Especialidad> traerListaDeEspecialidades();
	Especialidad traerEspecialidadPorId(Long id);
}

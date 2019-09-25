package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;

public interface ServicioEspecialidad {
	List<Especialidad> traerListaDeEspecialidades();
	Especialidad traerEspecialidadPorId(Long id);
	Especialidad traerEspecialidadPorNombre(String tipoEsp);
	Long guardarEspecialidad(Especialidad especialidad);
}

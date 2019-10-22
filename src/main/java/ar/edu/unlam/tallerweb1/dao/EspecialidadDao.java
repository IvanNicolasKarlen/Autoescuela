package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.TipoDeVehiculo;

public interface EspecialidadDao {
	List<Especialidad> traerListaDeEspecialidades();
	Especialidad traerEspecialidadPorId(Long id);
	Especialidad traerEspecialidadPorNombre(String tipoEsp);
	Long guardarEspecialidad(Especialidad especialidad);
	List<Especialidad> traerEspecialidadesQueUnInstructorNoTenga(Long idInstructor);
}

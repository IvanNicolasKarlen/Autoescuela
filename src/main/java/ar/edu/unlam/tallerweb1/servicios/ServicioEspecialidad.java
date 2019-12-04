package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioEspecialidad {
	
	/***************************************ORGANIZADOR*********************/
	List<Especialidad> traerListaDeEspecialidades();
	Especialidad traerEspecialidadPorId(Long id);
	Especialidad traerEspecialidadPorNombre(String tipoEsp);
	Long guardarEspecialidad(Especialidad especialidad);
	List<Especialidad> traerEspecialidadesQueUnInstructorNoTenga(Instructor ins);

	
	}
package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;

public interface IveDao {
	Long guardarIve(InstructorVehiculoEspecialidad ive);
	List<InstructorVehiculoEspecialidad> traerListaIve();
	List<InstructorVehiculoEspecialidad> traerListaIvePorEspecialidad(Especialidad especialidad);
}

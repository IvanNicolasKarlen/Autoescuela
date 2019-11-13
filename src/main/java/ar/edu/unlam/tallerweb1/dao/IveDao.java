package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;

public interface IveDao {
	
	/***********************************ORGANIZADOR**********************************/
	Long guardarIve(InstructorVehiculoEspecialidad ive);
	List<InstructorVehiculoEspecialidad> traerListaIve();
	List<InstructorVehiculoEspecialidad> traerListaIvePorEspecialidad(Especialidad especialidad);
	InstructorVehiculoEspecialidad traerIvePorInstructorEspecialidad(Especialidad esp, Instructor ins);
	InstructorVehiculoEspecialidad buscarIvePorId(Long idIVE);
	List<InstructorVehiculoEspecialidad> traerIvePorInstructor(Instructor ins);
}
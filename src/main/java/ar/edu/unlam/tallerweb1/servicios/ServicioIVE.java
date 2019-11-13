package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;

public interface ServicioIVE {
	/****************************ORGANIZADOR******************************/
	Long guardarIve(InstructorVehiculoEspecialidad ive);
	List <InstructorVehiculoEspecialidad> traerListaIve();
	List <InstructorVehiculoEspecialidad> traerListaIvePorEspecialidad(Especialidad especialidad);
	InstructorVehiculoEspecialidad traerIveProInstructorEspecialidad(Especialidad esp, Instructor ins);
	InstructorVehiculoEspecialidad buscarIvePorId(Long idIVE);
	List <InstructorVehiculoEspecialidad> traerListaIvePorInstructor(Instructor instructor);
}
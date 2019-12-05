package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.IveDao;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;

@Service
@Transactional
public class ServicioIVEImpl implements ServicioIVE {
	@Inject
	private IveDao iveDao;
	
	/********************************organizador************************/
	@Override
	public Long guardarIve(InstructorVehiculoEspecialidad ive) {
		return iveDao.guardarIve(ive);
	}
	@Override
	public List<InstructorVehiculoEspecialidad> traerListaIve() {
		return iveDao.traerListaIve();
	}
	@Override
	public List<InstructorVehiculoEspecialidad> traerListaIvePorEspecialidad(Especialidad especialidad) {
		return iveDao.traerListaIvePorEspecialidad(especialidad);
	}
	@Override
	public InstructorVehiculoEspecialidad traerIveProInstructorEspecialidad(Especialidad esp, Instructor ins) {
		return iveDao.traerIvePorInstructorEspecialidad(esp,ins);
	}
	@Override
	public InstructorVehiculoEspecialidad buscarIvePorId(Long idIVE) {
		return iveDao.buscarIvePorId(idIVE);
	}
	@Override
	public List<InstructorVehiculoEspecialidad> traerListaIvePorInstructor(Instructor instructor) {
		return iveDao.traerIvePorInstructor(instructor);
	}



	
}
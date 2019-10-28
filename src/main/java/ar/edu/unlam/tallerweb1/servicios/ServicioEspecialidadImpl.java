package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EspecialidadDao;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
@Service
@Transactional
public class ServicioEspecialidadImpl implements ServicioEspecialidad {
	@Inject
	private EspecialidadDao especialidadDao;

	/***************************************ORGANIZADOR*********************/
	
	@Override
	public List<Especialidad> traerListaDeEspecialidades() {
		return especialidadDao.traerListaDeEspecialidades();
	}

	@Override
	public Especialidad traerEspecialidadPorId(Long id) {
		return especialidadDao.traerEspecialidadPorId(id);
		
	}

	@Override
	public Especialidad traerEspecialidadPorNombre(String tipoEsp) {
		return especialidadDao.traerEspecialidadPorNombre(tipoEsp);
	}

	@Override
	public Long guardarEspecialidad(Especialidad especialidad) {
		return especialidadDao.guardarEspecialidad(especialidad);
	}

	@Override
	public List<Especialidad> traerEspecialidadesQueUnInstructorNoTenga(Long idInstructor) {
		return especialidadDao.traerEspecialidadesQueUnInstructorNoTenga(idInstructor);
	}
}

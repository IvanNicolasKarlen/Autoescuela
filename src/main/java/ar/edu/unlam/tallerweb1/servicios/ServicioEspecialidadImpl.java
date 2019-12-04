package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EspecialidadDao;
import ar.edu.unlam.tallerweb1.dao.IveDao;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
@Service
@Transactional
public class ServicioEspecialidadImpl implements ServicioEspecialidad {
	@Inject
	private EspecialidadDao especialidadDao;
	@Inject
	private IveDao iveDao;
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
	public List<Especialidad> traerEspecialidadesQueUnInstructorNoTenga(Instructor ins) {
		List <InstructorVehiculoEspecialidad> listaIve = iveDao.traerIvePorInstructor(ins);
		List <Especialidad> especialidades = this.traerListaDeEspecialidades();
		List <Especialidad> especialidadesRetorno = new ArrayList<Especialidad>();
		for(Especialidad esp:especialidades){
			for(InstructorVehiculoEspecialidad ive:listaIve){
				if(!(ive.getEspecialidad().equals(esp))){
					especialidadesRetorno.add(esp);
				}
			}
		}
		
		if(listaIve.isEmpty()){
			return especialidades;
		}
		else {
			return especialidadesRetorno;
		}
	}
}
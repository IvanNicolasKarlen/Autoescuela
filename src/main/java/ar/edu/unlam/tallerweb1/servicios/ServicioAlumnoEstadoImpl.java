package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoEstadoDao;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;

@Service("ServicioAlumnoEstado")
@Transactional
public class ServicioAlumnoEstadoImpl implements ServicioAlumnoEstado {
	
	@Inject
	private AlumnoEstadoDao alumnoEstadoDao;

	@Override
	public EstadoDelCurso buscarEstadoCursando() {
		// TODO Auto-generated method stub
		return alumnoEstadoDao.buscarEstadoCursando();
	}
	
	

}

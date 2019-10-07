package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoEstadoDao;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;

@Service("ServicioAlumnoEstado")
@Transactional
public class ServicioAlumnoEstadoImpl implements ServicioAlumnoEstado {
	
	@Inject
	private AlumnoEstadoDao alumnoEstadoDao;

	@Override
	public EstadoInscripcion buscarEstadoCursando() {
		// TODO Auto-generated method stub
		return alumnoEstadoDao.buscarEstadoCursando();
	}
	
	

}

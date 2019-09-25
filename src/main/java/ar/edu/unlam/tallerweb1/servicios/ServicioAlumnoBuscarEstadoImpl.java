package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoBuscarAlumnoDao;
import ar.edu.unlam.tallerweb1.dao.AlumnoBuscarEstadoDao;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;

@Service("ServicioAlumnoBuscarEstado")
@Transactional
public class ServicioAlumnoBuscarEstadoImpl implements ServicioAlumnoBuscarEstado {

	@Inject
	private AlumnoBuscarEstadoDao servicioBuscarEstadoDao;

	@Override
	public EstadoDelCurso buscarEstadoCursando() {
		return servicioBuscarEstadoDao.buscarEstadoCursando();
	}

	

	

	

}

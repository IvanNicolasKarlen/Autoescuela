package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AsistenciaDao;
import ar.edu.unlam.tallerweb1.modelo.Asistencia;

@Service
@Transactional
public class ServicioAsistenciaImpl implements ServicioAsistencia {
	@Inject
	private AsistenciaDao asistenciaDao;
	@Override
	public List<Asistencia> traerListaDeAsistencia() {
		return asistenciaDao.traerListaDeAsistencia();
	}

	@Override
	public Asistencia traerAsistenciaPorNombre(String estado) {
		return asistenciaDao.traerAsistenciaPorNombre(estado);
	}

	@Override
	public Asistencia traerAsistenciaPorId(Long id) {
		return asistenciaDao.traerAsistenciaPorId(id);
	}

}

package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EstadoDelCursoDao;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;

@Service
@Transactional
public class ServicioEstadoDeCursoImpl implements ServicioEstadoDelCurso {
	@Inject
	private EstadoDelCursoDao estadoDelCursoDao; 
	
	@Override
	public List<EstadoDelCurso> traerListaDeEstadoDeLosCursos() {
		return estadoDelCursoDao.traerListaDeEstadosDelCurso();
	}

	@Override
	public EstadoDelCurso traerEstadoDelCursoPorNombre(String estado) {
		return estadoDelCursoDao.traerEstadoDelCursoPorNombre(estado);
	}

	@Override
	public EstadoDelCurso traerEstadoDelCursoPorId(Long id) {
		return estadoDelCursoDao.traerEstadoDelCursoPorId(id);
	}

}

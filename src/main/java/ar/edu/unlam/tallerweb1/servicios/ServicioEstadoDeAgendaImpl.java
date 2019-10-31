package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EstadoDeAgendaDao;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;

@Service
@Transactional
public class ServicioEstadoDeAgendaImpl implements ServicioEstadoDeAgenda {
	@Inject
	private EstadoDeAgendaDao estadoDeAgendaDao;

	@Override
	public List<EstadoDeAgenda> traerListaDeEstadoDeAgenda() {
		return estadoDeAgendaDao.traerListaDeEstadoDeAgenda();
	}

	@Override
	public EstadoDeAgenda traerEstadoDeAgendaPorNombre(String estado) {
		return estadoDeAgendaDao.traerEstadoDeAgendaPorNombre(estado);
	}

	@Override
	public EstadoDeAgenda traerEstadoDeAgendaPorId(Long id) {
		return estadoDeAgendaDao.traerEstadoDeAgendaPorId(id);
	}

}

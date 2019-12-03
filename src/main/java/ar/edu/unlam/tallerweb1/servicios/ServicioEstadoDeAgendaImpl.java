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
	
/**************************************INSTRUCTOR*****************************************/
	
	@Override
	public List<EstadoDeAgenda> traerListaDeEstadoDeAgendaParaInstructor() {
		return estadoDeAgendaDao.traerListaDeEstadoDeAgendaParaInstructor();
	}
	
	@Override
	public List<EstadoDeAgenda> traerDetalleDeEstadoDeAgendaParaInstructor() {
		return estadoDeAgendaDao.traerDetalleDeEstadoDeAgendaParaInstructor();
	}

//	
//	@Override
//	public void updateEstadoDeAgenda(EstadoDeAgenda mensaje) {
//		estadoDeAgendaDao.updateEstadoDeAgenda(mensaje);		
//	}
//	
//	@Override
//	public EstadoDeAgenda traerListaDeOcupados() {
//		return estadoDeAgendaDao.traerListaDeOcupados();	
//		
//	}
	/*************************************************************************************/
	
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

	@Override
	public List<EstadoDeAgenda> traerListaDeEstadoDeAgendaParaOrganizador() {
		return estadoDeAgendaDao.traerListaDeEstadoDeAgendaParaOrganizador();
	}






}
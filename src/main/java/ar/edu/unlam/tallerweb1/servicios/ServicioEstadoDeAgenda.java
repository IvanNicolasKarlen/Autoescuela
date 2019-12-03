package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;

public interface ServicioEstadoDeAgenda {
	
	/************************************************INSTRUCTOR***************************/
	List<EstadoDeAgenda> traerListaDeEstadoDeAgendaParaInstructor();
	List<EstadoDeAgenda> traerDetalleDeEstadoDeAgendaParaInstructor();
//	void updateEstadoDeAgenda(EstadoDeAgenda mensaje);
//	EstadoDeAgenda traerListaDeOcupados();

	
	
	/***************************************************************************/

	List<EstadoDeAgenda> traerListaDeEstadoDeAgenda();
	EstadoDeAgenda traerEstadoDeAgendaPorNombre(String estado);
	EstadoDeAgenda traerEstadoDeAgendaPorId(Long id);
	List<EstadoDeAgenda> traerListaDeEstadoDeAgendaParaOrganizador();
}
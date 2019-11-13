package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;

public interface ServicioEstadoDeAgenda {
	
	/************************************************INSTRUCTOR***************************/
	List<EstadoDeAgenda> traerListaDeEstadoDeAgendaMenosEstadoDisponible();
	
	
	
	/***************************************************************************/

	List<EstadoDeAgenda> traerListaDeEstadoDeAgenda();
	EstadoDeAgenda traerEstadoDeAgendaPorNombre(String estado);
	EstadoDeAgenda traerEstadoDeAgendaPorId(Long id);
}
package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;

public interface EstadoDeAgendaDao {

	
	/********************************ORGANIZADOR**********************************/
	
	List<EstadoDeAgenda> traerListaDeEstadoDeAgenda();
	EstadoDeAgenda traerEstadoDeAgendaPorNombre(String estado);
	EstadoDeAgenda traerEstadoDeAgendaPorId(Long id);
	
	
	/*******************************ALUMNO*****************************************/
	EstadoDeAgenda buscarEstadoOcupado();
	EstadoDeAgenda traigoElEstadoCanceladaPorAlumno();
	EstadoDeAgenda traigoElEstadoDisponible();
	EstadoDeAgenda traigoElEstadoFinalizado();
	EstadoDeAgenda traigoElEstadoAbandonada();
	EstadoDeAgenda traigoElEstadoOcupada();
	EstadoDeAgenda buscarEstadoPerdida();

}

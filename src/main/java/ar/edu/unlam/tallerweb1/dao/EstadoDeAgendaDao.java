package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;

public interface EstadoDeAgendaDao {

/********************************INSTRUCTOR**********************************/
	
	List<EstadoDeAgenda> traerListaDeEstadoDeAgendaParaInstructor();
	List<EstadoDeAgenda> traerDetalleDeEstadoDeAgendaParaInstructor();
//	void updateEstadoDeAgenda(EstadoDeAgenda mensaje);	
//	EstadoDeAgenda traerListaDeOcupados();
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
	List<EstadoDeAgenda> traerListaDeEstadoDeAgendaParaOrganizador();
}
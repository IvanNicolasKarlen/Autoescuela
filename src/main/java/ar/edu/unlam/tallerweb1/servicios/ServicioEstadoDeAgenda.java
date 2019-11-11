package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;

public interface ServicioEstadoDeAgenda {
	
	List<EstadoDeAgenda> traerListaDeEstadoDeAgenda();
	EstadoDeAgenda traerEstadoDeAgendaPorNombre(String estado);
	EstadoDeAgenda traerEstadoDeAgendaPorId(Long id);
}

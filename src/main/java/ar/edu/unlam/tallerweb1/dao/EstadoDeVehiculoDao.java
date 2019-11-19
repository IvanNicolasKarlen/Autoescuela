package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;

public interface EstadoDeVehiculoDao {
	EstadoDeVehiculo buscarEstadoPorEstadoActual(String estadoActual);
	Long guardarEstado(EstadoDeVehiculo estadoDeVehiculo);
	EstadoDeVehiculo buscarEstadoPorId(Long estadoId);
	
/********************************INSTRUCTOR**********************************/
	
	List<EstadoDeVehiculo> traerListaDeEstadoDeVehiculo();
	void updateEstadoDeVehiculo(EstadoDeVehiculo idEstadoDeVehiculo);	
	EstadoDeVehiculo traerEstadoVehiculoPorNombre(String noFuncionando);
}

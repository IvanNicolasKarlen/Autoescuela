package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;

public interface ServicioEstadoDeVehiculo {
			EstadoDeVehiculo buscarEstadoPorEstadoActual(String estadoActual);
			Long guardarEstado(EstadoDeVehiculo estadoDeVehiculo);
			EstadoDeVehiculo buscarEstadoPorId(Long estadoId);
			
			/************************************************INSTRUCTOR***************************/
			List<EstadoDeVehiculo> traerListaDeEstadoDeVehiculo();
			void updateEstadoDeVehiculo(EstadoDeVehiculo idEstadoDeVehiculo);
			EstadoDeVehiculo traerEstadoVehiculoPorNombre(String noFuncionando);

}

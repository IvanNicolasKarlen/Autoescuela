package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;

public interface ServicioEstadoDeVehiculo {
			EstadoDeVehiculo buscarEstadoPorEstadoActual(String estadoActual);
			Long guardarEstado(EstadoDeVehiculo estadoDeVehiculo);
			EstadoDeVehiculo buscarEstadoPorId(Long estadoId);
}

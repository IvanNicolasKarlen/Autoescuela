package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;

public interface EstadoDeVehiculoDao {
	EstadoDeVehiculo buscarEstadoPorEstadoActual(String estadoActual);
	Long guardarEstado(EstadoDeVehiculo estadoDeVehiculo);
}

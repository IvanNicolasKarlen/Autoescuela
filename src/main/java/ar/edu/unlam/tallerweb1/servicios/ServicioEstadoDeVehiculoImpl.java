package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.dao.EstadoDeVehiculoDao;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;

@Service
public class ServicioEstadoDeVehiculoImpl implements ServicioEstadoDeVehiculo {
	@Inject
	private EstadoDeVehiculoDao estadoDeVehiculoDao;
	@Override
	public EstadoDeVehiculo buscarEstadoPorEstadoActual(String estadoActual) {
		return estadoDeVehiculoDao.buscarEstadoPorEstadoActual(estadoActual);
	}
	@Override
	public Long guardarEstado(EstadoDeVehiculo estadoDeVehiculo) {
		return estadoDeVehiculoDao.guardarEstado(estadoDeVehiculo);
	}
	
}

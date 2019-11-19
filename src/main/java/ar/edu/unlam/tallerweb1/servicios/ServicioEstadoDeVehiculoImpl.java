package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.EstadoDeVehiculoDao;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;

@Service
@Transactional
public class ServicioEstadoDeVehiculoImpl implements ServicioEstadoDeVehiculo {
	@Inject
	private EstadoDeVehiculoDao estadoDeVehiculoDao;

	/*******************************ORGANIZADOR***********************/
	@Override
	public EstadoDeVehiculo buscarEstadoPorEstadoActual(String estadoActual) {
		return estadoDeVehiculoDao.buscarEstadoPorEstadoActual(estadoActual);
	}
	@Override
	public Long guardarEstado(EstadoDeVehiculo estadoDeVehiculo) {
		return estadoDeVehiculoDao.guardarEstado(estadoDeVehiculo);
	}
	@Override
	public EstadoDeVehiculo buscarEstadoPorId(Long estadoId) {
		return estadoDeVehiculoDao.buscarEstadoPorId(estadoId);
	}
	/********************************INSTRUCTOR***********************************/
	@Override
	public List<EstadoDeVehiculo> traerListaDeEstadoDeVehiculo() {
		return estadoDeVehiculoDao.traerListaDeEstadoDeVehiculo();

	}
	@Override
	public void updateEstadoDeVehiculo(EstadoDeVehiculo idEstadoDeVehiculo) {
		estadoDeVehiculoDao.updateEstadoDeVehiculo(idEstadoDeVehiculo);
		
	}
	@Override
	public EstadoDeVehiculo traerEstadoVehiculoPorNombre(String noFuncionando) {
		return estadoDeVehiculoDao.traerEstadoVehiculoPorNombre(noFuncionando);

	}

	
}

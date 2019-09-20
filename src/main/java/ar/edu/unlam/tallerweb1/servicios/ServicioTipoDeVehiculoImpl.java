package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.dao.TipoDeVehiculoDao;
import ar.edu.unlam.tallerweb1.modelo.TipoDeVehiculo;

@Service
public class ServicioTipoDeVehiculoImpl implements ServicioTipoDeVehiculo {
	@Inject
	private TipoDeVehiculoDao tipoDeVehiculoDao;

	@Override
	public List<TipoDeVehiculo> traerTiposDeVehiculos() {
		return tipoDeVehiculoDao.traerTiposDeVehiculos();
	}
}

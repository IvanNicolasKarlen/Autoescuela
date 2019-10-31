package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.TipoDeVehiculoDao;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.TipoDeVehiculo;

@Service
@Transactional
public class ServicioTipoDeVehiculoImpl implements ServicioTipoDeVehiculo {
	@Inject
	private TipoDeVehiculoDao tipoDeVehiculoDao;

	@Override
	public List<TipoDeVehiculo> traerTiposDeVehiculos() {
		return tipoDeVehiculoDao.traerTiposDeVehiculos();
	}

	@Override
	public TipoDeVehiculo buscarTipoDeVehiculo(TipoDeVehiculo tipoVehiculo) {
		return tipoDeVehiculoDao.buscarTipoDeVehiculo(tipoVehiculo);
	}

	@Override
	public Long guardarTipoDeVehiculo(TipoDeVehiculo tipoVehiculo) {
		return tipoDeVehiculoDao.guardarTipoDeVehiculo(tipoVehiculo);
	}

	@Override
	public List<TipoDeVehiculo> buscarTipoDeVehiculoPorEspecialidad(Especialidad varEspecialidad) {
		return tipoDeVehiculoDao.buscarTipoDeVehiculoPorEspecialidad(varEspecialidad);
	}
}

package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.TipoDeVehiculo;

public interface TipoDeVehiculoDao {

	List<TipoDeVehiculo> traerTiposDeVehiculos();

	TipoDeVehiculo buscarTipoDeVehiculo(TipoDeVehiculo tipoVehiculo);

	Long guardarTipoDeVehiculo(TipoDeVehiculo tipoVehiculo);

	List<TipoDeVehiculo> buscarTipoDeVehiculoPorEspecialidad(Especialidad varEspecialidad);

}

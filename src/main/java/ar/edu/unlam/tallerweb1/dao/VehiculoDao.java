package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;

public interface VehiculoDao {
	List<Vehiculo> obtenerVehiculoPorEspecialidad(Especialidad especialidad);
	Vehiculo buscarVehiculo(Vehiculo vehiculo);
	String guardarVehiculo(Vehiculo vehiculo);
}

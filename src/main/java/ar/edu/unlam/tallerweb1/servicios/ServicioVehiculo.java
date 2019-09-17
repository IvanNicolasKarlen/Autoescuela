package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;

public interface ServicioVehiculo {
	List<Vehiculo> obtenerVehiculoPorEspecialidad(Especialidad especialidad);
	Vehiculo buscarVehiculo(Vehiculo vehiculo);
	String guardarVehiculo(Vehiculo vehiculo);
}

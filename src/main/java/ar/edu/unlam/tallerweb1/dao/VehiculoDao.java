package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;

public interface VehiculoDao {
	/**********************************ORGANIZADOR*****************************/
	List<Vehiculo> obtenerVehiculoPorEspecialidad(Especialidad especialidad);
	Vehiculo buscarVehiculo(Vehiculo vehiculo);
	Long guardarVehiculo(Vehiculo vehiculo);
	Vehiculo buscarVehiculoPorId(Long id);
	List<Vehiculo> obtenerVehiculosSinInstructorPorEspecialidad(Especialidad esp);
}

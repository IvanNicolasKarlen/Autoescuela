package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;

public interface ServicioVehiculo {
	
	/********************************Instructor**************************/
	List<EstadoDeVehiculo> traerVehiculos(Long idAgenda);
	void updateVehiculo(Vehiculo idEstadoDeVehiculo);

	
	/********************************ORGANIZADOR**************************/
	List<Vehiculo> obtenerVehiculoPorEspecialidad(Especialidad especialidad);
	Vehiculo buscarVehiculo(Vehiculo vehiculo);
	Long guardarVehiculo(Vehiculo vehiculo);
	Vehiculo buscarVehiculoPorId(Long id);
	List<Vehiculo> obtenerVehiculosSinInstructorPorEspecialidad(Especialidad esp);
}
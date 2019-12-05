package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;

public interface VehiculoDao {
	
	
	/*************MOCK**********/
	void setSessionFactory(SessionFactory sessionFactory);
	
	/**********************************INSTRUCTOR*****************************/

	List<EstadoDeVehiculo> traerVehiculos(Long idAgenda);
	void updateVehiculo(Vehiculo idEstadoDeVehiculo);	

	/**********************************ORGANIZADOR*****************************/
	List<Vehiculo> obtenerVehiculoPorEspecialidad(Especialidad especialidad);
	Vehiculo buscarVehiculo(Vehiculo vehiculo);
	Long guardarVehiculo(Vehiculo vehiculo);
	Vehiculo buscarVehiculoPorId(Long id);
	List<Vehiculo> obtenerVehiculosSinInstructorPorEspecialidad(Especialidad esp);
}

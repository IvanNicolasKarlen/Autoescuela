package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;

public interface EstadoDeVehiculoDao {
	
	/*************MOCK**********/
	void setSessionFactory(SessionFactory sessionFactory);
	
	
	EstadoDeVehiculo buscarEstadoPorEstadoActual(String estadoActual);
	Long guardarEstado(EstadoDeVehiculo estadoDeVehiculo);
	EstadoDeVehiculo buscarEstadoPorId(Long estadoId);
	
/********************************INSTRUCTOR**********************************/
	
	List<EstadoDeVehiculo> traerListaDeEstadoDeVehiculo();
	void updateEstadoDeVehiculo(EstadoDeVehiculo idEstadoDeVehiculo);	
	EstadoDeVehiculo traerEstadoVehiculoPorNombre(String noFuncionando);
}

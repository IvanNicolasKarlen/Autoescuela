package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;
@Repository
public class EstadoDeVehiculoDaoImpl implements EstadoDeVehiculoDao {
	@Inject
	private SessionFactory sessionFactory;
	
	
	/****************************ORGANIZADOR**********************************/
	@Override
	public EstadoDeVehiculo buscarEstadoPorEstadoActual(String estadoActual) {
		return (EstadoDeVehiculo) sessionFactory.getCurrentSession().createCriteria(EstadoDeVehiculo.class)
				.add(Restrictions.eq("estadoActual", estadoActual)).uniqueResult();
	}
	@Override
	public Long guardarEstado(EstadoDeVehiculo estadoDeVehiculo) {
		return (Long)sessionFactory.getCurrentSession().save(estadoDeVehiculo);
	}
	@Override
	public EstadoDeVehiculo buscarEstadoPorId(Long estadoId) {
		final Session session = sessionFactory.getCurrentSession();
		return (EstadoDeVehiculo)session.get(EstadoDeVehiculo.class, estadoId);
	}
	
	/**********************************INSTRUCTOR*****************************************/

	@Override
	public List<EstadoDeVehiculo> traerListaDeEstadoDeVehiculo() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(EstadoDeVehiculo.class)
				.list();
	}
	@Override
	public void updateEstadoDeVehiculo(EstadoDeVehiculo idEstadoDeVehiculo) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.update(idEstadoDeVehiculo);
		
	}
	@Override
	public EstadoDeVehiculo traerEstadoVehiculoPorNombre(String noFuncionando) {
		return (EstadoDeVehiculo) sessionFactory.getCurrentSession().createCriteria(EstadoDeVehiculo.class)
				.add(Restrictions.eq("estadoActual", noFuncionando)).uniqueResult();
	}
	

}

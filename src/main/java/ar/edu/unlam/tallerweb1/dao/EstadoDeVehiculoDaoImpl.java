package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;
@Repository
public class EstadoDeVehiculoDaoImpl implements EstadoDeVehiculoDao {
	@Inject
	private SessionFactory sessionFactory;
	@Override
	public EstadoDeVehiculo buscarEstadoPorEstadoActual(String estadoActual) {
		return (EstadoDeVehiculo) sessionFactory.getCurrentSession().createCriteria(EstadoDeVehiculo.class)
				.add(Restrictions.eq("estadoActual", estadoActual)).uniqueResult();
	}
	@Override
	public Long guardarEstado(EstadoDeVehiculo estadoDeVehiculo) {
		return (Long)sessionFactory.getCurrentSession().save(estadoDeVehiculo);
	}

	

}

package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.TipoDeVehiculo;
@Repository
public class TipoDeVehiculoDaoImpl implements TipoDeVehiculoDao {
	@Inject
	private SessionFactory sessionFactory;
	@Override
	public List<TipoDeVehiculo> traerTiposDeVehiculos() {
		return sessionFactory.getCurrentSession().createCriteria(TipoDeVehiculo.class).list();
	}

}

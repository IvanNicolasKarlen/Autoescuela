package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.TipoDeVehiculo;
@Repository
public class TipoDeVehiculoDaoImpl implements TipoDeVehiculoDao {
	@Inject
	private SessionFactory sessionFactory;
	@Override
	public List<TipoDeVehiculo> traerTiposDeVehiculos() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(TipoDeVehiculo.class).list();
	}
	@Override
	public TipoDeVehiculo buscarTipoDeVehiculo(TipoDeVehiculo tipoVehiculo) {
		final Session session = sessionFactory.getCurrentSession();
		return (TipoDeVehiculo)session.createCriteria(TipoDeVehiculo.class)
				.add(Restrictions.eq("tipo", tipoVehiculo.getTipo()))
				.add(Restrictions.eq("especialidad", tipoVehiculo.getEspecialidad()))
				.uniqueResult();
		
	}
	@Override
	public Long guardarTipoDeVehiculo(TipoDeVehiculo tipoVehiculo) {
		final Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(tipoVehiculo);
	}
	@Override
	public List<TipoDeVehiculo> buscarTipoDeVehiculoPorEspecialidad(Especialidad varEspecialidad) {
		final Session session = sessionFactory.getCurrentSession();
		return (List<TipoDeVehiculo>)session.createCriteria(TipoDeVehiculo.class)
				.add(Restrictions.eq("especialidad", varEspecialidad)).list();
	}

}

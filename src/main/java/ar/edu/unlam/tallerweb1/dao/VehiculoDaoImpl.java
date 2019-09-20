package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;

@Repository
public class VehiculoDaoImpl implements VehiculoDao {
	@Inject
	private SessionFactory sessionFactory;

	@Override
	public List<Vehiculo> obtenerVehiculoPorEspecialidad(Especialidad especialidad) {
		final Session sesion = sessionFactory.getCurrentSession();
		List<Vehiculo> vehiculos = sesion.createCriteria(Vehiculo.class).createAlias("tipoDeVehiculo", "tipoBuscado")
									.add(Restrictions.eq("tipoBuscado.especialidad", especialidad))
									.list();
		return vehiculos;
	}

	@Override
	public Vehiculo buscarVehiculo(Vehiculo vehiculo) {
		final Session sesion = sessionFactory.getCurrentSession();
		Vehiculo vehiculoBuscado = (Vehiculo) sesion.createCriteria(Vehiculo.class)
									.add(Restrictions.eq("modelo",vehiculo.getModelo()))
									.add(Restrictions.eq("patente",vehiculo.getPatente()))
									.uniqueResult();
		return vehiculoBuscado; 
	}

	@Override
	public Long guardarVehiculo(Vehiculo vehiculo) {
		final Session sesion = sessionFactory.getCurrentSession();
		return (Long)sesion.save(vehiculo);
	
	}

	@Override
	public Vehiculo buscarVehiculoPorId(Long id) {
		return (Vehiculo)sessionFactory.getCurrentSession()
				.createCriteria(Vehiculo.class).add(Restrictions.eq("id", id)).uniqueResult();
	}
	
}

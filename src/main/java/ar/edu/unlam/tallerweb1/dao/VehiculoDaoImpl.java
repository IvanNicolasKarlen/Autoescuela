package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;

@Repository
public class VehiculoDaoImpl implements VehiculoDao {
	@Inject
	private SessionFactory sessionFactory;

	/******************************************INSTRUCTOR**************************************/
	@Override
	public List<EstadoDeVehiculo> traerVehiculos(Long idAgenda) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(EstadoDeVehiculo.class)
				.add(Restrictions.like("estadoActual", "averiado"))
				.createAlias("vehiculos", "vehiculoBuscado")
				.createAlias("vehiculoBuscado.ives", "ivesBuscadas")
				.createAlias("ivesBuscadas.agendas", "agendasBuscadas")
				.add(Restrictions.eq("agendasBuscadas.id", idAgenda))
				
				.list();
				
	}
	
	@Override
	public void updateVehiculo(Vehiculo idEstadoDeVehiculo) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.update(idEstadoDeVehiculo);
		
	}
	/******************************************ORGANIZADOR**************************************/
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
		final Session sesion = sessionFactory.getCurrentSession();
		return (Vehiculo) sesion.get(Vehiculo.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vehiculo> obtenerVehiculosSinInstructorPorEspecialidad(Especialidad esp) {
		final Session session = sessionFactory.getCurrentSession();
		return (List<Vehiculo>) session.createCriteria(Vehiculo.class)
								.createAlias("tipoDeVehiculo", "tipoBuscado")
								.createAlias("tipoBuscado.especialidad", "especialidadBuscada")
								.createAlias("especialidadBuscada.instructoresVehiculosEspecialidades", "iveBuscada")
								.add(Restrictions.isNull("iveBuscada.instructor"))
								.list();
	}

	
}
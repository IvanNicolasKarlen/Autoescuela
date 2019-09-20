package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.TipoDeVehiculo;
@Repository
public class EspecialidadDaoImpl implements EspecialidadDao {
	@Inject
	private SessionFactory sessionFactory;
	@Override
	public List<Especialidad> traerListaDeEspecialidades() {
		return sessionFactory.getCurrentSession().createCriteria(Especialidad.class).list();
	}
	@Override
	public Especialidad traerEspecialidadPorId(Long id) {
		/*return (Especialidad) sessionFactory.getCurrentSession().createCriteria(Especialidad.class)
				.add(Restrictions.eqOrIsNull("id", id)).uniqueResult();*/
		return (Especialidad)sessionFactory.getCurrentSession().get(Especialidad.class, id);
	}


}

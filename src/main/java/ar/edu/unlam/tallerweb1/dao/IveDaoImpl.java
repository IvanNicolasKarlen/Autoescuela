package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;

@Repository
public class IveDaoImpl implements IveDao {
	@Inject
	private SessionFactory sessionFactory;
	
		public Long guardarIve(InstructorVehiculoEspecialidad ive){
			return (Long)sessionFactory.getCurrentSession().save(ive);
		}

		@Override
		public List<InstructorVehiculoEspecialidad> traerListaIve(){
			final Session sesion = sessionFactory.getCurrentSession();
			List<InstructorVehiculoEspecialidad> listaIve = sesion.createCriteria(InstructorVehiculoEspecialidad.class)
															.add(Restrictions.isNotNull("id")).list();
			return listaIve;
		}
}

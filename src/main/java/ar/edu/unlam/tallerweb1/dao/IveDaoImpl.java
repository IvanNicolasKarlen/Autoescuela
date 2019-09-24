package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
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
			@SuppressWarnings("unchecked")
			List<InstructorVehiculoEspecialidad> listaIve = sesion.createCriteria(InstructorVehiculoEspecialidad.class)
															.list();
			return listaIve;
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<InstructorVehiculoEspecialidad> traerListaIvePorEspecialidad(Especialidad especialidad) {
			final Session session = sessionFactory.getCurrentSession();
			return (List<InstructorVehiculoEspecialidad>)
					session.createCriteria(InstructorVehiculoEspecialidad.class)
					.add(Restrictions.eq("especialidad", especialidad));
		}
}

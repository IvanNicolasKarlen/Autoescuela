package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;

@Repository("EstadoDeAgendaDao")
public class EstadoDeAgendaDaoImpl implements EstadoDeAgendaDao {

	@Inject
	private SessionFactory sessionFactory;

/************************************************ORGANIZADOR***************************/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoDeAgenda> traerListaDeEstadoDeAgenda() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(EstadoDeAgenda.class)
				.list();
	}

	@Override
	public EstadoDeAgenda traerEstadoDeAgendaPorNombre(String estado) {
		Session session = sessionFactory.getCurrentSession();
		return (EstadoDeAgenda) session.createCriteria(EstadoDeAgenda.class)
							.add(Restrictions.eq("estado", estado)).uniqueResult();
	}

	@Override
	public EstadoDeAgenda traerEstadoDeAgendaPorId(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (EstadoDeAgenda) session.get(EstadoDeAgenda.class, id);
	}
	
	
	
	
	/***************************************ALUMNO********************************/
	@Override
	public EstadoDeAgenda traigoElEstadoEnEspera() {
		final Session session = sessionFactory.getCurrentSession();
		
		EstadoDeAgenda a = (EstadoDeAgenda) session.createCriteria(EstadoDeAgenda.class)
				.add(Restrictions.eq("estado","En espera"))
				.uniqueResult();
				
		return a;
	}
}

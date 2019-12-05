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
		return (List<EstadoDeAgenda>) session.createCriteria(EstadoDeAgenda.class)
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
	public EstadoDeAgenda buscarEstadoOcupado() {
final Session session = sessionFactory.getCurrentSession();
		
		EstadoDeAgenda a = (EstadoDeAgenda) session.createCriteria(EstadoDeAgenda.class)
				.add(Restrictions.eq("estado","Ocupada"))
				.uniqueResult();
				
		return a;
	}

	@Override
	public EstadoDeAgenda traigoElEstadoCanceladaPorAlumno() {
final Session session = sessionFactory.getCurrentSession();
		
		EstadoDeAgenda a = (EstadoDeAgenda) session.createCriteria(EstadoDeAgenda.class)
				.add(Restrictions.eq("estado","Cancelado por Alumno"))
				.uniqueResult();
				
		return a;
	}
	
	@Override
	public EstadoDeAgenda traigoElEstadoDisponible() {
final Session session = sessionFactory.getCurrentSession();
		
		EstadoDeAgenda a = (EstadoDeAgenda) session.createCriteria(EstadoDeAgenda.class)
				.add(Restrictions.eq("estado","Disponible"))
				.uniqueResult();
				
		return a;
	}

	@Override
	public EstadoDeAgenda traigoElEstadoFinalizado() {
final Session session = sessionFactory.getCurrentSession();
		
		EstadoDeAgenda a = (EstadoDeAgenda) session.createCriteria(EstadoDeAgenda.class)
				.add(Restrictions.eq("estado","Finalizado"))
				.uniqueResult();
				
		return a;
	}

	@Override
	public EstadoDeAgenda traigoElEstadoAbandonada() {
final Session session = sessionFactory.getCurrentSession();
		
		EstadoDeAgenda a = (EstadoDeAgenda) session.createCriteria(EstadoDeAgenda.class)
				.add(Restrictions.eq("estado","Abandonada"))
				.uniqueResult();
				
		return a;
	}

	@Override
	public EstadoDeAgenda traigoElEstadoOcupada() {
final Session session = sessionFactory.getCurrentSession();
		
		EstadoDeAgenda a = (EstadoDeAgenda) session.createCriteria(EstadoDeAgenda.class)
				.add(Restrictions.eq("estado","Ocupada"))
				.uniqueResult();
				
		return a;
	}

	@Override
	public EstadoDeAgenda buscarEstadoPerdida() {
final Session session = sessionFactory.getCurrentSession();
		
		EstadoDeAgenda a = (EstadoDeAgenda) session.createCriteria(EstadoDeAgenda.class)
				.add(Restrictions.eq("estado","Clase perdida"))
				.uniqueResult();
				
		return a;
	}
	/***************************************INSTRUCTOR********************************/
	@SuppressWarnings("unchecked")
	public List<EstadoDeAgenda> traerListaDeEstadoDeAgendaParaInstructor() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(EstadoDeAgenda.class)
				.add(Restrictions.ne("estado", "Disponible"))
				.add(Restrictions.ne("estado", "Ocupada"))
				.add(Restrictions.ne("estado", "Cancelado por Alumno"))
				.add(Restrictions.ne("estado", "Cancelado por Organizador"))
				.add(Restrictions.ne("estado", "Finalizado"))
				.add(Restrictions.ne("estado", "Clase perdida"))
				.add(Restrictions.ne("estado", "Abandonada"))
				
				.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoDeAgenda> traerDetalleDeEstadoDeAgendaParaInstructor() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(EstadoDeAgenda.class)
				.add(Restrictions.ne("detalle", "El alumno puede inscribirse"))
				.add(Restrictions.ne("detalle", "Ya hay un alumno inscripto"))
				.add(Restrictions.ne("detalle", "El alumno cancelo la clase"))
				.add(Restrictions.ne("detalle", "El organizador decidio cancelar la clase"))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoDeAgenda> traerListaDeEstadoDeAgendaParaOrganizador() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(EstadoDeAgenda.class)
						.add(Restrictions.ne("estado", "Cancelado por Alumno"))
						.add(Restrictions.ne("estado", "Cancelado por Instructor"))
						.add(Restrictions.ne("estado", "Abandonada"))
						.list();
	}
	
//	
//	@Override
//	public void updateEstadoDeAgenda(EstadoDeAgenda mensaje) {
//		final Session session = sessionFactory.getCurrentSession();
//		session.update(mensaje);		
//	}
//
//	@Override
//	public EstadoDeAgenda traerListaDeOcupados() {
//		final Session session = sessionFactory.getCurrentSession();
//		
//		EstadoDeAgenda a = (EstadoDeAgenda) session.createCriteria(EstadoDeAgenda.class)
//				.add(Restrictions.eq("estado", "Ocupado"))
//				.uniqueResult();
//		return a;
//	}
//	


	
}
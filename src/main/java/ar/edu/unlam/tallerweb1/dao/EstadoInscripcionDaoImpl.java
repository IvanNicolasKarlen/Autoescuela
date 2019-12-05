package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;

@Repository("EstadoInscripcionDao")
public class EstadoInscripcionDaoImpl implements EstadoInscripcionDao {

	@Inject
	private SessionFactory sessionfactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionfactory = sessionFactory;
	}

	
	@Override
	public EstadoInscripcion buscarEstadoCursando() {
		final Session session = sessionfactory.getCurrentSession();

	
		return(EstadoInscripcion) session.createCriteria(EstadoInscripcion.class)
				.add(Restrictions.eq("estado", "Cursando"))
				.uniqueResult();
	}


	@Override
	public EstadoInscripcion buscarEstadoFinalizado() {
		final Session session = sessionfactory.getCurrentSession();

		
		return(EstadoInscripcion) session.createCriteria(EstadoInscripcion.class)
				.add(Restrictions.eq("estado", "Finalizado"))
				.uniqueResult();
	}


	@Override
	public EstadoInscripcion buscarEstadoEliminadoPorAlumno() {
final Session session = sessionfactory.getCurrentSession();

		
		return(EstadoInscripcion) session.createCriteria(EstadoInscripcion.class)
				.add(Restrictions.eq("estado", "Eliminado por Alumno"))
				.uniqueResult();
	}
	
}
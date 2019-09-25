package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;

@Repository("AlumnoEstadoDao")
public class AlumnoEstadoDaoImpl implements AlumnoEstadoDao {
	
	@Inject
	private SessionFactory sessionfactory;

	@Override
	public EstadoDelCurso buscarEstadoCursando() {
		final Session session = sessionfactory.getCurrentSession();

		String estado = "Cursando";
		return(EstadoDelCurso) session.createCriteria(EstadoDelCurso.class)
				.add(Restrictions.eq("estadoDelCurso", estado))
				.uniqueResult();
	}
	
	
	

}

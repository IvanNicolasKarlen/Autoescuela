package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;

@Repository("AlumnoBuscarEstadoDao")
public class AlumnoBuscarEstadoDaoImpl implements AlumnoBuscarEstadoDao {

	// Como todo dao maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	@Inject
    private SessionFactory sessionFactory;

	@Override
	
	public EstadoDelCurso buscarEstadoCursando() {
final Session session = sessionFactory.getCurrentSession();

	String estado = "Cursando";
	return(EstadoDelCurso) session.createCriteria(EstadoDelCurso.class)
			.add(Restrictions.eq("estadoDelCurso", estado))
			.uniqueResult();
		}

	

}

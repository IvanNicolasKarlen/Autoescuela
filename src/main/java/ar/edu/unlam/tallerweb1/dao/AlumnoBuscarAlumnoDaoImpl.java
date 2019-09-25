package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Alumno;

@Repository("AlumnoBuscarAlumnoDao")
public class AlumnoBuscarAlumnoDaoImpl implements AlumnoBuscarAlumnoDao {

	// Como todo dao maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	@Inject
    private SessionFactory sessionFactory;

	@Override
	public Alumno buscarAlumno(Long idAlumno) {
		final Session session = sessionFactory.getCurrentSession();
		
		return (Alumno) session.createCriteria(Alumno.class)
				.add(Restrictions.eq("id", idAlumno))
				.uniqueResult();
		
		
	}

	
	

}

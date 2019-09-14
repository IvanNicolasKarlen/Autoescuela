package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Alumno;

@Repository("AlumnoMetodoQueGuardaAlumnosDao")
public class AlumnoMetodoQueGuardaAlumnosDaoImpl implements AlumnoMetodoQueGuardaAlumnosDao {

	// Como todo dao maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	@Inject
    private SessionFactory sessionFactory;

	

	
	@Override
	public void guardarAlumno(Alumno alumno) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.save(alumno);
		
	}

}

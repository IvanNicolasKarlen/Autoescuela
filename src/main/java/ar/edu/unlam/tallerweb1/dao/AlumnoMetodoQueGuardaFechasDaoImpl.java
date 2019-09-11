package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Agenda;

@Repository("AlumnoMetodoQueGuardaFechasDao")
public class AlumnoMetodoQueGuardaFechasDaoImpl implements AlumnoMetodoQueGuardaFechasDao {

	// Como todo dao maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	@Inject
    private SessionFactory sessionFactory;

	@Override
	public void guardarFechasEnLaAgenda(Agenda agenda) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.save(agenda);
		
	}


	
	
	

}

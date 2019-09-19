package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
@Repository
public class OrganizadorAgregarInstructorDaoImpl implements OrganizadorAgregarInstructorDao {
	@Inject
	private SessionFactory sessionFactory;
	@Override
	public Long agregarInstructor(Instructor instructor, Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(usuario);
		return (Long)session.save(instructor);

	}

}

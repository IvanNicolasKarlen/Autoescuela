package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
@Repository
public class OrganizadorInstructorDaoImpl implements OrganizadorInstructorDao {
	@Inject
	private SessionFactory sessionFactory;
	@Override
	public Long agregarInstructor(Instructor instructor) {
		final Session session = sessionFactory.getCurrentSession();
		return (Long)session.save(instructor);

	}
	@Override
	public Instructor buscarInstructorPorId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Instructor) session.get(Instructor.class, id);
	}

}

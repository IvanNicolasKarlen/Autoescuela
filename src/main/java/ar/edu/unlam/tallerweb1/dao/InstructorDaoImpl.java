package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
@Repository
public class InstructorDaoImpl implements InstructorDao {
	@Inject
	private SessionFactory sessionFactory;
	@Override
	public Long agregarInstructor(Instructor instructor, Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(usuario);
		return (Long)session.save(instructor);

	}
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


	
	
	/******************************INSTRUCTOR**********************************/
	
	@Override
	public Instructor buscarInstructor(Long idInstructor) {
		final Session session = sessionFactory.getCurrentSession();
		return (Instructor) session.createCriteria(Instructor.class)
				.add(Restrictions.eq("id", idInstructor))
				.uniqueResult();
	}
	@Override
	public Usuario buscarUsuario(Long idInstructor) {
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.get(Usuario.class, idInstructor);
	}

}

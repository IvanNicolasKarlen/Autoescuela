package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Curso;
@Repository
public class OrganizadorCursoDaoImpl implements OrganizadorCursoDao {
	@Inject
	private SessionFactory sessionFactory;
	@Override
	public Long agregarCurso(Curso curso) {
		final Session session = sessionFactory.getCurrentSession();
		return (Long)session.save(curso);
	}

	@Override
	public Curso buscarCurso(Curso curso) {
		final Session session = sessionFactory.getCurrentSession();
		return (Curso)session.createCriteria(Curso.class)
				.add(Restrictions.eq("titulo", curso.getTitulo())).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> traerListaDeCursos() {
		final Session session = sessionFactory.getCurrentSession();
		return (List<Curso>)session.createCriteria(Curso.class).list();
	}

	@Override
	public Curso buscarCursoPorId(Long cursoid) {
		final Session session = sessionFactory.getCurrentSession();
		return (Curso)session.get(Curso.class, cursoid);
	}

}

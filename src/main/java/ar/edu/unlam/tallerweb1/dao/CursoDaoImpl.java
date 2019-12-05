package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

@Repository("CursoDao")
public class CursoDaoImpl implements CursoDao {
	
	@Inject 
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	


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

	@Override
	public void eliminarCurso(Curso curso) {
		final Session session = sessionFactory.getCurrentSession();
		session.delete(curso);
	}

	@Override
	public void modificarCurso(Curso curso) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(curso);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> traerCursosPorEspecialidad(String especialidad) {
		final Session session = sessionFactory.getCurrentSession();
		return (List<Curso>) session.createCriteria(Curso.class)
							.createAlias("especialidad", "especialidadBuscada")
							.add(Restrictions.like("especialidadBuscada.tipo", especialidad))
							.list();
	}

	
	
	
	
	
	
	
	
	
}
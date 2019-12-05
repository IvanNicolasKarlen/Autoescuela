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
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

@Repository("EstadoDelCursoDao")
public class EstadoDelCursoDaoImpl implements EstadoDelCursoDao {

	@Inject
    private SessionFactory sessionFactory;
	
	
	/*************************************ORGANIZADOR***************************/
	@SuppressWarnings("unchecked")
	@Override
	public List<EstadoDelCurso> traerListaDeEstadosDelCurso() {
		Session session = sessionFactory.getCurrentSession();
		return (List<EstadoDelCurso>) session.createCriteria(EstadoDelCurso.class).list();
	}

	@Override
	public EstadoDelCurso traerEstadoDelCursoPorNombre(String estado) {
		Session session = sessionFactory.getCurrentSession();
		return (EstadoDelCurso) session.createCriteria(EstadoDelCurso.class)
								.add(Restrictions.eq("estadoDelCurso", estado)).uniqueResult();
	}

	@Override
	public EstadoDelCurso traerEstadoDelCursoPorId(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (EstadoDelCurso) session.get(EstadoDelCurso.class,id);
	}


}
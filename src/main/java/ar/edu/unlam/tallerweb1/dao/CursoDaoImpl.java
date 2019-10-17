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
	private SessionFactory sessionfactory;

	@Override
	public List<Curso> buscarCursos() {
		final Session session = sessionfactory.getCurrentSession();
		
		List<Curso> milista = session.createCriteria(Curso.class)
				.createAlias("especialidad", "especialidad")
								.list();
		return milista;
	}
	
	
	@Override
	public Curso buscarCurso(Long cursoElegidoid) {
final Session session = sessionfactory.getCurrentSession();
		
		return (Curso) session.createCriteria(Curso.class)
				.add(Restrictions.eq("id",cursoElegidoid))
				.uniqueResult();
		
	}
}

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

@Repository("AlumnoInscripcionDao")
public class AlumnoInscripcionDaoImpl implements AlumnoInscripcionDao {
	
	@Inject 
	private SessionFactory sessionfactory;

	@Override
	public List<Curso> buscarCursos() {
		final Session session = sessionfactory.getCurrentSession();
		
		List<Curso> milista = session.createCriteria(Curso.class)
								.list();
		return milista;
	}

	@Override
	public Curso buscarCurso(Curso cursoElegido) {
final Session session = sessionfactory.getCurrentSession();
		
		return (Curso) session.createCriteria(Curso.class)
				.add(Restrictions.eq("id",cursoElegido.getId()))
				.uniqueResult();
		
	}

	@Override
	public List<Inscripcion> consultarSiYaSeInscribioAUnCurso(Long idAlumno, EstadoInscripcion estado,
			Especialidad especialidad) {
		final Session session = sessionfactory.getCurrentSession();
		List <Inscripcion> l =  session.createCriteria(Inscripcion.class)
					.createAlias("curso.especialidad", "ce")
					.add(Restrictions.eq("ce.id", especialidad.getId()))
					.add(Restrictions.eq("alumno.id", idAlumno))
					.add(Restrictions.eq("estado.id", estado.getId()))
					.list();
		return l;
	}

	@Override
	public void guardarInscripcion(Alumno alumno, Curso curso, Inscripcion cursoAlumno, EstadoInscripcion estado) {
		final Session session = sessionfactory.getCurrentSession();
		
		cursoAlumno.setCurso(curso);
		cursoAlumno.setAlumno(alumno);
		cursoAlumno.setEstadoInscripcion(estado);		
		
		
		
	}

	@Override
	public Inscripcion buscarInscripcion(Alumno alumno, Curso curso) {
		final Session session = sessionfactory.getCurrentSession();
		return (Inscripcion) session.createCriteria(Inscripcion.class)
				.add(Restrictions.eq("alumno.id",alumno.getId()))
				.add(Restrictions.eq("curso.id",curso.getId()))
				.uniqueResult();
		
	}

	@Override
	public void guardarInscripcionEnLaAgenda(Agenda a) {
		final Session session = sessionfactory.getCurrentSession();
		session.update(a);
		
	}


	
	
	
	

}

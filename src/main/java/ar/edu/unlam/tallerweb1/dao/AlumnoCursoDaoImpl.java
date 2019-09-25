package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

@Repository("AlumnoCursoDao")
public class AlumnoCursoDaoImpl implements AlumnoCursoDao {
	
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
	public List<TablaCursoAlumno> consultarSiYaSeInscribioAUnCurso(Long idAlumno, EstadoDelCurso estado,
			Especialidad especialidad) {
		final Session session = sessionfactory.getCurrentSession();
		List <TablaCursoAlumno> l =  session.createCriteria(TablaCursoAlumno.class)
					.createAlias("curso.especialidad", "ce")
					.add(Restrictions.eq("ce.id", especialidad.getId()))
					.add(Restrictions.eq("alumno.id", idAlumno))
					.add(Restrictions.eq("estadoDelCurso.id", estado.getId()))
					.list();
		return l;
	}

	@Override
	public void guardarCurso(Alumno alumno, Curso curso, TablaCursoAlumno cursoAlumno, EstadoDelCurso estado) {
		final Session session = sessionfactory.getCurrentSession();
		
		cursoAlumno.setCurso(curso);
		cursoAlumno.setAlumno(alumno);
		cursoAlumno.setEstadoDelCurso(estado);
		session.save(cursoAlumno);
		
	}
	
	
	
	

}

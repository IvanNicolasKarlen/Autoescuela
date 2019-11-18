package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

@Repository("InscripcionDao")
public class InscripcionDaoImpl implements InscripcionDao {

	@Inject
	private SessionFactory sessionfactory;

	
	/********************************* Alumno *********************************************/
	@Override
	public List<Inscripcion> saberSiEstaRealizandoAlgunCurso(Long idAlumno, EstadoInscripcion estado) {
		final Session session = sessionfactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<Inscripcion> lista = session.createCriteria(Inscripcion.class)
				.add(Restrictions.eq("alumno.id",idAlumno))
				.createAlias("curso.especialidad", "esp")
				//.setProjection(Projections.countDistinct(("esp.id")))
				.createAlias("estadoInscripcion", "estadoInscripcionBuscado")
				.add(Restrictions.eq("estadoInscripcionBuscado.id", estado.getId()))
				
				.list();
		return lista;
	}

	@Override
	public List<Inscripcion> consultarSiYaSeInscribioAUnCurso(Long idAlumno, EstadoInscripcion estado, Especialidad especialidad) {
		final Session session = sessionfactory.getCurrentSession();
		
		
		System.out.println(estado.getId());
		List <Inscripcion> l =  session.createCriteria(Inscripcion.class)
					.createAlias("curso", "cur")
					.createAlias("cur.especialidad", "ce")
					.add(Restrictions.eq("ce.id", especialidad.getId()))
					.add(Restrictions.eq("alumno.id", idAlumno))
					.add(Restrictions.eq("estadoInscripcion.id", estado.getId()))
					.list();
		return l;
	}
	
	
	
	@Override
	public void guardarInscripcion(Alumno alumno, Curso curso, Inscripcion cursoAlumno, EstadoInscripcion estado) {
		final Session session = sessionfactory.getCurrentSession();
		
		
		cursoAlumno.setCurso(curso);
		cursoAlumno.setAlumno(alumno);
		cursoAlumno.setEstadoInscripcion(estado);		
		session.save(cursoAlumno);
		
		
	}


	@Override
	public void guardarInscripcionEnLaAgenda(Agenda a) {
		final Session session = sessionfactory.getCurrentSession();
		session.update(a);
		
	}

	@Override
	public List<Inscripcion> traerLosCursosEnQueSeEncuentraAnotado(Long idAlumno, EstadoInscripcion estado) {
		final Session session = sessionfactory.getCurrentSession();
		
		List <Inscripcion> l =  session.createCriteria(Inscripcion.class)
				.add(Restrictions.eq("alumno.id", idAlumno))
				.add(Restrictions.eq("estadoInscripcion.id", estado.getId()))
				.createAlias("curso.especialidad", "especialidad")
				.list();
	return l;
	}

	
	
	@Override
	public Inscripcion buscarCursoAEliminar(Long idEspecialidad, Long idAlumno) {
		final Session session = sessionfactory.getCurrentSession();
		
		Inscripcion a = (Inscripcion) session.createCriteria(Inscripcion.class)
				.add(Restrictions.eq("alumno.id",idAlumno))
				.createAlias("curso.especialidad", "especialidad")
				.add(Restrictions.eq("especialidad.id",idEspecialidad))
				.createAlias("estadoInscripcion", "estadoInscripcion")
				.add(Restrictions.eq("estadoInscripcion.estado", "Cursando"))
				.uniqueResult();
				
		return a;
	}

	@Override
	public void eliminarInscripcionDelAlumno(Inscripcion inscripcionEliminar) {
		final Session session = sessionfactory.getCurrentSession();
		
		session.update(inscripcionEliminar);
		
	}


	@Override
	public Inscripcion buscarCursoAEliminarDelAlumno(Long idCurso, Long idAlumno) {
final Session session = sessionfactory.getCurrentSession();
		
		return (Inscripcion) session.createCriteria(Inscripcion.class)
				.add(Restrictions.eq("alumno.id",idAlumno))
				.add(Restrictions.eq("curso.id",idCurso))
				.createAlias("estadoInscripcion", "estadoInscripcion")
				.add(Restrictions.eq("estadoInscripcion.estado", "Cursando"))
				.uniqueResult();
		
	}

	@Override
	public Inscripcion cursoQueQuieroEliminar(Long idCurso, Long idAlumno) {
final Session session = sessionfactory.getCurrentSession();
		
		return (Inscripcion) session.createCriteria(Inscripcion.class)
				.createAlias("alumno", "alumno")
				.add(Restrictions.eq("alumno.id",idAlumno))
				.createAlias("curso", "curso")
				.add(Restrictions.eq("curso.id",idCurso))
				.createAlias("estadoInscripcion", "estadoInscripcion")
				.add(Restrictions.eq("estadoInscripcion.estado", "Cursando"))
				.uniqueResult();
	}

	@Override
	public Inscripcion buscarInscripcion( Long idAlumno, Long idCurso) {
		 
		 final Session session = sessionfactory.getCurrentSession();
			return (Inscripcion) session.createCriteria(Inscripcion.class)
					.createAlias("alumno", "alumno")
					.add(Restrictions.eq("alumno.id",idAlumno))
					.createAlias("curso", "curso")
					.add(Restrictions.eq("curso.id",idCurso))
					.createAlias("estadoInscripcion", "estadoInscrip")
					.add(Restrictions.eq("estadoInscrip.estado", "Cursando"))
					.uniqueResult();
	}

	
	
	
	/******************************************************************************/

	
	

		
		
		
}
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
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Instructor;

@Repository("servicioAgendaDao")
public class AgendaDaoImp implements AgendaDao {

	@Inject
    private SessionFactory sessionFactory;
	
	List <Agenda> miLista;
	List <Alumno> miListaAlumno;
	
	@Override
	public List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Agenda.class)
				.createAlias("instructorVehiculoEspecialidad", "iveBuscado")
				.createAlias("iveBuscado.instructor", "instructorId")
				.add(Restrictions.isNotNull("inscripcion"))
				.add(Restrictions.eq("instructorId.id", idInstructor))
				.list();
	}

	

	
	
	/***************************************Alumno**************************************/
	@Override
	public TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso curso) {
	final Session session = sessionFactory.getCurrentSession();
		
		List <Agenda> result = session.createCriteria(Agenda.class)
				//* permite que no traiga duplicados
//				.setProjection(Projections.projectionList())
//				.add((Criterion) Projections.distinct(Projections.property("fecha")))
				.add(Restrictions.isNull("inscripcion.id"))
				.createAlias("instructorVehiculoEspecialidad", "ive")
				.add(Restrictions.eq("ive.especialidad.id", curso.getEspecialidad().getId()))
				.createAlias("instructorVehiculoEspecialidad.vehiculo.estadoDeVehiculo", "estadoVehiculo")
				.add(Restrictions.eq("estadoVehiculo.estadoActual", "Disponible"))
				.list();
		
	
				// creamos un treeSet para agregar las agendas sin que se 
				// repitan las fechas
																	// reverseOrder ordena los elementos
																	// en forma descendente
				TreeSet<Agenda> agendasDesc = new TreeSet<Agenda>(java.util.Collections.reverseOrder());
				agendasDesc.addAll(result);
						
				return agendasDesc;
	
	}

	@Override
	public Agenda buscarAgendasElegidas(Long id, Curso curso) {
		final Session session = sessionFactory.getCurrentSession();
		Agenda a = (Agenda) session.createCriteria(Agenda.class)
				.add(Restrictions.eq("id",id))
				.add(Restrictions.isNull("inscripcion.id"))
				.createAlias("instructorVehiculoEspecialidad", "ive")
				.createAlias("ive.especialidad", "esp")
				.add(Restrictions.eq("esp.id", curso.getEspecialidad().getId()))
				.createAlias("instructorVehiculoEspecialidad.vehiculo", "ve")
				.createAlias("ve.estadoDeVehiculo", "estadoVehiculo")
				.add(Restrictions.eq("estadoVehiculo.estadoActual", "Disponible"))
				.uniqueResult();
				
		return a;
	}

	@Override
	public List<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno) {
final Session session = sessionFactory.getCurrentSession();
		
		List<Agenda> lista = session.createCriteria(Agenda.class)
				.createAlias("inscripcion.alumno", "alumno")
				.add(Restrictions.eq("alumno.id", idAlumno))
				.createAlias("asistencia", "asistencia")
				.add(Restrictions.eq("asistencia.estado", "En espera"))
				.createAlias("inscripcion.estadoInscripcion", "estadoInscripcion")
				.add(Restrictions.eq("estadoInscripcion.estado", "Cursando"))
				.list();
		return lista;
	}
	
	
	
	@Override
	public List<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(Long c, EstadoInscripcion estado, Long idAlumno) {
		final Session session = sessionFactory.getCurrentSession();
		
		List<Agenda> lista = session.createCriteria(Agenda.class)
				.createAlias("inscripcion.alumno", "alumno")
				.add(Restrictions.eq("alumno.id", idAlumno))
				.createAlias("asistencia", "asistencia")
				.add(Restrictions.eq("asistencia.estado", "En espera"))
				.createAlias("inscripcion.curso", "curso")
				.add(Restrictions.eq("curso.id",c))
				.createAlias("inscripcion.estadoInscripcion", "estadoInscripcion")
				.add(Restrictions.eq("estadoInscripcion.estado", "Cursando"))
				.list();
		return lista;
		
	}
					//Alumno 1				Inscripcion 2 Especialidad 2
	@Override
	public List<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idInscripcion, Long idAlumno) {
		 final Session session = sessionFactory.getCurrentSession();
		 
		 List<Agenda> lista = session.createCriteria(Agenda.class)
					.createAlias("inscripcion.alumno", "alumno")
					.add(Restrictions.eq("alumno.id", idAlumno))
					.add(Restrictions.eq("inscripcion.id", idInscripcion))
					.list();
		 
		 return lista;
		 
		 
		 /*List<Agenda> lista = session.createCriteria(Agenda.class)
					.createAlias("inscripcion.alumno", "alumno")
					.add(Restrictions.eq("alumno.id", idAlumno))
					.createAlias("inscripcion.curso.especialidad", "especialidad")
					.add(Restrictions.eq("especialidad.id",idEspecialidad))
					.createAlias("inscripcion.estadoInscripcion", "estadoInscripcion")
					.add(Restrictions.eq("estadoInscripcion.estado", "Cursando"))
					.list();
			return lista;*/
	}

	
	
	
	@Override
	public Agenda traerClaseQueQuiereEliminar(Long idAgendaSeleccionado, Long idAlumno) {
		final Session session = sessionFactory.getCurrentSession();
		
		Agenda a = (Agenda) session.createCriteria(Agenda.class)
				.add(Restrictions.eq("id",idAgendaSeleccionado))
				.createAlias("inscripcion.alumno", "alumno")
				.add(Restrictions.eq("alumno.id",idAlumno))
				.uniqueResult();
				
		return a;
	}

	@Override
	public void eliminarClaseDeLaAgenda(Agenda agenda) {
		final Session session = sessionFactory.getCurrentSession();
	
		session.update(agenda);
		
	}





	@Override
	public List<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas)
	{
		final Session session = sessionFactory.getCurrentSession();
		
		List<Agenda> result =  session.createCriteria(Agenda.class)
				.add(Restrictions.isNull("inscripcion.id"))
				.createAlias("instructorVehiculoEspecialidad", "ive")
				.add(Restrictions.eq("ive.especialidad.id", curso.getEspecialidad().getId()))
				.createAlias("instructorVehiculoEspecialidad.vehiculo.estadoDeVehiculo", "estadoVehiculo")
				.add(Restrictions.eq("estadoVehiculo.estadoActual", "Disponible"))
				.list();

		return result;
	}

	/*******************************O R G A N I Z A D X R *//////////////////////////
	@Override
	public Long crearAgenda(Agenda agenda) {
		final Session sesion = sessionFactory.getCurrentSession();
			Long id = (Long)sesion.save(agenda);
		return id;

	}





	@SuppressWarnings("unchecked")
	@Override
	public List<Agenda> traerAgendaPorFechayHora(String fecha, Integer hora) {
		final Session session = sessionFactory.getCurrentSession();
		return (List<Agenda>) session.createCriteria(Agenda.class)
								.add(Restrictions.eq("fecha", fecha))
								.add(Restrictions.eq("hora",hora))
								.list();
	}





	@Override
	public Agenda traerAgendaPorFechaHoraInstructor(String fecha, Integer hora, Instructor instructor) {
		final Session session = sessionFactory.getCurrentSession();
		return (Agenda) session.createCriteria(Agenda.class)
						.createAlias("instructorVehiculoEspecialidad", "iveBuscada")
						.add(Restrictions.eq("fecha", fecha))
						.add(Restrictions.eq("hora",hora))
						.add(Restrictions.eq("iveBuscada.instructor", instructor))
						.uniqueResult();
	}





	@Override
	public Agenda traerAgendaPorFechaYAlumno(Alumno alumno, String fecha) {
		final Session session = sessionFactory.getCurrentSession();
		return (Agenda)session.createCriteria(Agenda.class)
				.createAlias("inscripcion", "inscripcionBuscada")
				.add(Restrictions.eq("fecha", fecha))
				.add(Restrictions.eq("inscripcionBuscada.alumno", alumno))
				.uniqueResult();
	}





	
	
	/***************************************************************************************/
	

}

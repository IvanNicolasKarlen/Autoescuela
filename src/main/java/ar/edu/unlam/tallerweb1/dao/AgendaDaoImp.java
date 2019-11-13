package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Instructor;

@Repository("servicioAgendaDao")
public class AgendaDaoImp implements AgendaDao {

	@Inject
    private SessionFactory sessionFactory;

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





	@Override
	public List<Agenda> traerTodasLasClasesDeUnAlumno(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (List<Agenda>) session.createCriteria(Agenda.class)
				.createAlias("inscripcion", "inscripcionBuscada")
				.createAlias("inscripcionBuscada.alumno", "alumnoBuscado")
				.add(Restrictions.eq("alumnoBuscado.id", id))
				.list();
	}





	@Override
	public void modificarAgenda(Agenda agenda) {
		final Session sesion = sessionFactory.getCurrentSession();
		sesion.update(agenda);
	}	
	
	/***************************************************************************************/
	

	
	
	/***************************************Alumno**************************************/
	@Override
	public TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso curso, EstadoDeAgenda estadoDeAgenda) {
	final Session session = sessionFactory.getCurrentSession();
		
		List <Agenda> result = session.createCriteria(Agenda.class)
				.add(Restrictions.isNull("inscripcion.id"))
				.createAlias("instructorVehiculoEspecialidad", "ive")
				.add(Restrictions.eq("ive.especialidad.id", curso.getEspecialidad().getId()))
				.add(Restrictions.eq("estadoDeAgenda.id", estadoDeAgenda.getId()))
				
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
	public TreeSet<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno, EstadoInscripcion estado) {
final Session session = sessionFactory.getCurrentSession();
		
	List <Agenda> lista =  session.createCriteria(Agenda.class)
				.createAlias("inscripcion.alumno", "alumno")
				.add(Restrictions.eq("alumno.id", idAlumno))
				.createAlias("estadoDeAgenda", "estadoDeAgenda")
				.createAlias("inscripcion.estadoInscripcion", "estadoInscripcion")
				.add(Restrictions.eq("estadoInscripcion.id", estado.getId()))
				.list();
	
	TreeSet<Agenda> agendasDesc = new TreeSet<Agenda>();
	agendasDesc.addAll(lista);
			
	return agendasDesc;
	
	}
	
	
	
	@Override
	public TreeSet<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(Long c, EstadoInscripcion estado, Long idAlumno) {
		final Session session = sessionFactory.getCurrentSession();
		
		List<Agenda> lista = session.createCriteria(Agenda.class)
				.createAlias("inscripcion.alumno", "alumno")
				.add(Restrictions.eq("alumno.id", idAlumno))
				.createAlias("estadoDeAgenda", "estadoDeAgenda")
				.add(Restrictions.eq("estadoDeAgenda.estado", "Ocupada"))
				.createAlias("inscripcion.curso", "curso")
				.add(Restrictions.eq("curso.id",c))
				.createAlias("inscripcion.estadoInscripcion", "estadoInscripcion")
				.add(Restrictions.eq("estadoInscripcion.estado", "Cursando"))
				.list();
		
		
		TreeSet<Agenda> agendasDesc = new TreeSet<Agenda>();
		agendasDesc.addAll(lista);
				
		return agendasDesc;
		
	}
					//Alumno 1				Inscripcion 2 Especialidad 2
	@Override
	public TreeSet<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(  Long idEspecialidad,Long idAlumno  , EstadoInscripcion estado) {
		 final Session session = sessionFactory.getCurrentSession();
		 
		 List<Agenda> lista =  session.createCriteria(Agenda.class)
				 .createAlias("inscripcion.curso.especialidad", "especialidad")
				 .add(Restrictions.eq("especialidad.id", idEspecialidad))
				 .createAlias("inscripcion.alumno", "alumno")
				 .add(Restrictions.eq("alumno.id", idAlumno))
				 
					.createAlias("inscripcion.estadoInscripcion", "estadoInscripcion")
					.add(Restrictions.eq("estadoInscripcion.id", estado.getId()))
					.list();
		 
		 TreeSet<Agenda> agendasDesc = new TreeSet<Agenda>();
			agendasDesc.addAll(lista);
					
			return agendasDesc;
		 
		 
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

	
	





	@Override
	public void guardarClaseQueEliminoElAlumnoParaQueSePuedaInscribirOtroAlumno(Agenda agenda) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.save(agenda);
		
	}





	@Override
	public Agenda traerClaseQueQuiereEliminarParaAgregarlaEnLimpio(Long idAgendaSeleccionado, Long idAlumno) {
		final Session session = sessionFactory.getCurrentSession();
		
		Agenda a = (Agenda) session.createCriteria(Agenda.class)
				.add(Restrictions.eq("id",idAgendaSeleccionado))
				.createAlias("inscripcion.alumno", "alumno")
				.add(Restrictions.eq("alumno.id",idAlumno))
				.uniqueResult();
				
		return a;
	}





	@Override
	public TreeSet<Agenda> traerTodasLasClasesAEliminarDeUnaSolaEspecialidad(Long idAlumno, Long idInscripcion,
			EstadoInscripcion estado)
	{
				 final Session session = sessionFactory.getCurrentSession();
				 
				 List<Agenda> lista =  session.createCriteria(Agenda.class)
						 .createAlias("inscripcion", "inscripcion")
						 .add(Restrictions.eq("inscripcion.id", idInscripcion))
						 .createAlias("inscripcion.alumno", "alumno")
						 .add(Restrictions.eq("alumno.id", idAlumno))
							.createAlias("inscripcion.estadoInscripcion", "estadoInscripcion")
							.add(Restrictions.eq("estadoInscripcion.id", estado.getId()))
							.list();
				 
				 TreeSet<Agenda> agendasDesc = new TreeSet<Agenda>();
					agendasDesc.addAll(lista);
							
					return agendasDesc;
				 
				 
			
	}





	@Override
	public List<Agenda> validoQueNoSeCreenDosVecesLaMismaClase(Agenda agenda,  EstadoDeAgenda disponible) {
final Session session = sessionFactory.getCurrentSession();
		

		List<Agenda> a = session.createCriteria(Agenda.class)
				.add(Restrictions.eq("fecha",agenda.getFecha()))
				//.createAlias("inscripcion", "inscripcion")
				.add(Restrictions.eq("hora",agenda.getHora()))
				.add(Restrictions.isNull("inscripcion"))
				.createAlias("estadoDeAgenda", "estadoDeAgenda")
				.add(Restrictions.eq("estadoDeAgenda.id", disponible.getId() ))
				.list();
				
		return a;
	}





	@Override
	public TreeSet<Agenda> traerTodasLasClasesParaEliminarYCrearlasEnLimpio(Long idAlumno, Long idEspecialidad,
			EstadoInscripcion estado, EstadoDeAgenda ocupada) {
		final Session session = sessionFactory.getCurrentSession();
		 
		 List<Agenda> lista =  session.createCriteria(Agenda.class)
				 .createAlias("inscripcion.curso.especialidad", "especialidad")
				 .add(Restrictions.eq("especialidad.id", idEspecialidad))
				 .add(Restrictions.eq("estadoDeAgenda.id", ocupada.getId()))
				 .createAlias("inscripcion.alumno", "alumno")
				 .add(Restrictions.eq("alumno.id", idAlumno))
					.createAlias("inscripcion.estadoInscripcion", "estadoInscripcion")
					.add(Restrictions.eq("estadoInscripcion.id", estado.getId()))
					.list();
		 
		 TreeSet<Agenda> agendasDesc = new TreeSet<Agenda>();
			agendasDesc.addAll(lista);
					
			return agendasDesc;
		
	}





	@Override
	public TreeSet<Agenda> traigoSoloLasClasesConEstadoOcupada(Agenda a, EstadoDeAgenda ocupada) {
		final Session session = sessionFactory.getCurrentSession();
		 
		
		List<Agenda> lista =  session.createCriteria(Agenda.class)
				 .createAlias("inscripcion", "inscripcion")
				 .add(Restrictions.eq("inscripcion.id", a.getInscripcion().getId()))
					.createAlias("inscripcion.estadoInscripcion", "estadoInscripcion")
					.add(Restrictions.eq("estadoInscripcion.id", ocupada.getId()))
					.list();
		 
		
		TreeSet<Agenda> agendasDesc = new TreeSet<Agenda>();
		agendasDesc.addAll(lista);
				
		return agendasDesc;
		 
	}


	
	
	/**************************************INSTRUCTOR***************************/
	/***************************************Instructor**************************************/

	@Override
	public List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Agenda.class)
				.createAlias("instructorVehiculoEspecialidad", "iveBuscado")
				.createAlias("iveBuscado.instructor", "instructorId")
				.createAlias("estadoDeAgenda", "estadoBuscado")
				.add(Restrictions.eq("estadoBuscado.estado", "Disponible"))
				.add(Restrictions.isNotNull("inscripcion"))
				.add(Restrictions.eq("instructorId.id", idInstructor))
				.list();
	}
	
	@Override
	public List<Agenda> buscarAlumnos(String nombre,String apellido) {
		final Session session = sessionFactory.getCurrentSession();
		Criteria criteria =  session.createCriteria(Agenda.class)
				.createAlias("inscripcion", "inscripcionBuscada")
				.createAlias("inscripcionBuscada.alumno", "alumnoBuscado")
				.createAlias("alumnoBuscado.usuario", "usuarioBuscado")
				.createAlias("estadoDeAgenda","estadoBuscado")
				.add(Restrictions.like("estadoBuscado.estado", "Disponible"));
				
				
				if(apellido != null) {
				criteria.add(Restrictions.like("usuarioBuscado.apellido","%" + apellido + "%"));
				}
				
				if(nombre != null) {
					criteria.add(Restrictions.like("usuarioBuscado.nombre","%" + nombre + "%"));
				}			
				return criteria.list();
	}

	@Override
	public void updateEstadoDeAgenda(Agenda agenda) {
		final Session session = sessionFactory.getCurrentSession();
		
		session.update(agenda);
}
	@Override
	public Agenda buscarAgendaPorId(Long idAgenda) {
		Session session = sessionFactory.getCurrentSession();
		return (Agenda) session.get(Agenda.class, idAgenda);
	}

	@Override
	public List<Agenda> traerFechasDisponibles() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Agenda.class)
				.createAlias("estadoDeAgenda", "estadoBuscado")
				.createAlias("inscripcion", "inscripcionBuscada")
				.add((Restrictions.eq("estadoBuscado.estado", "Disponible")))
						.setProjection(Projections.projectionList()
								.add(Projections.distinct(Projections.property("inscripcionBuscada.id")))
								)
				
				.list();
	}
	
	@Override
	public Agenda buscarAgenda(Agenda agenda) {
		final Session session = sessionFactory.getCurrentSession();
		return (Agenda)session.createCriteria(Agenda.class)
				.add(Restrictions.eq("id", agenda.getId())).uniqueResult();
	}
	

	@Override
	public List<Agenda> traerAgendaInstructor(Long idInstructor) {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Agenda.class)
				.createAlias("instructorVehiculoEspecialidad", "ive")
				.createAlias("ive.instructor", "instructorBuscado")
				.add(Restrictions.eq("instructorBuscado.id", idInstructor))
				.list();
	}

	
	
	
	/***************************************************************************************/
	

}
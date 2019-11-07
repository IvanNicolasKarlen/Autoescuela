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
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

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
	public TreeSet<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno) {
final Session session = sessionFactory.getCurrentSession();
		
	List <Agenda> lista =  session.createCriteria(Agenda.class)
				.createAlias("inscripcion.alumno", "alumno")
				.add(Restrictions.eq("alumno.id", idAlumno))
				.createAlias("estadoDeAgenda", "estadoDeAgenda")
				.add(Restrictions.eq("estadoDeAgenda.estado", "Ocupada"))
				.createAlias("inscripcion.estadoInscripcion", "estadoInscripcion")
				.add(Restrictions.eq("estadoInscripcion.estado", "Cursando"))
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
	public TreeSet<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idEspecialidad, Long idAlumno) {
		 final Session session = sessionFactory.getCurrentSession();
		 
		 List<Agenda> lista =  session.createCriteria(Agenda.class)
				 .createAlias("inscripcion.curso.especialidad", "especialidad")
				 .add(Restrictions.eq("especialidad.id", idEspecialidad))
				 .createAlias("inscripcion.alumno", "alumno")
				 .add(Restrictions.eq("alumno.id", idAlumno))
				 .createAlias("estadoDeAgenda", "estadoDeAgenda")
					.add(Restrictions.eq("estadoDeAgenda.estado", "Ocupada"))
					.createAlias("inscripcion.estadoInscripcion", "estadoInscripcion")
					.add(Restrictions.eq("estadoInscripcion.estado", "Cursando"))
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
	public TreeSet<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas)
	{
		final Session session = sessionFactory.getCurrentSession();
	
		List<Agenda> result =  session.createCriteria(Agenda.class)
				.add(Restrictions.isNull("inscripcion.id"))
				.createAlias("instructorVehiculoEspecialidad", "ive")
				.add(Restrictions.eq("ive.especialidad.id", curso.getEspecialidad().getId()))
				.createAlias("instructorVehiculoEspecialidad.vehiculo.estadoDeVehiculo", "estadoVehiculo")
				.add(Restrictions.eq("estadoVehiculo.estadoActual", "Disponible"))
				.list();
		
		 TreeSet<Agenda> listaAgendas = new TreeSet<Agenda>();
		 listaAgendas.addAll(result);

		return listaAgendas;
	}

	
	@Override
	public Long crearAgenda(Agenda agenda) {
		final Session sesion = sessionFactory.getCurrentSession();
			Long id = (Long)sesion.save(agenda);
		return id;

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
	public Agenda buscarAgendaPorId(Long idAgendaEditar) {
final Session session = sessionFactory.getCurrentSession();
		
		Agenda a = (Agenda) session.createCriteria(Agenda.class)
				.add(Restrictions.eq("id",idAgendaEditar))
				.uniqueResult();
		return a;
	}





	
	
	
	/***************************************************************************************/
	

}

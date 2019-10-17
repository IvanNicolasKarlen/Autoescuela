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
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("servicioAgendaDao")
public class AgendaDaoImp implements AgendaDao {

	@Inject
    private SessionFactory sessionFactory;
	
	List <Agenda> miLista;
	List <Usuario> miListaAlumno;
	
	@Override
	public List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor) {
		final Session session = sessionFactory.getCurrentSession();
		miLista = session.createCriteria(Agenda.class)
				.add(Restrictions.isNotNull("inscripcion"))
				.createAlias("instructorVehiculoEspecialidad", "iveBuscado")
				.createAlias("iveBuscado.instructor", "instructorId")
				.add(Restrictions.eq("instructorId.id", idInstructor))
				.list();
		return miLista;
	}
	
	
	/***************************************Alumno**************************************/
	@Override
	public TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso curso) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		List <Agenda> result = session.createCriteria(Agenda.class)
				.add(Restrictions.isNull("inscripcion.id"))
				.createAlias("instructorVehiculoEspecialidad", "ive")
				.add(Restrictions.eq("ive.especialidad.id", curso.getEspecialidad().getId()))
				.createAlias("instructorVehiculoEspecialidad.vehiculo.estadoDeVehiculo", "estadoVehiculo")
				.add(Restrictions.eq("estadoVehiculo.estadoActual", "Disponible"))
				
				.setMaxResults(curso.getCantClasesPracticas())
				.list();
		
		
		// creamos un treeSet para agregar las agendas sin que se 
		// repitan las fechas
		TreeSet<Agenda> agendas = new TreeSet<Agenda>();
		agendas.addAll(result);
			
		return agendas ;
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
	
	
	

}

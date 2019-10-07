package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;

@Repository("servicioAgendaDao")
public class ServicioAgendaDaoImp implements ServicioAgendaDao {

	@Inject
    private SessionFactory sessionFactory;
	
	List <Agenda> miLista;
	List <Alumno> miListaAlumno;
	
	@Override
	public List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor) {
		final Session session = sessionFactory.getCurrentSession();
		miLista = session.createCriteria(Agenda.class)
				.createAlias("instructorVehiculoEspecialidad", "iveBuscado")
				.createAlias("iveBuscado.instructor", "instructorId")
				.add(Restrictions.eq("instructorId.id", idInstructor))
				.list();
		return miLista;
	}

	@Override
	public List<Alumno> buscarNombreyApellidoDeAlumnosDeUnInstructor(Long idInstructor) {
		final Session session = sessionFactory.getCurrentSession();
		miListaAlumno = session.createCriteria(Alumno.class)
				.createAlias("usuario", "usuarioBuscado")
				.createAlias("usuarioBuscado.instructor", "instructorId")
				.add(Restrictions.eq("instructorId.id", idInstructor))
				.list();
		return miListaAlumno;
	}

}

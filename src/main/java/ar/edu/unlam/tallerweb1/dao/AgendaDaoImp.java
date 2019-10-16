package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("agendaDao")
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
}

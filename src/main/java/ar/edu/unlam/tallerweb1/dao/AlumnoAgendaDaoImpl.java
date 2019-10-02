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

@Repository("AlumnoAgendaDao")
public class AlumnoAgendaDaoImpl implements AlumnoAgendaDao {
	@Inject
    private SessionFactory sessionFactory;

	@Override
	public void guardarAlumnoConSuCursoElegidoEnLaAgenda(Agenda agenda) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(agenda);

	}

	@Override
	public List<Agenda> traerAgendasDisponibles() {
		final Session session = sessionFactory.getCurrentSession();
		List <Agenda> agendas = session.createCriteria(Agenda.class)
				.add(Restrictions.isNull("alumno.id"))
				.list();
		return agendas;
	}


}

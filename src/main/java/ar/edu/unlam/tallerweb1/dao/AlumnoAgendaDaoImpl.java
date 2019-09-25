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
	public TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso curso) {
		
		final Session session = sessionFactory.getCurrentSession();
		
		List <Agenda> result = session.createCriteria(Agenda.class)
				.add(Restrictions.isNull("alumno.id"))
				.setMaxResults(curso.getCantClasesPracticas())
				.list();
		
		// creamos un treeSet para agregar las agendas sin que se 
		// repitan las fechas
		TreeSet<Agenda> agendas = new TreeSet<Agenda>();
		agendas.addAll(result);
			
		return agendas ;
	}

	@Override
	public void guardarAlumnoConSuCursoElegidoEnLaAgenda(Agenda agenda) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(agenda);

	}

}

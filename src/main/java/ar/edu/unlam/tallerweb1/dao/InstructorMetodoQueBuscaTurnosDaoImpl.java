package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Agenda;

@Repository("InstructorMetodoQueBuscaTurnosDao")
public class InstructorMetodoQueBuscaTurnosDaoImpl implements InstructorMetodoQueBuscaTurnosDao{

	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public List<Agenda> buscarTurnos(Long idInstructor) {
		final Session session = sessionFactory.getCurrentSession();
		List <Agenda> miLista =  session.createCriteria(Agenda.class)
								.createAlias("InstructorVehiculoEspecialidad", "iveBuscando")
								.createAlias("iveBuscado.Instructor", "InstructorId")
								.add(Restrictions.eq("InstructorId.id", idInstructor))
								.list();
		return miLista;
	}

}

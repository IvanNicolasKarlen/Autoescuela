package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Agenda;

@Repository("InstructorMetodoQueBuscaTurnosDao")
@Transactional
public class InstructorMetodoQueBuscaTurnosDaoImpl implements InstructorMetodoQueBuscaTurnosDao{

	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public List<Agenda> buscarTurnos(Long idInstructor) {

		List <Agenda> miLista =  sessionFactory.getCurrentSession().createCriteria(Agenda.class)
								.createAlias("InstructorVehiculoEspecialidad", "iveBuscando")
								.createAlias("iveBuscado.Instructor", "InstructorId")
								.add(Restrictions.eq("InstructorId.id", idInstructor))
								.list();
		return miLista;
	}

}

package ar.edu.unlam.tallerweb1.dao;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("agendaDao")
public class AgendaDaoImp implements AgendaDao {

	@Inject
    private SessionFactory sessionFactory;
	
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
	
	@Override
	public List<Agenda> buscarAlumnos(String nombre,String apellido) {
		final Session session = sessionFactory.getCurrentSession();
		Criteria criteria =  session.createCriteria(Agenda.class)
				.createAlias("inscripcion", "inscripcionBuscada")
				.createAlias("inscripcionBuscada.alumno", "alumnoBuscado")
				.createAlias("alumnoBuscado.usuario", "usuarioBuscado");
				
				if(apellido != null) {
				criteria.add(Restrictions.like("usuarioBuscado.apellido","%" + apellido + "%"));
				}
				
				if(nombre != null) {
					criteria.add(Restrictions.like("usuarioBuscado.nombre","%" + nombre + "%"));
				}
			
				return criteria.list();
		
	}

	
}

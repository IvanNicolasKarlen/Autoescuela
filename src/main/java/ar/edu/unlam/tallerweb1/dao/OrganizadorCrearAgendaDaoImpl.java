package ar.edu.unlam.tallerweb1.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.stereotype.Repository;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;

@Repository("organizadorCrearAgendaDao")
public class OrganizadorCrearAgendaDaoImpl implements OrganizadorCrearAgendaDao {
	@Inject
    private SessionFactory sessionFactory;
	
	
	@Override
	public Long crearAgenda(Agenda agenda) {
		final Session sesion = sessionFactory.getCurrentSession();
			Long id = (Long)sesion.save(agenda);
		return id;

	}

}

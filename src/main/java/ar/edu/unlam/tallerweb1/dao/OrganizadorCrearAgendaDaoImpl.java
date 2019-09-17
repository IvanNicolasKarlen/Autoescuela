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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<InstructorVehiculoEspecialidad> traerListaIve(){
		final Session sesion = sessionFactory.getCurrentSession();
		List<InstructorVehiculoEspecialidad> listaIve = new ArrayList<InstructorVehiculoEspecialidad>();
		listaIve = sesion.createCriteria(InstructorVehiculoEspecialidad.class).list();
		return listaIve;
	}
	
	@Override
	public String crearAgenda(LocalDate desde, LocalDate hasta) {
		final Session sesion = sessionFactory.getCurrentSession();
		List <Agenda> agendas = new ArrayList<Agenda>();

		List<InstructorVehiculoEspecialidad> listaIve = this.traerListaIve();
		for(LocalDate date = desde; desde.isBefore(hasta); date = date.plusDays(1)){
			for(InstructorVehiculoEspecialidad ive:listaIve){
				for(Integer i=9;i<=18;i++){
					agendas.add(new Agenda());
					agendas.get(agendas.size()-1).setFecha(date.toString());
					agendas.get(agendas.size()-1).setHora(i);
					agendas.get(agendas.size()-1).setInstructorVehiculoEspecialidad(ive);
				}
			}
		}
		for(Agenda ag:agendas){
			sesion.save(ag);
		}
		Integer agendaw = agendas.size();
		return agendaw.toString();

		
	}

}

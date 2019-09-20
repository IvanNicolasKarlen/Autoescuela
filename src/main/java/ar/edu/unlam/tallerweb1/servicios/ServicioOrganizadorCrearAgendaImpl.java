package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.IveDao;
import ar.edu.unlam.tallerweb1.dao.OrganizadorCrearAgendaDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;

@Service
@Transactional
public class ServicioOrganizadorCrearAgendaImpl implements ServicioOrganizadorCrearAgenda {
	@Inject
	private OrganizadorCrearAgendaDao organizadorCrearAgendaDao;
	@Inject
	private IveDao iveDao;
		public Long crearAgenda(LocalDate desde, LocalDate hasta){
			List <Agenda> agendas = new ArrayList<Agenda>(); 
			List <InstructorVehiculoEspecialidad> listaIve = iveDao.traerListaIve();
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
			Long id=null;
			for(Agenda varAgendas:agendas){
				id = organizadorCrearAgendaDao.crearAgenda(varAgendas);
			}
			return id;
		}
}

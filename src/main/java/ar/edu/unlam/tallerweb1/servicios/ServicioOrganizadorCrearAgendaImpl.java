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
import ar.edu.unlam.tallerweb1.modelo.Asistencia;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;

@Service
@Transactional
public class ServicioOrganizadorCrearAgendaImpl implements ServicioOrganizadorCrearAgenda {
	@Inject
	private OrganizadorCrearAgendaDao organizadorCrearAgendaDao;
	@Inject
	private IveDao iveDao;
	
	
	/************************************ORGANIZADOR******************************/
	public Long crearAgenda(Asistencia asistencia, LocalDate desde, LocalDate hasta, Integer horaC, Integer horaF, List<InstructorVehiculoEspecialidad> listaIve){
		List <Agenda> agendas = new ArrayList<Agenda>();
		for(LocalDate date = desde; date.isBefore(hasta); date = date.plusDays(1)){
			for(InstructorVehiculoEspecialidad ive:listaIve){
				for(Integer i=horaC;i<=horaF;i=i+100){
					Agenda ag = new Agenda();
					ag.setFecha(date.toString());
					ag.setHora(i);
					ag.setInstructorVehiculoEspecialidad(ive);
					ag.setAsistencia(asistencia);
					ag.setClasePagada(false);
					agendas.add(ag);
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

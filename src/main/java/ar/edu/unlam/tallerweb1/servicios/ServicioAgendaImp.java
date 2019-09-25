package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoAgendaDao;
import ar.edu.unlam.tallerweb1.dao.ServicioAgendaDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
@Service("servicioAgenda")
@Transactional
public class ServicioAgendaImp implements ServicioAgenda{
	@Inject
	private ServicioAgendaDao  servicioAgendaDao;
	@Override
	public List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor) {
		return servicioAgendaDao.buscarDiaYHorarioDeTurnoDeUnInstructor(idInstructor);
	}
	@Override
	public List<Alumno> buscarNombreyApellidoDeAlumnosDeUnInstructor(Long idInstructor) {
		return servicioAgendaDao.buscarNombreyApellidoDeAlumnosDeUnInstructor(idInstructor);
	}
}

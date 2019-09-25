package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;


import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.InstructorMetodoQueBuscaTurnosDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;

@Service("servicioInstructorMetodoQueBuscaTurnos")
@Transactional

public class ServicioInstructorMetodoQueBuscaTurnosImpl implements ServicioInstructorMetodoQueBuscaTurnos{
	
	@Inject
	private InstructorMetodoQueBuscaTurnosDao buscaTurnoDao;

	@Override
	public List<Agenda> buscarTurnos(Long idInstructor) {
		return buscaTurnoDao.buscarTurnos(idInstructor);
	}

}

package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.InstructorDao;
import ar.edu.unlam.tallerweb1.modelo.Instructor;


@Service
@Transactional
public class ServicioInstructorImpl implements ServicioInstructor {
	@Inject
	private InstructorDao instructorDao;
	
	@Override
	public Long agregarInstructor(Instructor instructor) {
		return instructorDao.agregarInstructor(instructor);
	}

	@Override
	public Instructor buscarInstructorPorId(Long id) {
		return instructorDao.buscarInstructorPorId(id);
	}

}

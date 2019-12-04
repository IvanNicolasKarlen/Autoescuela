package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.InstructorDao;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;


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

	
	/******************************INSTRUCTOR*******************************************/

	@Override
	public Instructor buscarInstructor(Long idInstructor) {
		Usuario usuario =	instructorDao.buscarUsuario(idInstructor);
		return instructorDao.buscarInstructor( usuario.getInstructor().getId());
	}

	@Override
	public Usuario buscarUsuario(Long idInstructor) {

		return instructorDao.buscarUsuario(idInstructor);
	}

	
}

package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoMetodoGuardaCursoDao;
import ar.edu.unlam.tallerweb1.dao.InstructorQueTraeAlumnoDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;

@Service("ServicioInstructorQueTraeAlumno")
@Transactional

public class ServicioInstructorQueTraeAlumnoImpl implements ServicioInstructorQueTraeAlumno {
	
	@Inject
	private InstructorQueTraeAlumnoDao instructorQueTraeAlumnoDao;

	@Override
	public List <Alumno> buscarAlumnosDeInstructor(Long idInstructor) {
		return instructorQueTraeAlumnoDao.buscarAlumnosDeInstructor(idInstructor);
	}

}

package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;


import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.InstructorQueTraeAlumnoDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service("servicioInstructorQueTraeAlumno")
@Transactional

public class ServicioBuscarAlumnosDelInstuctorImp implements ServicioBuscarAlumnosDelInstuctor {
	
	@Inject
	private InstructorQueTraeAlumnoDao instructorQueTraeAlumnoDao;

	@Override
	public List <Alumno> buscarAlumnosDeInstructor(Long idInstructor,String nombre, String apellido) {
		return instructorQueTraeAlumnoDao.buscarAlumnosDeInstructor(idInstructor,nombre,apellido);
	}

}

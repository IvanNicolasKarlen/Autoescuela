package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoConsultaEspecialidadDaoImpl;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;

@Service("servicioAlumnoConsultaEspecialidad")
@Transactional
public class ServicioAlumnoConsultaEspecialidadImpl implements ServicioAlumnoConsultaEspecialidad {
	@Inject
	AlumnoConsultaEspecialidadDaoImpl servicioAlumnoConsultaEspecialidadDao;
	//servicioAlumnoConsultaEspecialidad
	@Override
	public Especialidad consultarEspecialidadCursoElegido(Curso cursoElegido) {
		return servicioAlumnoConsultaEspecialidadDao.consultarEspecialidadCursoElegido(cursoElegido);
		
	}

}

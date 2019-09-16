package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.dao.BuscarInstructorPorIdDao;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

@Service("ServicioBuscarInstructorPorId")
public class ServicioBuscarInstructorPorIdImpl implements ServicioBuscarInstructorPorId {

	@Inject
	public BuscarInstructorPorIdDao buscarInstructorPorIdDao;
	
	@Override
	public Instructor buscarInstructorPorId(Long idUsuario) {
		
		return buscarInstructorPorIdDao.buscarInstructorPorId(idUsuario);
		
		
		
	}

}

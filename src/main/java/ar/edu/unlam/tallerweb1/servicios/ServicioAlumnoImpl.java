package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoDao;
import ar.edu.unlam.tallerweb1.modelo.Alumno;

@Service("ServicioAlumno")
@Transactional
public class ServicioAlumnoImpl implements ServicioAlumno {

	@Inject
	private AlumnoDao alumnoDao;

	@Override
	public Alumno buscarAlumno(Long idAlumno) {
		return alumnoDao.buscarAlumno( idAlumno);
	}
	
	
}

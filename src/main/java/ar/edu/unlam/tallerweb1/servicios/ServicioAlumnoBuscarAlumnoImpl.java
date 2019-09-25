package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoBuscarAlumnoDao;
import ar.edu.unlam.tallerweb1.modelo.Alumno;

@Service("ServicioAlumnoBuscarAlumno")
@Transactional
public class ServicioAlumnoBuscarAlumnoImpl implements ServicioAlumnoBuscarAlumno {


	@Inject
	private AlumnoBuscarAlumnoDao servicioBuscarAlumnoDao;

	
	@Override
	public Alumno buscarAlumno(Long idAlumno) {
		return servicioBuscarAlumnoDao.buscarAlumno( idAlumno);
	}

	

}

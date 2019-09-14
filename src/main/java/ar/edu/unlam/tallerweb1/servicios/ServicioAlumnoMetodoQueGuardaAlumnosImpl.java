package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoMetodoQueGuardaAlumnosDao;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Alumno;

@Service("ServicioAlumnoMetodoQueGuardaAlumnos")
@Transactional
public class ServicioAlumnoMetodoQueGuardaAlumnosImpl implements ServicioAlumnoMetodoQueGuardaAlumnos {

	@Inject
	private AlumnoMetodoQueGuardaAlumnosDao guardaAlumnosDao;

	@Override
	public void guardarAlumno(Alumno alumno) {
		guardaAlumnosDao.guardarAlumno( alumno);
		
	}

	
	
	
	
}

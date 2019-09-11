package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoMetodoFinalizoCursoDao;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

@Service("ServicioAlumnoMetodoFinalizoCurso")
@Transactional
public class ServicioAlumnoMetodoFinalizoCursoImpl implements ServicioAlumnoMetodoFinalizoCurso {

	@Inject
	private AlumnoMetodoFinalizoCursoDao servicioFinalizoCursoDao;

	@Override
	public TablaCursoAlumno consultarSiFinalizoSuCurso(Alumno alumno, Curso cursoElegido) {
		// TODO Auto-generated method stub
		return servicioFinalizoCursoDao.consultarSiFinalizoSuCurso( alumno,  cursoElegido);
	}

	
	

}

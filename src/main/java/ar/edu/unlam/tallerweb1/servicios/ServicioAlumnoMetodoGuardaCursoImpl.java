package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoMetodoGuardaCursoDao;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

@Service("ServicioAlumnoMetodoGuardaCurso")
@Transactional
public class ServicioAlumnoMetodoGuardaCursoImpl implements ServicioAlumnoMetodoGuardaCurso {

	@Inject
	private AlumnoMetodoGuardaCursoDao servicioGuardarCursoDao;

	@Override
	public void guardarCurso(Alumno alumno, Curso cursoElegido, TablaCursoAlumno cursoAlumno) {
		servicioGuardarCursoDao.guardarCurso( alumno,  cursoElegido,  cursoAlumno);
	}


}

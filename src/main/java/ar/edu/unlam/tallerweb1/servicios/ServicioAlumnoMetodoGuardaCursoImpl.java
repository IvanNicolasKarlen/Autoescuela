package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoMetodoGuardaCursoDao;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

@Service("ServicioAlumnoMetodoGuardaCurso")
@Transactional
public class ServicioAlumnoMetodoGuardaCursoImpl implements ServicioAlumnoMetodoGuardaCurso {

	@Inject
	private AlumnoMetodoGuardaCursoDao servicioGuardarCursoDao;

	

	@Override
	public void guardarCurso(Alumno Alumno, Curso cursoElegido, TablaCursoAlumno cursoAlumno, EstadoDelCurso estado) {
		servicioGuardarCursoDao.guardarCurso( Alumno,  cursoElegido,  cursoAlumno, estado);
		
	}


}

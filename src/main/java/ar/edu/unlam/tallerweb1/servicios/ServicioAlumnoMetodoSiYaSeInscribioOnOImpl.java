package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoMetodoSiYaSeInscribioOnODao;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

@Service("ServicioCursoMetodoSiYaSeInscribioOnO")
@Transactional
public class ServicioAlumnoMetodoSiYaSeInscribioOnOImpl implements ServicioAlumnoMetodoSiYaSeInscribioOnO {

	@Inject
	private AlumnoMetodoSiYaSeInscribioOnODao servicioInscriptoOnoDao;

	@Override
	public List<TablaCursoAlumno> consultarSiYaSeInscribioAUnCurso(Long alumno, EstadoDelCurso estado, Especialidad esp) {
		
		return servicioInscriptoOnoDao.consultarSiYaSeInscribioAUnCurso( alumno, estado,esp);
	}

	

}

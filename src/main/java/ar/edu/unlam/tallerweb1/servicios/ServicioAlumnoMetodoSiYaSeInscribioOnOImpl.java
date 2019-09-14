package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoMetodoSiYaSeInscribioOnODao;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

@Service("ServicioCursoMetodoSiYaSeInscribioOnO")
@Transactional
public class ServicioAlumnoMetodoSiYaSeInscribioOnOImpl implements ServicioAlumnoMetodoSiYaSeInscribioOnO {

	@Inject
	private AlumnoMetodoSiYaSeInscribioOnODao servicioInscriptoOnoDao;

	@Override
	public List<TablaCursoAlumno> consultarSiYaSeInscribioAUnCurso(Alumno alumno, Curso cursoElegido) {
		
		return servicioInscriptoOnoDao.consultarSiYaSeInscribioAUnCurso( alumno,  cursoElegido);
	}

	

}

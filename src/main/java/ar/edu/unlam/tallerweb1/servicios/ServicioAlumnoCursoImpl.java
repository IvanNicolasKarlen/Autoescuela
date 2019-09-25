package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.dao.AlumnoCursoDao;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

@Service("ServicioAlumnoCursoImpl")
public class ServicioAlumnoCursoImpl implements ServicioAlumnoCurso {
	
	@Inject
	private AlumnoCursoDao alumoCursoDao;

	@Override
	public List<Curso> buscarCursos() {
	
		return alumoCursoDao.buscarCursos() ;
	}

	@Override
	public Curso buscarCurso(Curso cursoElegido) {
		
		return alumoCursoDao.buscarCurso( cursoElegido);
	}

	@Override
	public List<TablaCursoAlumno> consultarSiYaSeInscribioAUnCurso(Long idAlumno, EstadoDelCurso estado,
			Especialidad especialidad) {
		
		return alumoCursoDao.consultarSiYaSeInscribioAUnCurso( idAlumno, estado, especialidad);
	}

	@Override
	public void guardarCurso(Alumno alumno, Curso cursoElegido, TablaCursoAlumno cursoAlumno, EstadoDelCurso estado) {
		alumoCursoDao.guardarCurso( alumno,  cursoElegido,  cursoAlumno,  estado);
		
	}

}

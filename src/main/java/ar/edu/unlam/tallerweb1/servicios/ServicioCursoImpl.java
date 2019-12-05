package ar.edu.unlam.tallerweb1.servicios;


import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.tallerweb1.dao.CursoDao;
import ar.edu.unlam.tallerweb1.dao.EstadoInscripcionDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;

@Service("ServicioCurso")
@Transactional
public class ServicioCursoImpl implements ServicioCurso{

	@Inject
	private CursoDao  cursoDao;
	@Inject
	private EstadoInscripcionDao estadoinscripcionDao;
	
	
	
	
	
	
	@Override
	public Long agregarCurso(Curso curso) {
		return cursoDao.agregarCurso(curso);
	}

	@Override
	public Curso buscarCurso(Curso curso) {
		return cursoDao.buscarCurso(curso);
	}

	@Override
	public List<Curso> traerListaDeCursos() {
		return cursoDao.traerListaDeCursos();
	}

	@Override
	public Curso buscarCursoPorId(Long cursoid) {
		return cursoDao.buscarCursoPorId(cursoid);
	}

	@Override
	public void eliminarCurso(Curso curso) {
		cursoDao.eliminarCurso(curso);
	}

	@Override
	public void modificarCurso(Curso curso) {
		cursoDao.modificarCurso(curso);
	}

	@Override
	public List<Curso> traerCursosPorEspecialidad(String especialidad) {
		return cursoDao.traerCursosPorEspecialidad(especialidad);
	}

	
	

/*****************MOCK*****************/

public void setCursoDao(CursoDao cursoDao) {
	this.cursoDao = cursoDao;
}
	
}
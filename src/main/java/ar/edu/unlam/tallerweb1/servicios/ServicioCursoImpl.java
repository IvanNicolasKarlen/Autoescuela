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
	public List<Curso> buscarCursos() {
	
		return cursoDao.buscarCursos() ;
	}
	
	
	@Override
	public Curso buscarCurso(Long cursoElegido) {
		
		return cursoDao.buscarCurso( cursoElegido);
	}


	
}

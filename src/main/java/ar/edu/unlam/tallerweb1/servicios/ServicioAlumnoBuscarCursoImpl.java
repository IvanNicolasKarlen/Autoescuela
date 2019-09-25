package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoBuscarCursoDao;
import ar.edu.unlam.tallerweb1.modelo.Curso;

@Service("ServicioAlumnoBuscarCurso")
@Transactional
public class ServicioAlumnoBuscarCursoImpl implements ServicioAlumnoBuscarCurso {

	@Inject
	AlumnoBuscarCursoDao buscarCursoDao;

	@Override
	public Curso buscarCurso(Curso cursoElegido) {
		return buscarCursoDao.buscarCurso( cursoElegido);
	}
	
	
}

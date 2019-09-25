package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoMetodoQueBuscaCursosDao;
import ar.edu.unlam.tallerweb1.modelo.Curso;

@Service("ServicioAlumnoMetodoQueBuscaCursos")
@Transactional
public class ServicioAlumnoMetodoQueBuscaCursosImpl implements ServicioAlumnoMetodoQueBuscaCursos {

	@Inject
	private AlumnoMetodoQueBuscaCursosDao buscaCursoDao;

	@Override
	public List<Curso> buscarCursos() {
		
		return buscaCursoDao.buscarCursos();
	}

	
	

}

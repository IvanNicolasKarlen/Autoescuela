package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.OrganizadorCursoDao;
import ar.edu.unlam.tallerweb1.modelo.Curso;
@Service
@Transactional
public class ServicioOrganizadorCursoImpl implements ServicioOrganizadorCurso {
	@Inject
	private OrganizadorCursoDao organizadorCursoDao;
	@Override
	public Long agregarCurso(Curso curso) {
		return organizadorCursoDao.agregarCurso(curso);
	}

	@Override
	public Curso buscarCurso(Curso curso) {
		return organizadorCursoDao.buscarCurso(curso);
	}

	@Override
	public List<Curso> traerListaDeCursos() {
		return organizadorCursoDao.traerListaDeCursos();
	}

	@Override
	public Curso buscarCursoPorId(Long cursoid) {
		return organizadorCursoDao.buscarCursoPorId(cursoid);
	}

	@Override
	public void eliminarCurso(Curso curso) {
		organizadorCursoDao.eliminarCurso(curso);
	}

}

package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.OrganizadorInscripcionDao;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
@Service
@Transactional
public class ServicioOrganizadorInscripcionImpl implements ServicioOrganizadorInscripcion {
	@Inject
	private OrganizadorInscripcionDao inscripcionDao;
	@Override
	public List<Inscripcion> traerInscripcionesDeUnCurso(Curso curso) {
		return inscripcionDao.traerInscripcionesDeUnCurso(curso);
	}

}

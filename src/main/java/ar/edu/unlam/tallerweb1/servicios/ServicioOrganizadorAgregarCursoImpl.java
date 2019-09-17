package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.OrganizadorAgregarCursoDao;
import ar.edu.unlam.tallerweb1.modelo.Curso;
@Service("servicioOrganizadorAgregarCurso")
@Transactional
public class ServicioOrganizadorAgregarCursoImpl implements ServicioOrganizadorAgregarCurso {
	@Inject
	private OrganizadorAgregarCursoDao agregarCursodao;
	@Override
	public Boolean agregarCurso(Curso curso) {
		return agregarCursodao.agregarCurso(curso);
	}

}

package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.OrganizadorInstructorDao;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service
@Transactional
public class ServicioOrganizadorInstructorImpl implements ServicioOrganizadorInstructor {
	@Inject
	private OrganizadorInstructorDao organizadorInstructorDao;
	
	@Override
	public Long agregarInstructor(Usuario usuario, Instructor instructor) {
		return organizadorInstructorDao.agregarInstructor(instructor,usuario);
	}

	@Override
	public Instructor buscarInstructorPorId(Long id) {
		return organizadorInstructorDao.buscarInstructorPorId(id);
	}

}

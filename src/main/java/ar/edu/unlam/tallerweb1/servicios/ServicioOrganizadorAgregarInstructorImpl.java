package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.OrganizadorAgregarInstructorDao;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service
@Transactional
public class ServicioOrganizadorAgregarInstructorImpl implements ServicioOrganizadorAgregarInstructor {
	@Inject
	private OrganizadorAgregarInstructorDao organizadorAgregarInstructorDao;
	
	@Override
	public Long agregarInstructor(Usuario usuario, Instructor instructor) {
		return organizadorAgregarInstructorDao.agregarInstructor(instructor,usuario);
	}

}

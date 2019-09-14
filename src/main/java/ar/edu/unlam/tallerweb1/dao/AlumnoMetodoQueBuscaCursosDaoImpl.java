package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Curso;


@Repository("CursoMetodoQueBuscaCursosDao")
public class AlumnoMetodoQueBuscaCursosDaoImpl implements AlumnoMetodoQueBuscaCursosDao {

	// Como todo dao maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	@Inject
    private SessionFactory sessionFactory;

	
	@Override
	public List<Curso> buscarCursos() {
		final Session session = sessionFactory.getCurrentSession();
		
		List<Curso> milista = session.createCriteria(Curso.class)
								.list();
		
		return milista;
	}
}

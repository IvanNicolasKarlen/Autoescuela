package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;

@Repository("AlumnoEspecialidadDao")
public class AlumnoEspecialidadDaoImpl implements AlumnoEspecialidadDao {

	@Inject
	private SessionFactory sessionfactory;

	@Override
	public Especialidad consultarEspecialidadCursoElegido(Curso cursoElegido) {
		final Session session=sessionfactory.getCurrentSession();
		Curso c = (Curso) session.createCriteria(Curso.class)
		.createAlias("especialidad", "esp")
		.add(Restrictions.eq("id", cursoElegido.getId()))
		.uniqueResult();
		
		return c.getEspecialidad();
	}
	
	
	
}

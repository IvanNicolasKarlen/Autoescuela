package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Curso;

@Repository("AlumnoBuscarCursoDao")
public class AlumnoBuscarCursoDaoImpl implements AlumnoBuscarCursoDao {

	
	@Inject
	private SessionFactory sessionfactory;

	@Override
	public Curso buscarCurso(Curso cursoElegido) {
		final Session session = sessionfactory.getCurrentSession();
		
		return (Curso) session.createCriteria(Curso.class)
				.add(Restrictions.eq("id",cursoElegido.getId()))
				.uniqueResult();
		
		
	}
	
	
}

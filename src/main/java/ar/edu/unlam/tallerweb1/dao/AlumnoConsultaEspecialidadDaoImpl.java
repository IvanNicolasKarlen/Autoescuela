package ar.edu.unlam.tallerweb1.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;

@Repository("AlumnoConsultaEspecialidadDao")
public class AlumnoConsultaEspecialidadDaoImpl implements AlumnoConsultaEspecialidadDao  {

	@Inject 
	private SessionFactory sessionFactory;
	
	@Override
	public Especialidad consultarEspecialidadCursoElegido(Curso cursoElegido) {
		final Session session=sessionFactory.getCurrentSession();
		Curso c = (Curso) session.createCriteria(Curso.class)
		.createAlias("especialidad", "esp")
		.add(Restrictions.eq("id", cursoElegido.getId()))
		.uniqueResult();
		
		return c.getEspecialidad();
		 
	}
}
	
	
	
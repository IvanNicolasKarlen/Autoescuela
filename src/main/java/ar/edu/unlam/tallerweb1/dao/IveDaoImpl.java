package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;

@Repository
public class IveDaoImpl implements IveDao {
	@Inject
	private SessionFactory sessionFactory;
	
	
	/****************************************ORGANIZADOR******************************************/
	public Long guardarIve(InstructorVehiculoEspecialidad ive){
		return (Long)sessionFactory.getCurrentSession().save(ive);
	}

	@Override
	public List<InstructorVehiculoEspecialidad> traerListaIve(){
		final Session sesion = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<InstructorVehiculoEspecialidad> listaIve = sesion.createCriteria(InstructorVehiculoEspecialidad.class)
														.list();
		return listaIve;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstructorVehiculoEspecialidad> traerListaIvePorEspecialidad(Especialidad especialidad) {
		final Session session = sessionFactory.getCurrentSession();
		return (List<InstructorVehiculoEspecialidad>)
				session.createCriteria(InstructorVehiculoEspecialidad.class)
				.add(Restrictions.eq("especialidad", especialidad)).list();
	}

	@Override
	public InstructorVehiculoEspecialidad traerIvePorInstructorEspecialidad(Especialidad esp, Instructor ins) {
		final Session session = sessionFactory.getCurrentSession();
		return (InstructorVehiculoEspecialidad) session.createCriteria(InstructorVehiculoEspecialidad.class)
				.add(Restrictions.eq("especialidad", esp))
				.add(Restrictions.eq("instructor", ins))
				.uniqueResult();
	}

	@Override
	public InstructorVehiculoEspecialidad buscarIvePorId(Long idIVE) {
		final Session session = sessionFactory.getCurrentSession();
		return (InstructorVehiculoEspecialidad) session.get(InstructorVehiculoEspecialidad.class, idIVE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InstructorVehiculoEspecialidad> traerIvePorInstructor(Instructor ins) {
		final Session session = sessionFactory.getCurrentSession();
		return (List<InstructorVehiculoEspecialidad>) session.createCriteria(InstructorVehiculoEspecialidad.class)
				.add(Restrictions.eq("instructor", ins)).list();
	}




}
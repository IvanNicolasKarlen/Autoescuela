package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.TipoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
@Repository
public class EspecialidadDaoImpl implements EspecialidadDao {
	@Inject
	private SessionFactory sessionFactory;
	
/************************************** Organizador ***********************/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Especialidad> traerListaDeEspecialidades() {
		final Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Especialidad.class).list();
	}
	@Override
	public Especialidad traerEspecialidadPorId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return (Especialidad)session.get(Especialidad.class, id);
	}
	@Override
	public Especialidad traerEspecialidadPorNombre(String tipoEsp) {
		final Session session = sessionFactory.getCurrentSession();
		return (Especialidad)session.createCriteria(Especialidad.class)
				.add(Restrictions.eq("tipo", tipoEsp)).uniqueResult();
	}
	@Override
	public Long guardarEspecialidad(Especialidad especialidad) {
		final Session session = sessionFactory.getCurrentSession();
		return (Long)session.save(especialidad);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Especialidad> traerEspecialidadesQueUnInstructorNoTenga(Long idInstructor) {
		final Session session = sessionFactory.getCurrentSession();
		return (List<Especialidad>) session.createCriteria(Especialidad.class)
									.createAlias("instructoresVehiculosEspecialidades", "iveBuscada")
									.createAlias("iveBuscada.instructor", "instructorBuscado")
									.add(Restrictions.not(Restrictions.eq("instructorBuscado.id", idInstructor)))
									.list();
	}

	
	
	
	
	/**************************** Alumno **********************************/
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
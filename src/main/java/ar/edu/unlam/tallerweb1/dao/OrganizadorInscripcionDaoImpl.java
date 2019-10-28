package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
@Repository
public class OrganizadorInscripcionDaoImpl implements OrganizadorInscripcionDao {
	@Inject
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<Inscripcion> traerInscripcionesDeUnCurso(Curso curso) {
		Session session = sessionFactory.getCurrentSession();
		return (List<Inscripcion>) session.createCriteria(Inscripcion.class)
									.add(Restrictions.eq("curso", curso))
									.list();
	}

}

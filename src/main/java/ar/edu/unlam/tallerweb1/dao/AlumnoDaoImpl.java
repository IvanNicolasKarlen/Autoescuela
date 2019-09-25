package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Alumno;

@Repository("AlumnoDao")
public class AlumnoDaoImpl implements AlumnoDao {

	@Inject
	private SessionFactory sessionFactory;

	@Override
	public Alumno buscarAlumno(Long idAlumno) {
final Session session = sessionFactory.getCurrentSession();
		
		return (Alumno) session.createCriteria(Alumno.class)
				.add(Restrictions.eq("id", idAlumno))
				.uniqueResult();
	}
	
	
}

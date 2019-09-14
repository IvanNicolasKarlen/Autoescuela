package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

@Repository("AlumnoMetodoFinalizoCursoDao")
public class AlumnoMetodoFinalizoCursoDaoImpl implements AlumnoMetodoFinalizoCursoDao {

	// Como todo dao maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	@Inject
    private SessionFactory sessionFactory;

	@Override
	public TablaCursoAlumno consultarSiFinalizoSuCurso(Alumno alumno, Curso curso) {
		final Session session = sessionFactory.getCurrentSession();
		
		Long idCurso = (Long) curso.getId();
		Long idAlumno = (Long) alumno.getId();
		
		return (TablaCursoAlumno) session.createCriteria(TablaCursoAlumno.class)
				.add(Restrictions.eq("alumno.id", idAlumno))
				.add(Restrictions.eq("curso.id", idCurso))
				.uniqueResult();

	}

}

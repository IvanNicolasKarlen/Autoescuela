package ar.edu.unlam.tallerweb1.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;
@Repository("AlumnoMetodoGuardaCursoDao")
public class AlumnoMetodoGuardaCursoDaoImpl implements AlumnoMetodoGuardaCursoDao {

	// Como todo dao maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	@Inject
    private SessionFactory sessionFactory;


	@Override
	public void guardarCurso( Alumno alumno, Curso curso, TablaCursoAlumno cursoAlumno) {
		final Session session = sessionFactory.getCurrentSession();
		
		cursoAlumno.setCurso(curso);
		cursoAlumno.setAlumno(alumno);
		cursoAlumno.setEstado("Cursando");
		session.save(cursoAlumno);
		
	}
}

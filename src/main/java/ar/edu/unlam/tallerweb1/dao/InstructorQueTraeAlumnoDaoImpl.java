package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
@Repository("InstructorQueTraeAlumnoDao")

public class InstructorQueTraeAlumnoDaoImpl implements InstructorQueTraeAlumnoDao {

	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public List <Alumno> buscarAlumnosDeInstructor(Long idInstructor) {

		final Session session = sessionFactory.getCurrentSession();
		List <Alumno> milista = session.createCriteria(Alumno.class)
				
		
		
		
		
		
	}

}

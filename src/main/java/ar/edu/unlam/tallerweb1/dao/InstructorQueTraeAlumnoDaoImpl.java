package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
@Repository("InstructorQueTraeAlumnoDao")

public class InstructorQueTraeAlumnoDaoImpl implements InstructorQueTraeAlumnoDao {

	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public List <Alumno> buscarAlumnosDeInstructor(Long idInstructor,String nombre, String apellido) {

		final Session session = sessionFactory.getCurrentSession();
		List <Alumno> milista =  session.createCriteria(Alumno.class)
								.createAlias("usuario", "usuarioBuscado")
								.list();
		return milista;
		
	}

}

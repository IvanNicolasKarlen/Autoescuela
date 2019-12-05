package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository("AlumnoDao")
public class AlumnoDaoImpl implements AlumnoDao {

	@Inject
	private SessionFactory sessionFactory;
/*************MOCK**********/
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	

	
	/************************************ INSTRUCTOR ***********************************/
	
	@Override
	public List<Alumno> buscarAlumnos(String nombre,String apellido) {
		final Session session = sessionFactory.getCurrentSession();
		Criteria criteria =  session.createCriteria(Alumno.class)
		.createAlias("usuario", "usuarioBuscado");
				
				if(apellido != null) {
				criteria.add(Restrictions.like("usuarioBuscado.apellido","%" + apellido + "%"));
				}
				
				if(nombre != null) {
					criteria.add(Restrictions.like("usuarioBuscado.nombre","%" + nombre + "%"));
				}
				
				return criteria.list();
		
	}
	
	
	
	
	
	/****************************************************************************/
	
	
	
	
	
	@Override
	public Alumno buscarAlumno(Long idAlumno) {
final Session session = sessionFactory.getCurrentSession();
		
		return (Alumno) session.createCriteria(Alumno.class)
				.add(Restrictions.eq("id", idAlumno))
				.uniqueResult();
	}


	@Override
	public Usuario buscarUsuario(Long idSesion) {
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.get(Usuario.class, idSesion);
	}

	
	
}

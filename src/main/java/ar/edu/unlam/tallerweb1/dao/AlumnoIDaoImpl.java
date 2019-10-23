package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
@Repository("alumnoIDao")
public class AlumnoIDaoImpl implements AlumnoIDao {
	@Inject
    private SessionFactory sessionFactory;
	
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
}

package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

// implelemtacion del DAO de usuarios, la anotacion @Repository indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.dao
// para encontrar esta clase.
@Repository("usuarioDao")
public class UsuarioDaoImpl implements UsuarioDao {

	// Como todo dao maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	@Inject
    private SessionFactory sessionFactory;

		
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}



	/****************************************** INSTRUCTOR **************************************/
	
	@Override
	public List<Usuario> traerAlumnos(Long idInstructor) {
		final Session session = sessionFactory.getCurrentSession();
			return session.createCriteria(Usuario.class)
			.add(Restrictions.eq("rol", "Alumno"))
			.setProjection(Projections.projectionList()
			.add(Projections.distinct(Projections.property("email")))
			)
			.list();
		
		}
	
	
	
	@Override
	public Usuario consultarUsuario(Usuario usuario) {

		// Se obtiene la sesion asociada a la transaccion iniciada en el servicio que invoca a este metodo y se crea un criterio
		// de busqueda de Usuario donde el email y password sean iguales a los del objeto recibido como parametro
		// uniqueResult da error si se encuentran más de un resultado en la busqueda.
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
				.add(Restrictions.eq("nombreDeUsuario", usuario.getNombreDeUsuario()))
				.add(Restrictions.eq("password", usuario.getPassword()))
				.uniqueResult();
	}
	@Override
	public 	Long insertarUsuario (Usuario usuario){
		final Session session = sessionFactory.getCurrentSession();
		return (Long)session.save(usuario);		
	}
	

	@Override
	public Usuario traerUsuarioPorId(Long user) {
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.get(Usuario.class, user);
	}
	
	/****************************O R G A N I Z A D X R ******************///////////////

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> traerUsuarios(String nombre, String apellido, String nombreUsuario, Integer dni,
			String traer) {
		final Session session = sessionFactory.getCurrentSession();
		Criteria query = session.createCriteria(Usuario.class);
		if(nombre!=null){
			query = query.add(Restrictions.like("nombre", nombre));
		}
		if(apellido!=null){
			query = query.add(Restrictions.like("apellido", apellido));
		}
		if(nombreUsuario!=null){
			query = query.add(Restrictions.like("nombreDeUsuario", nombreUsuario));
		}
		if(dni!=null){
			query = query.add(Restrictions.eq("dni", dni));
		}
		switch(traer){
			case "Alumno": query = query.add(Restrictions.isNotNull("alumno")).add(Restrictions.eq("rol", traer));
							break;
			case "Instructor": query = query.add(Restrictions.isNotNull("instructor")).add(Restrictions.eq("rol", traer));
							break;
			case "Todo": query = query.add(Restrictions.isNull("organizador"));
								break;
		}
		
		return (List<Usuario>) query.list();
		
	}



	@Override
	public Usuario traerUsuarioPorNombreUsuario(String nombreUser) {
		final Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class)
						.add(Restrictions.like("nombreDeUsuario", nombreUser)).uniqueResult();
	}



	
	
	
	
	
	
	
	
	
	
	
	
	/****************************************************************************************/
	
	
	
	
	
	
	
	
	
	
	
	
	/****************************************************************************************/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
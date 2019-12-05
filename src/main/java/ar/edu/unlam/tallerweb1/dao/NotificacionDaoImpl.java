package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Repository
public class NotificacionDaoImpl implements NotificacionDao {
	@Inject
	private SessionFactory sessionFactory;
	
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notificacion> traerTodasLasNotificaciones(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
	
		Criteria query = session.createCriteria(Notificacion.class);
		switch(usuario.getRol()){
		case "Alumno":	query = query.createAlias("alumno", "alumnoBuscado")
								.add(Restrictions.eq("alumnoBuscado.usuario", usuario));
						break;
		case "Instructor": query = query.createAlias("instructor", "instructorBuscado")
									.add(Restrictions.eq("instructorBuscado.usuario", usuario));
						break;
		case "Organizador": query = query.createAlias("organizador", "organizadorBuscado")
									.add(Restrictions.eq("organizadorBuscado.usuario", usuario));
						break;
		}
		return (List<Notificacion>) query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notificacion> traerNotificacionesNoLeidas(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		Criteria query = session.createCriteria(Notificacion.class);
		switch(usuario.getRol()){
		case "Alumno":	query = query.createAlias("alumno", "alumnoBuscado")
								.add(Restrictions.eq("alumnoBuscado.usuario", usuario))
								.add(Restrictions.eq("leida", false));
						break;
		case "Instructor": query = query.createAlias("instructor", "instructorBuscado")
									.add(Restrictions.eq("instructorBuscado.usuario", usuario))
									.add(Restrictions.eq("leida", false));
						break;
		case "Organizador": query = query.createAlias("organizador", "organizadorBuscado")
									.add(Restrictions.eq("organizadorBuscado.usuario", usuario))
									.add(Restrictions.eq("leida", false));
						break;
		}
		return (List<Notificacion>) query.list();
	}

	@Override
	public Long crearNotificacion(Notificacion notificacion) {
		final Session session = sessionFactory.getCurrentSession();
		return (Long)session.save(notificacion);

	}

	@Override
	public void modificarNotificacion(Notificacion notificacion) {
		final Session session = sessionFactory.getCurrentSession();
		session.update(notificacion);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notificacion> traerNotificacionesLeidas(Usuario usuario) {
		final Session session = sessionFactory.getCurrentSession();
		Criteria query = session.createCriteria(Notificacion.class);
		switch(usuario.getRol()){
		case "Alumno":	query = query.createAlias("alumno", "alumnoBuscado")
								.add(Restrictions.eq("alumnoBuscado.usuario", usuario))
								.add(Restrictions.eq("leida", true));
						break;
		case "Instructor": query = query.createAlias("instructor", "instructorBuscado")
									.add(Restrictions.eq("instructorBuscado.usuario", usuario))
									.add(Restrictions.eq("leida", true));
						break;
		case "Organizador": query = query.createAlias("organizador", "organizadorBuscado")
									.add(Restrictions.eq("organizadorBuscado.usuario", usuario))
									.add(Restrictions.eq("leida", true));
						break;
		}
		return (List<Notificacion>) query.list();
	}

	@Override
	public Notificacion traerNotificacionPorId(Long id) {
		final Session session = sessionFactory.getCurrentSession();
		return(Notificacion)session.get(Notificacion.class, id);
	}
}
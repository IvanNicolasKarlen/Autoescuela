package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface NotificacionDao {
	List<Notificacion> traerTodasLasNotificaciones(Usuario usuario);
	List<Notificacion> traerNotificacionesNoLeidas(Usuario usuario);
	List<Notificacion> traerNotificacionesLeidas(Usuario usuario);
	Long crearNotificacion (Notificacion notificacion);
	void modificarNotificacion (Notificacion notificacion);
	Notificacion traerNotificacionPorId(Long id);
	void setSessionFactory(SessionFactory sessionFactory);
}


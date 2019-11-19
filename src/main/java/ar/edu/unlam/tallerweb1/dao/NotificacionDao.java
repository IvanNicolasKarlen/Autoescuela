package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface NotificacionDao {
	List<Notificacion> traerTodasLasNotificaciones(Usuario usuario);
	List<Notificacion> traerNotificacionesNoLeidas(Usuario usuario);
	Long crearNotificacion (Notificacion notificacion);
	void modificarNotificacion (Notificacion notificacion);
}
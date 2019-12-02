package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.dao.NotificacionDao;
import ar.edu.unlam.tallerweb1.dao.OrganizadorDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioNotificacion {
	List<Notificacion> traerTodasLasNotificaciones(Usuario usuario);
	List<Notificacion> traerNotificacionesNoLeidas(Usuario usuario);
	List<Notificacion> traerNotificacionesLeidas(Usuario usuario);
	Long crearNotificacion (Usuario usuario, Agenda agenda);
	void modificarNotificacion (Notificacion notificacion);
	void setNotificacionDao(NotificacionDao notificacionDao);
	void setOrganizadorDao(OrganizadorDao organizadorDao);
	Notificacion traerNotificacionPorId(Long id);
}
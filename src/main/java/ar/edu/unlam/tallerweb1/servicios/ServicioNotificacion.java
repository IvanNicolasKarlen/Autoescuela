package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioNotificacion {
	List<Notificacion> traerTodasLasNotificaciones(Usuario usuario);
	List<Notificacion> traerNotificacionesNoLeidas(Usuario usuario);
	Long crearNotificacion (Usuario usuario, Agenda agenda);
	void modificarNotificacion (Notificacion notificacion);
}
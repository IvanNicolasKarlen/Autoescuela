package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.NotificacionDao;
import ar.edu.unlam.tallerweb1.dao.OrganizadorDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Organizador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service
@Transactional
public class ServicioNotificacionImpl implements ServicioNotificacion {
	@Inject
	private NotificacionDao notificacionDao;
	@Inject
	private OrganizadorDao organizadorDao;
	@Override
	public void setNotificacionDao(NotificacionDao notificacionDao) {
		this.notificacionDao = notificacionDao;
	}

	@Override
	public void setOrganizadorDao(OrganizadorDao organizadorDao) {
		this.organizadorDao = organizadorDao;
	}

	@Override
	public List<Notificacion> traerTodasLasNotificaciones(Usuario usuario) {
		return notificacionDao.traerTodasLasNotificaciones(usuario);
	}

	@Override
	public List<Notificacion> traerNotificacionesNoLeidas(Usuario usuario) {
		return notificacionDao.traerNotificacionesNoLeidas(usuario);
	}

	@Override
	public Long crearNotificacion(Usuario usuario, Agenda agenda) {
		
		/*Borro los 00 de la hora militar para poder mostrarla +:00hs*/
		Integer horaAgenda = agenda.getHora()/100; 
		/*Formateo la fecha de hoy para la notificacion*/
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String fechaNoti = (LocalDate.now()).format(formatter);
		/*Traigo todos los organizadores para enviarles la noti*/
		List<Organizador> listaOrg = organizadorDao.traerTodosLosOrganizadores();
		/*Creo una lista de notificaciones q luego guardare en la bd*/
		List <Notificacion> notificacionesGuardar = new ArrayList<Notificacion>();
		/*Creo la notificacion*/
		Notificacion notificacionNueva = new Notificacion();
		/*Seteo sus atributos*/
		notificacionNueva.setLeida(false);
		notificacionNueva.setFecha(fechaNoti);
		notificacionNueva.setMensaje("La clase del dia " +agenda.getFecha() +" a las " 
		+horaAgenda +":00hs ha sido cancelada por " +usuario.getRol()+".");
		notificacionNueva.setDetalles(
				"<p>Clase: " +agenda.getFecha() +", " +horaAgenda +"hs.</p>"
				+"<p>Motivo: " +agenda.getEstadoDeAgenda().getEstado() +".</p>"
				+"<p>Detalles de cancelación: " +agenda.getEstadoDeAgenda().getDetalle() +".</p>"
				+"<p>Cancelada por: " +usuario.getNombreDeUsuario() +" - "+usuario.getNombre() +" "+usuario.getApellido() +".</p>"
				+"<p>Dictada por: " +agenda.getInstructorVehiculoEspecialidad().getInstructor().getUsuario().getNombreDeUsuario()+".</p>"
				+"<p>Tomada por alumno(a): " +agenda.getInscripcion().getAlumno().getUsuario().getNombreDeUsuario() +" - " 
				+agenda.getInscripcion().getAlumno().getUsuario().getNombre() +" " 
				+agenda.getInscripcion().getAlumno().getUsuario().getApellido() +".</p>"
				+"<p><i>Notificacion emitida: " +notificacionNueva.getFecha() +"</i></p>"
				);
		/*Si el q crea la noti NO ES un organizador, seteo a quien enviarsela dependiendo el rol
		 * Si es un alumno, se la envio a un instructor y viceversa*/
		if(!(usuario.getRol().equals("Organizador"))){

			switch(usuario.getRol()){
			case "Alumno": notificacionNueva.setInstructor(agenda.getInstructorVehiculoEspecialidad().getInstructor());
							break;
			case "Instructor": notificacionNueva.setAlumno(agenda.getInscripcion().getAlumno());
							break;					
			};
			/*Recorro la lista de organizadores y creo una nueva notificacion para cada uno*/
			for(Organizador lo:listaOrg){
				Notificacion notiOrg = notificacionNueva;
				notiOrg.setOrganizador(lo);
				notificacionesGuardar.add(notiOrg);
			}
		}else{
			/*Si el q crea la noti ES un organizador, creo una nueva notificacion igual a la primera */
			Notificacion notiNueva2 = notificacionNueva;
			/*a una le seteo el instructor a enviar*/
			notificacionNueva.setInstructor(agenda.getInstructorVehiculoEspecialidad().getInstructor());
			/*y a la otra el alumno y la agrego a la lista*/
			notiNueva2.setAlumno(agenda.getInscripcion().getAlumno());
			notificacionesGuardar.add(notiNueva2);
			/*Recorro la misma lista q antes, pero si el organizador es el mismo q envia la noti NO lo añado*/
			for(Organizador lo:listaOrg){
				if(!(lo.equals(usuario.getOrganizador()))){
					Notificacion notiOrg = notificacionNueva;
					notiOrg.setOrganizador(lo);
					notificacionesGuardar.add(notiOrg);
				}	
			}
		}
		/*añado la notificacion a la lista*/
		notificacionesGuardar.add(notificacionNueva);
		
		Long id = null;
		/*recorro la lista de notificaciones y las guardo en la bd*/
		for(Notificacion noti:notificacionesGuardar){
			id = notificacionDao.crearNotificacion(noti);
		}
		/*retorno el ultimo id guardado, como una verificacion de q se guardo correctamente*/
		return id;
	}

	@Override
	public void modificarNotificacion(Notificacion notificacion) {
		notificacionDao.modificarNotificacion(notificacion);
		
	}

	@Override
	public List<Notificacion> traerNotificacionesLeidas(Usuario usuario) {
		return notificacionDao.traerNotificacionesLeidas(usuario);
	}

	@Override
	public Notificacion traerNotificacionPorId(Long id) {
		return notificacionDao.traerNotificacionPorId(id);
	}

}

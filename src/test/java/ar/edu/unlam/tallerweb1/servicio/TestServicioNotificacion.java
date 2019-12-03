package ar.edu.unlam.tallerweb1.servicio;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.dao.NotificacionDao;
import ar.edu.unlam.tallerweb1.dao.OrganizadorDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Organizador;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacionImpl;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;




public class TestServicioNotificacion extends SpringTest {
	@SuppressWarnings("unchecked")
	@Test
	public void testCrearNotificaciones(){
		ServicioNotificacion servNotificacionTest = new ServicioNotificacionImpl();
		
		NotificacionDao notificacionDaoTest = mock(NotificacionDao.class);
		
		OrganizadorDao organizadorDaoTest = mock(OrganizadorDao.class);
		
		when(notificacionDaoTest.crearNotificacion(Matchers.any(Notificacion.class))).thenReturn((long)1);
		
		Usuario userTest = mock(Usuario.class);
		
		when(userTest.getRol()).thenReturn("Alumno");
		
		Alumno aTest = mock(Alumno.class);
		
		when(userTest.getAlumno()).thenReturn(aTest);
		
		when(aTest.getUsuario()).thenReturn(userTest);
		
		Usuario user2 = mock(Usuario.class);
		
		Instructor inst = mock(Instructor.class);
		
		when(inst.getUsuario()).thenReturn(user2);
		
		when(inst.getUsuario().getNombre()).thenReturn("Nombre");
		
		when(inst.getUsuario().getNombreDeUsuario()).thenReturn("NombreUSER");
		
		when(inst.getUsuario().getApellido()).thenReturn("Apellido");
		
		Inscripcion insc = mock(Inscripcion.class);
		
		when(insc.getAlumno()).thenReturn(aTest);
		
		Organizador org = mock(Organizador.class); 
		
		Organizador org2 = mock(Organizador.class); 
		
		Organizador org3 = mock(Organizador.class); 
		
		List<Organizador> lo = new ArrayList<Organizador>();
		
		lo.add(org);lo.add(org2);lo.add(org3); //Crear notificacion x1, x2, x3
		
		when(organizadorDaoTest.traerTodosLosOrganizadores()).thenReturn(lo);
		
		Agenda agendaTest = mock(Agenda.class);
		
		EstadoDeAgenda eda = mock(EstadoDeAgenda.class);
		
		InstructorVehiculoEspecialidad iveTest = mock(InstructorVehiculoEspecialidad.class);
		
		when(agendaTest.getInstructorVehiculoEspecialidad()).thenReturn(iveTest);
		
		when(agendaTest.getEstadoDeAgenda()).thenReturn(eda);
		
		when(agendaTest.getEstadoDeAgenda().getEstado()).thenReturn("Cancelada por alumno");
		
		when(agendaTest.getFecha()).thenReturn("31-12-2019");
		
		when(agendaTest.getHora()).thenReturn(1500);
		
		when(agendaTest.getInstructorVehiculoEspecialidad().getInstructor()).thenReturn(inst); //Crear Notificacion x4
		
		when(agendaTest.getInscripcion()).thenReturn(insc);
		
		servNotificacionTest.setNotificacionDao(notificacionDaoTest);
		
		servNotificacionTest.setOrganizadorDao(organizadorDaoTest);
		
		servNotificacionTest.crearNotificacion(userTest, agendaTest);
		
		/*Tiene que crear 4 notificaciones, porque son 3 Organizadores y el instructor*/
		
		Mockito.verify(notificacionDaoTest, Mockito.times(4)).crearNotificacion(Matchers.any(Notificacion.class));
	}

	
	
}
		

package ar.edu.unlam.tallerweb1.persistencia;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorAlumno;
import ar.edu.unlam.tallerweb1.controladores.ControladorInstructor;
import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.servicios.ServicioAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoDeAgenda;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
public class testInstructor<Agenda> extends SpringTest {
	
	

	// Clase que prueba la conexion a la base de datos. Hereda de SpringTest por lo que corre dentro del contexto
	// de spring
	
		
		/************************************* INSTRUCTOR **************************************/
		@Test
	    @Transactional @Rollback(true)
	    public void pruebaConexion(){
	        assertThat(getSession().isConnected()).isTrue();
	    }
	    
	    @Test
	    @Transactional @Rollback(true)
	    public void pruebaGuardarInstructor(){
	    	Instructor i = new Instructor();
	    	getSession().save(i);
	    	Instructor i2 = getSession().get(Instructor.class, i.getId());
	    	assertThat(i.getId()).isEqualTo(i2.getId());
	    }
	    
	    @Test
	    @Transactional @Rollback(true)
	    public void pruebaEliminarInstructor(){
	    	Instructor i = new Instructor();
	    	i.setId((long) 1);
	    	getSession().save(i);
	    	getSession().delete(i);
	    	Instructor i2 = getSession().get(Instructor.class, i.getId());
	    	assertThat(i2).isNull();
	    }
	    
		@Test
		public void testInicioVistaInstructor(){
			//mokeo el controlador
			ControladorUsuario controladorUsuariorMock = new ControladorUsuario();
			//trucheo el request
			HttpServletRequest requestMock = mock(HttpServletRequest.class);
			HttpSession sessionHttpMock= mock(HttpSession.class);
			//los when
			when(requestMock.getSession()).thenReturn(sessionHttpMock);
			when(requestMock.getSession().getAttribute("ROL")).thenReturn("Instructor");
			//modelandview
			ModelAndView mav  = controladorUsuariorMock.index(requestMock);
			//pruebo el metodo dle controlador
			assertThat(mav.getViewName()).isEqualTo("indexInstructor");
	}
		
		
		@Test
		public void testQueVerificaQueMeLleveALaVistaAlumnosInstructor() {
			//mokeo el controlador
	    	ControladorInstructor controladorInstructor = new ControladorInstructor();
	    	//trucheo el request
	    	HttpServletRequest requestMock = mock(HttpServletRequest.class);
			HttpSession sessionHttpMock= mock(HttpSession.class);
			//los when
			when(requestMock.getSession()).thenReturn(sessionHttpMock);
			when(requestMock.getSession().getAttribute("ROL")).thenReturn("Instructor");
			//ModelAndView
	    	ModelAndView vista = controladorInstructor.BuscarTodosLosAlumnosDeUnInstructor(requestMock);
	    	//pruebo el metodo del controllador	
	    	assertThat(vista.getViewName()).isEqualTo("alumnosInstructor");

		}
		
		@Test
		public void testBuscadorDeAlumnos() {
			//mokeo el controlador
	    	ControladorInstructor controladorInstructor = new ControladorInstructor();
	    	//mokeo los servicios necesarios
	    	ServicioAgenda servicioAgendaMock = mock (ServicioAgenda.class);
	    	//setarlos al controllador, para ellos agrego getters y setters en el controlador instructor
	    	controladorInstructor.setServicioAgenda(servicioAgendaMock);
	    	//los when
	    	List<ar.edu.unlam.tallerweb1.modelo.Agenda> buscarAlumnos= new ArrayList<>();
	    	when(servicioAgendaMock.buscarAlumnos(1l, "", "")).thenReturn(buscarAlumnos);
	    	List<ar.edu.unlam.tallerweb1.modelo.Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor = new ArrayList<>();
	    	when(servicioAgendaMock.buscarDiaYHorarioDeTurnoDeUnInstructor(1L)).thenReturn(buscarDiaYHorarioDeTurnoDeUnInstructor);
	    	List<ar.edu.unlam.tallerweb1.modelo.Agenda> traerFechasDisponibles= new ArrayList<>();
	    	when(servicioAgendaMock.traerFechasDisponibles(1L)).thenReturn(traerFechasDisponibles);
	    	//trucheo el request
	        HttpServletRequest requestMock=mock(HttpServletRequest.class);
	        HttpSession sessionMock=mock(HttpSession.class);
	        when(requestMock.getSession()).thenReturn(sessionMock);
	        when(requestMock.getSession().getAttribute("ROL")).thenReturn("Instructor");
	        //llamo al metodo del controlador
	        controladorInstructor.buscarAlumnos("", "", requestMock);
	        //ModelAndView
	        ModelAndView modelAndView = controladorInstructor.buscarAlumnos("", "", requestMock);
	        ModelMap modelo=controladorInstructor.buscarAlumnos("", "", requestMock).getModelMap();
	        //pruebo el metodo del controllador
	        assertThat(modelo.get("buscarAlumnos")).isEqualTo(buscarAlumnos);
	        assertThat(modelo.get("listaAgenda")).isEqualTo(buscarDiaYHorarioDeTurnoDeUnInstructor);
	        assertThat(modelo.get("traerAlumnos")).isEqualTo(traerFechasDisponibles);
		}
		

		@Test
		public void testQueVerificaQueVayaALaVistaClaseCanceladaConExitoInstructor(){
			//mokeo el controlador
	    	ControladorInstructor controladorInstructor = new ControladorInstructor();
	    	//trucheo el request
	        HttpServletRequest requestMock=mock(HttpServletRequest.class);
	        HttpSession sessionMock=mock(HttpSession.class);
	        when(requestMock.getSession()).thenReturn(sessionMock);
	        when(requestMock.getSession().getAttribute("ROL")).thenReturn("Instructor");
	    	//ModelAndView
	    	ModelAndView vista = controladorInstructor.confirmarCancelacion(requestMock);
	    	//pruebo el metodo del controllador	
	    	assertThat(vista.getViewName()).isEqualTo("ClaseCanceladaConExitoInstructor");
		}
		
//		@Test
//		public void seleccionDeMotivoEstadoDeAgenda () {
//			//mokeo el controlador
//	    	ControladorInstructor controladorInstructor = new ControladorInstructor();
//	    	//mockeo servicio
//	    	ServicioEstadoDeAgenda servicioEstadoDeAgendaMock = mock(ServicioEstadoDeAgenda.class);
//	    	//lo seteo al controlador
//	    	controladorInstructor.setServicioEstadoDeAgenda(servicioEstadoDeAgendaMock);
//	    	//los when
//	    	List<EstadoDeAgenda> estadosDeAgenda = new ArrayList<>();
//	    	when(servicioEstadoDeAgendaMock.traerListaDeEstadoDeAgenda()).thenReturn(estadosDeAgenda);
//	    	//trucheo el request
//	        HttpServletRequest requestMock=mock(HttpServletRequest.class);
//	        HttpSession sessionMock=mock(HttpSession.class);
//	        when(requestMock.getSession()).thenReturn(sessionMock);
//	        when(requestMock.getSession().getAttribute("ROL")).thenReturn("Instructor");
//	        //llamo al metodo del controlador
//	        controladorInstructor.cancelarClase(1L, requestMock);
//	    	//ModelAndView
//	        ModelAndView modelAndView = controladorInstructor.cancelarClase(1L, requestMock);
//	        ModelMap modelo=(ModelMap) controladorInstructor.cancelarClase(1l, requestMock).getModel();
//	    	//pruebo el metodo del controllador
//	        assertThat(modelo.get("estadosDeAgenda")).isEqualTo(estadosDeAgenda);
//			
//		}
		
		
		@Test
		public void horasTrabajadas () {
			//mokeo el controlador
	    	ControladorInstructor controladorInstructor = new ControladorInstructor();
	    	//mockeo servicio
	    	ServicioAgenda servicioAgendaMock = mock(ServicioAgenda.class);
	    	//lo seteo al controlador
	    	controladorInstructor.setServicioAgenda(servicioAgendaMock);
	    	//los when
	    	Map<String,Integer> listaMeses = new HashMap <String,Integer>();
	    	when(servicioAgendaMock.horasTrabajadas(1L)).thenReturn(listaMeses);
	    	//trucheo el request
	        HttpServletRequest requestMock=mock(HttpServletRequest.class);
	        HttpSession sessionMock=mock(HttpSession.class);
	        when(requestMock.getSession()).thenReturn(sessionMock);
	        when(requestMock.getSession().getAttribute("ROL")).thenReturn("Instructor");
	        //llamo al metodo del controlador
	        controladorInstructor.horasTrabajadas(requestMock);
	    	//ModelAndView
	        ModelAndView modelAndView = controladorInstructor.horasTrabajadas(requestMock);
	        ModelMap modelo=(ModelMap) controladorInstructor.horasTrabajadas(requestMock).getModel();
	    	//pruebo el metodo del controllador
	        assertThat(modelo.get("listaMeses")).isEqualTo(listaMeses);
			
		
		

	    /*************************************************************************************/
	}
		
}



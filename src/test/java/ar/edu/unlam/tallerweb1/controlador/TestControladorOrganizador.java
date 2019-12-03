package ar.edu.unlam.tallerweb1.controlador;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Matchers;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorOrganizador;
import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioEspecialidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoInscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

public class TestControladorOrganizador extends SpringTest {
	@Test
	public void testInicio(){
		ControladorUsuario controladorUser = new ControladorUsuario();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession sessionHttp= mock(HttpSession.class);
		when(request.getSession()).thenReturn(sessionHttp);
		when(request.getSession().getAttribute("ROL")).thenReturn("Organizador");
		Long id = new Long(0);
		when(request.getSession().getAttribute("ID")).thenReturn(id);
		ServicioUsuario su = mock(ServicioUsuario.class);
		Usuario user = mock(Usuario.class);
		when(user.getRol()).thenReturn("Organizador");
		when(su.traerUsuarioPorId(id)).thenReturn(user);
		controladorUser.setServicioUsuario(su);
		ServicioNotificacion sn = mock(ServicioNotificacion.class);
		List<Notificacion> listaN= new ArrayList<Notificacion>();
		when(sn.traerNotificacionesNoLeidas(Matchers.any(Usuario.class))).thenReturn(listaN);
		controladorUser.setServicioNotificacion(sn);
		ModelAndView mav  = controladorUser.index(request);
		assertThat(mav.getViewName()).isEqualTo("indexOrganizador");
	}
	@Test
	public void testMostrarCursos(){
		ControladorOrganizador controladorOrg = new ControladorOrganizador();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession sessionHttp= mock(HttpSession.class);
		when(request.getSession()).thenReturn(sessionHttp);
		when(request.getSession().getAttribute("ROL")).thenReturn("Organizador");
		List <Curso> listaC = mock(List.class);
		Curso c = mock(Curso.class);
		Especialidad e = mock(Especialidad.class);
		listaC.add(c);
		List <Especialidad> listaEsp = mock(List.class);
		listaEsp.add(e);
		ServicioCurso servicioCursoTest = mock(ServicioCurso.class);
		ServicioEspecialidad servicioEspTest = mock(ServicioEspecialidad.class);
		when(servicioCursoTest.traerListaDeCursos()).thenReturn(listaC);
		when(servicioEspTest.traerListaDeEspecialidades()).thenReturn(listaEsp);
		controladorOrg.setServicioCurso(servicioCursoTest);
		controladorOrg.setServicioEspecialidad(servicioEspTest);
		ModelAndView mav = controladorOrg.mostrarCursos(request, "");
		assertThat(mav.getViewName()).isEqualTo("cursosOrg");

	}
	@Test
	public void testControlDeAcceso(){
		ControladorOrganizador controladorOrg = new ControladorOrganizador();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession sessionHttp= mock(HttpSession.class);
		when(request.getSession()).thenReturn(sessionHttp);
		when(request.getSession().getAttribute("ROL")).thenReturn("Usuario");
		ModelAndView mav = controladorOrg.agregarInstructor2(null, null, request);
		assertThat(mav.getViewName()).isEqualTo("redirect:/index");

	}
	@SuppressWarnings("unchecked")
	@Test
	public void testEliminacionDeCursoCorrecta(){
		ControladorOrganizador controladorOrg = new ControladorOrganizador();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession sessionHttp= mock(HttpSession.class);
		when(request.getSession()).thenReturn(sessionHttp);
		when(request.getSession().getAttribute("ROL")).thenReturn("Organizador");
		Long id = (long)2;
		List <Inscripcion> listaIns = mock(List.class);
		Curso cursoTest = mock(Curso.class);
		ServicioCurso servicioCursoTest = mock(ServicioCurso.class);
		when(servicioCursoTest.buscarCursoPorId(id)).thenReturn(cursoTest);
		when(servicioCursoTest.buscarCurso(cursoTest)).thenReturn(null);
		ServicioEstadoInscripcion servicioEstadoInscripcionTest = mock(ServicioEstadoInscripcion.class);
		when(servicioEstadoInscripcionTest.verificarQueElCursoNoTengaInscripcionesEnCurso(listaIns)).thenReturn(true);
		controladorOrg.setServicioCurso(servicioCursoTest);
		controladorOrg.setServicioEstadoInscripcion(servicioEstadoInscripcionTest);
		ModelAndView mav = controladorOrg.EliminarCurso(request, id, "si");
		assertThat(mav.getViewName()).isEqualTo("cursosOrg");	
		}
	
	@Test
	public void testCursoNoExistente(){
		ControladorOrganizador controladorOrg = new ControladorOrganizador();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession sessionHttp= mock(HttpSession.class);
		when(request.getSession()).thenReturn(sessionHttp);
		when(request.getSession().getAttribute("ROL")).thenReturn("Organizador");
		Long id = (long)2;
		ServicioCurso servicioCursoTest = mock(ServicioCurso.class);
		when(servicioCursoTest.buscarCursoPorId(id)).thenReturn(null);
		controladorOrg.setServicioCurso(servicioCursoTest);
		ModelAndView mav = controladorOrg.ModificarCursos(request, id);
		assertThat(mav.getModelMap().containsKey("error")).isTrue();
		
	}
}

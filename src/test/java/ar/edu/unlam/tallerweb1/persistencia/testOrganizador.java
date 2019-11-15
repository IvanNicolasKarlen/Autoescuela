package ar.edu.unlam.tallerweb1.persistencia;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;


import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorOrganizador;
import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;
import ar.edu.unlam.tallerweb1.modelo.*;
import ar.edu.unlam.tallerweb1.servicios.ServicioCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioEspecialidad;

public class testOrganizador extends SpringTest{

		@Test
	public void testInicio(){
		ControladorUsuario controladorUser = new ControladorUsuario();
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpSession sessionHttp= mock(HttpSession.class);
		when(request.getSession()).thenReturn(sessionHttp);
		when(request.getSession().getAttribute("ROL")).thenReturn("Organizador");
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
	
}

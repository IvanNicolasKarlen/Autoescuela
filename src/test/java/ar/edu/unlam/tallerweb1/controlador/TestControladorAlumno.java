package ar.edu.unlam.tallerweb1.controlador;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.ViewModel.CursosViewModel;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorAlumno;
import ar.edu.unlam.tallerweb1.controladores.ControladorInstructor;
import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;
import ar.edu.unlam.tallerweb1.dao.AgendaDao;
import ar.edu.unlam.tallerweb1.dao.AlumnoDao;
import ar.edu.unlam.tallerweb1.dao.CursoDao;
import ar.edu.unlam.tallerweb1.dao.CursoDaoImpl;
import ar.edu.unlam.tallerweb1.dao.EspecialidadDao;
import ar.edu.unlam.tallerweb1.dao.EstadoDeAgendaDao;
import ar.edu.unlam.tallerweb1.dao.EstadoInscripcionDao;
import ar.edu.unlam.tallerweb1.dao.InscripcionDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioAgendaImp;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioCursoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoInscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioInscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioInscripcionImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Instructor;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


//Se indica que los test que hereden de esta clase corran con el runner de junit para spring.
@RunWith(SpringJUnit4ClassRunner.class)
//Se indica
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
//Clase base para los test que se pretende que se corran dentro del contexto de spring




//@RequestMapping(path="/listadoFechas") 249 Hecho
// 	@RequestMapping(path="/clasesDelCurso")291 Hecho
// 	@RequestMapping(path="/finalizarCursoAlerta")407
// 	@RequestMapping(path="/historial")542
//	@RequestMapping(path="/mostrarclasesCurso")583
// @RequestMapping(path="/seleccionarClaseAgregar") 882



public class TestControladorAlumno extends SpringTest {
	
	
	
	
	
	@Test
    public void testQueVerificaQueSiSoyUnAlumno()
    {
    	//Quiero probar el login
    	ControladorAlumno controlador = new ControladorAlumno();
    	HttpServletRequest requestMock = mock(HttpServletRequest.class);
    	HttpSession sessionHttp= mock(HttpSession.class);
		
    	when(requestMock.getSession()).thenReturn(sessionHttp);
    	when(requestMock.getSession().getAttribute("ROL")).thenReturn("Alumno");
    	
    	ServicioAlumno serviAlumnoMock = mock(ServicioAlumno.class);
    	
    	controlador.setServicioAlumno(serviAlumnoMock);
    	
    	//Seteo al controlador el mock falso para saber que vista me devuelve
    	ModelAndView vista = controlador.indexAlumno(requestMock);
    	assertThat(vista.getViewName()).isEqualTo("indexAlumno");
    }
	
	
	
		@Test
	    public void testQueVerificaQueNoSoyUnAlumno()
	    {
	    	//Quiero probar el login
	    	ControladorAlumno controlador = new ControladorAlumno();
	    	HttpServletRequest requestMock = mock(HttpServletRequest.class);
	    	HttpSession sessionHttp= mock(HttpSession.class);
			
	    	//Devuelvo una sesion con el rol Organizador
	    	when(requestMock.getSession()).thenReturn(sessionHttp);
	    	when(requestMock.getSession().getAttribute("ROL")).thenReturn("Organizador");
	    
	    	//Seteo al controlador el mock falso para saber que vista me devuelve
	    	ModelAndView vista = controlador.indexAlumno(requestMock);
	    	assertThat(vista.getViewName()).isEqualTo("redirect:/index");
	    }
	
	
	
		@Test
		@Transactional
	    public void testQueMuestraMisClases()
	    {
			//Test que verifica si devuelve las clases que se anotó el alumno
			
			//trucheo el request
	        HttpServletRequest requestMock=mock(HttpServletRequest.class);
	        HttpSession sessionMock=mock(HttpSession.class);
	        
	        
	        when(requestMock.getSession()).thenReturn(sessionMock);
	        when(requestMock.getSession().getAttribute("ROL")).thenReturn("Alumno");
	        
	        
			ControladorAlumno controladorAlumno = new ControladorAlumno();

			//Creo mocks
			
			ServicioEstadoInscripcion servicioEstadoInscripcionMock = mock(ServicioEstadoInscripcion.class);
			
			ServicioAgenda servicioAgendaMock = mock(ServicioAgenda.class); 
			
			ServicioInscripcion servicioInscripcionMock = mock(ServicioInscripcion.class);
			
			controladorAlumno.setServicioEstadoInscripcion(servicioEstadoInscripcionMock);
			controladorAlumno.setServicioInscripcion(servicioInscripcionMock);
			controladorAlumno.setServicioAgenda(servicioAgendaMock);
			
			
			//Alumno por parametros
			Alumno alumno = new Alumno();
			
			//EstadoInscripcion
			EstadoInscripcion estadoCursando = new EstadoInscripcion();
			
			when(servicioEstadoInscripcionMock.buscarEstadoCursando()).thenReturn(estadoCursando);
			
			//Cursando
			List<Inscripcion> cursando = mock(List.class);
					
			when(servicioInscripcionMock.saberSiEstaRealizandoAlgunCurso(alumno.getId(), estadoCursando)).thenReturn(cursando);
			
			//Listado de clases
			List<Agenda> listadoDeClases = mock(List.class);
					
			when(servicioAgendaMock.traerTodasLasClasesQueEstaAnotado(alumno.getId(), estadoCursando)).thenReturn(listadoDeClases);
				
			//pruebo el metodo del controlador
			 ModelAndView vista = controladorAlumno.DiasDeCursada(requestMock);
		     ModelMap modelo=(ModelMap) controladorAlumno.DiasDeCursada(requestMock).getModel();
		    	
		        //pruebo el metodo del controlador
		        assertThat(modelo.get("listadoClases")).isEqualTo(listadoDeClases);
		        assertThat(modelo.get("num")).isEqualTo(cursando.size());
		        assertThat(modelo.get("listaCursos")).isEqualTo(cursando);
		        assertThat(vista.getViewName()).isEqualTo("fechaYHorasDeCadaCurso");

	    }
		
		
		
		
	@Test
	@Transactional
	public void testQueValidaSiEstoyInscriptoAUnCurso()
	{
		//Verificamos que el alumno este inscripto a un curso. Si no tiene curso, se le pedira que se registre a alguno
			
		//trucheo el request
	       HttpServletRequest requestMock=mock(HttpServletRequest.class);
	       HttpSession sessionMock=mock(HttpSession.class);
	        
	        
	       when(requestMock.getSession()).thenReturn(sessionMock);
	       when(requestMock.getSession().getAttribute("ROL")).thenReturn("Alumno");
	        
	        
		ControladorAlumno controladorAlumno = new ControladorAlumno();
		
		//Mockeo servicios	
		ServicioEstadoInscripcion servicioEstadoInscripcionMock = mock(ServicioEstadoInscripcion.class);
			
		ServicioAgenda servicioAgendaMock = mock(ServicioAgenda.class); 
		
		ServicioInscripcion servicioInscripcionMock = mock(ServicioInscripcion.class);
		
		//Seteo los servicios mockeados al controlador
		controladorAlumno.setServicioEstadoInscripcion(servicioEstadoInscripcionMock);
		controladorAlumno.setServicioInscripcion(servicioInscripcionMock);
		controladorAlumno.setServicioAgenda(servicioAgendaMock);
			
			
		//Alumno por parametros
		Alumno alumno = new Alumno();
			
		EstadoInscripcion estadoCursando = new EstadoInscripcion();
			
		when(servicioEstadoInscripcionMock.buscarEstadoCursando()).thenReturn(estadoCursando);
			
			
		//Traer las clases del filtro elegido Agenda
		List<Agenda> clasesDeUnSoloCurso = mock(List.class);
					
		Especialidad especialidad = new Especialidad();
					
		when(servicioAgendaMock.traerTodasLasClasesDeUnaSolaEspecialidad(especialidad.getId(),alumno.getId(),estadoCursando))
		.thenReturn(clasesDeUnSoloCurso);
			
		List<Inscripcion> listadoDeFiltros = mock(List.class);
		
		Long idEspecialidad = (long) 1;
		
		//pruebo el metodo del controlador
		 ModelAndView vista = controladorAlumno.ClasesElegidasEnElFiltro(requestMock,idEspecialidad);
	     ModelMap modelo=(ModelMap) controladorAlumno.ClasesElegidasEnElFiltro(requestMock, idEspecialidad).getModel();
	    
		
		 //pruebo el metodo del controllador
        assertThat(modelo.get("num")).isEqualTo(listadoDeFiltros.size());       
        assertThat(vista.getViewName()).isEqualTo("clasesElegidasEnElFiltroDeAlumno");

			
		}
		
		
	
	@Test
    public void testQueConsultaSiQueresFinalizarElCurso()
    {
		//Verificamos que este anotado al curso que quiere finalizar para poder realizar dicha operacion.
		
		//trucheo el request
	     HttpServletRequest requestMock=mock(HttpServletRequest.class);
	     HttpSession sessionMock=mock(HttpSession.class);
	     
	    AgendasViewModel agendaViewModelMock = mock(AgendasViewModel.class);
	        
	    when(requestMock.getSession()).thenReturn(sessionMock);
	    when(requestMock.getSession().getAttribute("ROL")).thenReturn("Alumno");
	    
	    ControladorAlumno controladorAlumno = new ControladorAlumno();
			
		ServicioInscripcion servicioInscripcionMock = mock(ServicioInscripcion.class);
			
		controladorAlumno.setServicioInscripcion(servicioInscripcionMock);
				
		//Alumno por parametros
		Alumno alumno = new Alumno();
		
		Curso curso = new Curso();
		
		Inscripcion inscripcionBuscada = mock(Inscripcion.class);
		
		when(servicioInscripcionMock.buscarInscripcion(alumno.getId(), curso.getId())).thenReturn(inscripcionBuscada);
		
		
		//pruebo el metodo del controlador
		 ModelAndView vista = controladorAlumno.consultarSiQuiereFinalizarONo(requestMock,agendaViewModelMock);
	     ModelMap modelo=(ModelMap) controladorAlumno.consultarSiQuiereFinalizarONo(requestMock, agendaViewModelMock).getModel();
	    
		
			
		 //pruebo el metodo del controllador
       assertThat(modelo.get("mensaje")).isEqualTo("¿Estas seguro?");       
       assertThat(vista.getViewName()).isEqualTo("alertaEliminar");

    }
	
	
	
	@Test
    public void testQueNoPermiteEliminarUnaClase()
    {
		//Contemplamos que si faltan dos dias para realizar la clase, esta ya no pueda ser eliminada
		
		//trucheo el request
	     HttpServletRequest requestMock=mock(HttpServletRequest.class);
	     HttpSession sessionMock=mock(HttpSession.class);
	         
	     AgendasViewModel agendaViewModelMock = mock(AgendasViewModel.class);
		    
	     
	    when(requestMock.getSession()).thenReturn(sessionMock);
	    when(requestMock.getSession().getAttribute("ROL")).thenReturn("Alumno");
	    
	    
	    ControladorAlumno controladorAlumno = new ControladorAlumno();
		
	    
	    ServicioAgenda servicioAgendaMock = mock(ServicioAgenda.class); 
		
	    controladorAlumno.setServicioAgenda(servicioAgendaMock);
	    
		
	  //Alumno por parametros
	  		Alumno alumno = new Alumno();
	  		
	  		when(agendaViewModelMock.getIdAgendaSeleccionada()).thenReturn(null);
			
	  		Boolean resultado = false;
	  				
	  				
	 when(servicioAgendaMock.verificarQueSePuedanEliminarTodasLasClases(alumno.getId(), agendaViewModelMock.getIdCurso())).thenReturn(resultado);
			
	//pruebo el metodo del controlador
	 ModelAndView vista = controladorAlumno.eliminarUnaClase(requestMock,agendaViewModelMock);
     ModelMap modelo=(ModelMap) controladorAlumno.eliminarUnaClase(requestMock, agendaViewModelMock).getModel();
    
	
		
	 //pruebo el metodo del controllador
   assertThat(modelo.get("mensaje")).isEqualTo("No se pueden eliminar las clases con menos de dos dias de anticipacion");       
   assertThat(vista.getViewName()).isEqualTo("Eliminada");

	 	
		
    }
	
	
	
	
	

	
	@Test
    public void testQueOfreceCronogramaDeClases()
    {
    
		//Test que verifica que se muestre el cronograma de clases
		
		ControladorAlumno controladorAlumno = new ControladorAlumno();
    	HttpServletRequest requestMock = mock(HttpServletRequest.class);
    	HttpSession sessionHttp= mock(HttpSession.class);
		
    	when(requestMock.getSession()).thenReturn(sessionHttp);
    	when(requestMock.getSession().getAttribute("ROL")).thenReturn("Alumno");
    	
    	ServicioCurso servicioCursoMock = mock(ServicioCurso.class);
    	ServicioInscripcion servicioInscripcionMock = mock(ServicioInscripcion.class);
    	ServicioAgenda servicioAgendaMock = mock(ServicioAgenda.class); 
		
    	controladorAlumno.setServicioAgenda(servicioAgendaMock);
    	controladorAlumno.setServicioCurso(servicioCursoMock);
    	controladorAlumno.setServicioInscripcion(servicioInscripcionMock);
  	   
    	
    	 //Alumno por parametros
  		Alumno alumno = new Alumno();
 
  		Curso curso = new Curso();
  		
  		//Datos del curso Elegido
  			Curso cursoMock = mock(Curso.class);
  			
  			when(cursoMock.getId()).thenReturn((long) 1);
  	  		
  			when(servicioCursoMock.buscarCursoPorId(cursoMock.getId())).thenReturn(curso);
  		
  			List <Inscripcion> inscripcionCurso = new ArrayList<>();
  			
  			when(servicioInscripcionMock.consultarSiYaSeInscribioAUnCurso(alumno.getId(),curso)).thenReturn(inscripcionCurso);
  			
  			
  			
  		//Traer todas las fechas con disponibilidad
			TreeSet<Agenda> agendas= new TreeSet<>();
					
					
			when(servicioAgendaMock.traerAgendasConFechasNoRepetidas(curso, alumno.getId())).thenReturn(agendas);

			 //pruebo el metodo del controllador
			 ModelAndView vista = controladorAlumno.guardarCursoSeleccionado(curso,requestMock);
		     ModelMap modelo=(ModelMap) controladorAlumno.guardarCursoSeleccionado(curso,requestMock).getModel();
			    
			assertThat(modelo.get("mensaje")).isEqualTo("Te ofrecemos este cronograma de clases");     
			
			assertThat(vista.getViewName()).isEqualTo("fechasAlumnoEnAgenda");

		
    }

    
}  
package ar.edu.unlam.tallerweb1.persistencia;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorAlumno;
import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;
import ar.edu.unlam.tallerweb1.dao.CursoDaoImpl;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioCursoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Instructor;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;


//Se indica que los test que hereden de esta clase corran con el runner de junit para spring.
@RunWith(SpringJUnit4ClassRunner.class)
//Se indica
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
//Clase base para los test que se pretende que se corran dentro del contexto de spring


public class testAlumno extends SpringTest {
	@Test
    public void testQueVerificaManejoDeVistaEnBaseALaSesion()
    {
    	
		
		
    	//Quiero probar el login
    	ControladorAlumno controlador = new ControladorAlumno();
    	Alumno alumnoMock = mock(Alumno.class);
    	HttpServletRequest requestMock = mock(HttpServletRequest.class);
    	ServicioAlumno servicioMock = mock(ServicioAlumno.class);
    	
    	
    	
    	when(requestMock.getAttribute("ROL")).thenReturn("Instructor");
    	
    	
    	//Seteo al controlador el mock falso para saber que vista me devuelve
    	ModelAndView vista = controlador.mostrarCursos(requestMock);
    	
    	
    	assertThat(vista.getViewName()).isEqualTo("redirect:/index");
    }
    
    
    //Pruebo que en el metodo del controlador mostrarCursos ln 107 me devuelva bien la vista
    @Test
    public void testQueVerificaSiTraeElListadoDeTodosLosCursos()
    {
    	
    	ServicioCurso servicioMock = mock(ServicioCurso.class);
    	HttpServletRequest requestMock = mock(HttpServletRequest.class);    	
    	ControladorAlumno controladorCurso = new ControladorAlumno();
    	
    	
    	//Pruebo a que vista me va a llevar el ModelAndView
    	ModelAndView vista = controladorCurso.mostrarCursos(requestMock);
    	
    	    	
    	assertThat(vista.getViewName()).isEqualTo("cursos");
    	
    	
    }
    
    //TraerListaCursos ln 114
    
    //Probar desde el dao - Sin mockear el dao
    //Luego el servicio 
    //Por ultimo, el controlador
    @Test
    public void testeoElMetodoTraerListaCursos()
    {
    	
    	ServicioCurso servicioMock = mock(ServicioCurso.class);
    	HttpServletRequest requestMock = mock(HttpServletRequest.class);    	
    	ControladorAlumno controladorCurso = new ControladorAlumno();
    	Curso curso = new Curso();
    	CursoDaoImpl cursoDao = new CursoDaoImpl();
    	ServicioCursoImpl cursoImpl = new ServicioCursoImpl();
    	
    	
    	//Guardo un curso en la bd
    	curso.setPrecio(100);
    	getSession().save(curso);
    	
    	
    	//Pruebo que el servicio me devuelva los cursos
    	
    	
    	List<Curso> cursos = cursoDao.traerListaDeCursos();
    	
    	
    	assertThat(cursos.size()).isEqualTo(1);
    	
    	
    	
    	
    	List<Curso> listRet = new ArrayList<>();
    	
    	
    	//Que el servicioMock sea igual a la lista que instancie
    	when(servicioMock.traerListaDeCursos()).thenReturn(listRet);
    	
    	
    	//Defino debajo de todo en el controladorAlumno un servicioCurso para que no me devuelva un NULLPOINTER
    	//Seteo al controladorCurso el servicioMock
    	controladorCurso.setServicioCurso(servicioMock);
    	
    	
    	//Pruebo a que vista me va a llevar el ModelAndView
    	ModelAndView vista = controladorCurso.mostrarCursos(requestMock);
    	
    	List<Curso> listaModelo = (List<Curso>) vista.getModel().get("lista");
    	
    	
    //	assertThat(listaModelo.size().isEqualTo(listRet.size()));
    	
    	
    	
    }

}

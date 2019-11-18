package ar.edu.unlam.tallerweb1.persistencia;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.controladores.ControladorAlumno;
import ar.edu.unlam.tallerweb1.controladores.ControladorUsuario;
import ar.edu.unlam.tallerweb1.dao.CursoDao;
import ar.edu.unlam.tallerweb1.dao.CursoDaoImpl;
import ar.edu.unlam.tallerweb1.dao.InscripcionDao;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioCursoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioInscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioInscripcionImpl;
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
    	HttpServletRequest requestMock = mock(HttpServletRequest.class);
    	HttpSession sessionHttp= mock(HttpSession.class);
		
    	when(requestMock.getSession()).thenReturn(sessionHttp);
    	when(requestMock.getSession().getAttribute("ROL")).thenReturn("Organizador");
    	//Seteo al controlador el mock falso para saber que vista me devuelve
    	ModelAndView vista = controlador.indexAlumno(requestMock);
    	assertThat(vista.getViewName()).isEqualTo("redirect:/index");
    }
	

	@Test
	public void testQueGuardaCursosParaTraerListadoDeTodosLosCursos()
	{
		CursoDao cursoDaoMock = mock(CursoDao.class);
		
		//Mock curso y seteo un dato
		Curso cursoMock = mock(Curso.class);		
				
		//Guardo en la lista mock
		List<Curso> listaMock = new ArrayList<Curso>();
		listaMock.add(cursoMock);
		    	
		when(cursoDaoMock.traerListaDeCursos()).thenReturn(listaMock);		
    	
							//Servicio
    	ServicioCursoImpl cursoImplMock = mock(ServicioCursoImpl.class);
    	cursoImplMock.setCursoDao(cursoDaoMock);
    	
    	List<Curso> listaCursos = cursoImplMock.traerListaDeCursos();
    	
    	assertThat(listaCursos).isNotNull();
		
	}
	
	 
    
 /*   @Test
    public void testQueConsultaSiYaSeInscribioElAlumnoAUnCurso()
    {
    	
    	ServicioInscripcionImpl servicioMock = mock(ServicioInscripcionImpl.class);
    	HttpServletRequest request = mock(HttpServletRequest.class);
    	Inscripcion inscripcionMock = mock(Inscripcion.class);
    	Curso cursoMock = mock(Curso.class);
    	Usuario usuarioMock = mock(Usuario.class);
    	
    	when(request.getSession().getAttribute("ID")).thenReturn(1);
    	when(cursoMock.getId()).thenReturn((long) 1);
    	
    	
    	//Lista para usar de retorno en mi metodo
    	List<Inscripcion> listMock = mock(List.class);
    	listMock.add(inscripcionMock); //agrego un obj mock
    	
    	//Traigo los datos del alumno logueado
    	 Long idAlumno = (Long) request.getSession().getAttribute("ID");
      	
    	 when(usuarioMock.getAlumno().getId()).thenReturn((long) 1);
     	
    	when(servicioMock.consultarSiYaSeInscribioAUnCurso(usuarioMock.getAlumno().getId(), cursoMock)).thenReturn(listMock);
   
    	//Agrego el dao en el servicio Inscripcion
    	InscripcionDao inscripcionDaoMock = mock(InscripcionDao.class);
    	servicioMock.setInscripcionDao(inscripcionDaoMock);
    	
    	
    	List<Inscripcion> inscripcion = servicioMock.consultarSiYaSeInscribioAUnCurso(idAlumno, inscripcionMock.getCurso());

    	
    	assertThat(inscripcion).isNotNull();
    }
    
    
    
    
    
    
    
    
    
    
    */
}  
    //TraerListaCursos ln 114
    
    //Probar desde el dao - Sin mockear el dao
    //Luego el servicio 
    //Por ultimo, el controlador
//    @Test
//    public void testeoElMetodoTraerListaCursos()
//    {
//    	
//    	ServicioCurso servicioMock = mock(ServicioCurso.class);
//    	HttpServletRequest requestMock = mock(HttpServletRequest.class);    	
//    	ControladorAlumno controladorCurso = new ControladorAlumno();
//    	Curso curso = new Curso();
//    	CursoDaoImpl cursoDao = new CursoDaoImpl();
//    	ServicioCursoImpl cursoImpl = new ServicioCursoImpl();
//    	
//    	
//    	//Guardo un curso en la bd
//    	curso.setPrecio(100);
//    	getSession().save(curso);
//    	
//    	
//    	//Pruebo que el servicio me devuelva los cursos
//    	
//    	
//    	List<Curso> cursos = cursoDao.traerListaDeCursos();
//    	
//    	
//    	assertThat(cursos.size()).isEqualTo(1);
//    	
//    	
//    	
//    	
//    	List<Curso> listRet = new ArrayList<>();
//    	
//    	
//    	//Que el servicioMock sea igual a la lista que instancie
//    	when(servicioMock.traerListaDeCursos()).thenReturn(listRet);
//    	
//    	
//    	//Defino debajo de todo en el controladorAlumno un servicioCurso para que no me devuelva un NULLPOINTER
//    	//Seteo al controladorCurso el servicioMock
//    	controladorCurso.setServicioCurso(servicioMock);
//    	
//    	
//    	//Pruebo a que vista me va a llevar el ModelAndView
//    	ModelAndView vista = controladorCurso.mostrarCursos(requestMock);
//    	
//    	List<Curso> listaModelo = (List<Curso>) vista.getModel().get("lista");
//    	
//    	
//    //	assertThat(listaModelo.size().isEqualTo(listRet.size()));
//    	
//    	
//    	
//    }
//
//}

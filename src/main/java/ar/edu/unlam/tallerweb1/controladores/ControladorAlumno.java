package ar.edu.unlam.tallerweb1.controladores;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.ViewModel.TurnosViewModel;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoMetodoFinalizoCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoMetodoGuardaCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoMetodoQueBuscaCursos;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoMetodoQueGuardaAlumnos;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoMetodoQueGuardaFechas;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoMetodoSiYaSeInscribioOnO;


@Controller
public class ControladorAlumno {

	// La anotacion @Inject indica a Spring que en este atributo se debe setear (inyeccion de dependencias)
	// un objeto de una clase que implemente la interface servicioUsuario, dicha clase debe estar anotada como
	// @Service o @Repository y debe estar en un paquete de los indicados en applicationContext.xml
	@Inject
	private ServicioAlumnoMetodoQueGuardaFechas servicioAlumnoGuardaFechas;
	@Inject
	private ServicioAlumnoMetodoQueGuardaAlumnos servicioAlumnoGuardaAlumnos;
	@Inject
	private ServicioAlumnoMetodoQueBuscaCursos servicioAlumnoBuscaCursos;
	@Inject
	private ServicioAlumnoMetodoSiYaSeInscribioOnO servicioAlumnoSiYaSeInscribioOnO;
	@Inject
	private ServicioAlumnoMetodoFinalizoCurso servicioAlumnoFinalizoCurso;
	@Inject
	private ServicioAlumnoMetodoGuardaCurso servicioAlumnoGuardaCurso;
	
	
	
	Alumno alumno = new Alumno();
	Curso curso = new Curso();
	TablaCursoAlumno cursoAlumno = new TablaCursoAlumno();
	Agenda agenda = new Agenda();
	
	
	@RequestMapping("/indexAlumno")
	public ModelAndView indexAlumno() {
		
		
		
		//servicioAlumnoGuardaFechas.metodoQueGuardaFechas(agenda); //************************************* NO VA (Solo de prueba)
		
		servicioAlumnoGuardaAlumnos.guardarAlumno(alumno);			//********************************** NO VA (Solo de prueba)
		
		return new ModelAndView("indexAlumno");
	}
	
	
	@RequestMapping(path="/listadoCursos")
	public ModelAndView mostrarCursos(){
		
		ModelMap modelo = new ModelMap();
		
		//Trae todo el listado de todos los cursos
		List<Curso> listaCursos =  servicioAlumnoBuscaCursos.buscarCursos();
		
		modelo.put("lista", listaCursos);

		return new ModelAndView("cursos",modelo);
	}
	
	
	
	
	@RequestMapping(path="/cursoElegido")
	public ModelAndView guardarCursoSeleccionado( @ModelAttribute("curso") Curso cursoElegido ){
		ModelMap modelo = new ModelMap();

		//Saber si el alumno ya está haciendo este curso que selecciono
		List<TablaCursoAlumno> cursando = servicioAlumnoSiYaSeInscribioOnO.consultarSiYaSeInscribioAUnCurso(alumno,cursoElegido);
		
		//Saber si finalizo o no este curso que selecciono
		TablaCursoAlumno estadoCurso = servicioAlumnoFinalizoCurso.consultarSiFinalizoSuCurso(alumno,cursoElegido);
	
			//Si lo esta cursando    Y			  aun no lo termino
		if ( ( cursando.size() > 0 ) && (estadoCurso.getEstado().equals("Cursando")) )
		{
			modelo.put("error","Debes finalizar el curso para agregar mas clases"); //Le avisa que no finalizo
			
			//Trae todo el listado de todos los cursos
			List<Curso> listaCursos =  servicioAlumnoBuscaCursos.buscarCursos();
			
			modelo.put("lista", listaCursos);
			return new ModelAndView("cursos", modelo); 

		}

		return new ModelAndView("fechas", modelo); //Todavia no curso nada

	}

	
}

package ar.edu.unlam.tallerweb1.controladores;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoEspecialidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoEstado;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoAgenda;



@Controller
public class ControladorAlumno {

	// La anotacion @Inject indica a Spring que en este atributo se debe setear (inyeccion de dependencias)
	// un objeto de una clase que implemente la interface servicioUsuario, dicha clase debe estar anotada como
	// @Service o @Repository y debe estar en un paquete de los indicados en applicationContext.xml
	
	@Inject
	private ServicioAlumnoCurso servicioAlumnoCurso;
	@Inject
	private ServicioAlumnoEstado servicioAlumnoEstado;
	@Inject
	private ServicioAlumnoEspecialidad servicioAlumnoEspecialidad;
	@Inject
	private ServicioAlumno servicioAlumno;
	@Inject
	private ServicioAlumnoAgenda servicioAlumnoAgenda;
	
	
	
	
	
	@RequestMapping("/indexAlumno")
	public ModelAndView indexAlumno() {
			
		return new ModelAndView("indexAlumno");
	}
	
	
	@RequestMapping(path="/listadoCursos")
	public ModelAndView mostrarCursos(HttpServletRequest request){
		
	if(request.getSession().getAttribute("ROL").equals("Alumno"))
	{
		ModelMap modelo = new ModelMap();
		
		//Trae todo el listado de todos los cursos
		List<Curso> listaCursos =  servicioAlumnoCurso.buscarCursos();
		
	
		modelo.put("lista", listaCursos);
		
		return new ModelAndView("cursos",modelo);
	}
		
	return new ModelAndView("redirect:/index");
	}
	
	
	
	
	@RequestMapping(path="/cursoElegido")
	public ModelAndView guardarCursoSeleccionado( @ModelAttribute("curso") Curso cursoElegido, HttpServletRequest request )
	{
		ModelMap modelo = new ModelMap();
		

		if(request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
		//Datos del curso Elegido
		Curso curso = servicioAlumnoCurso.buscarCurso(cursoElegido);
		
		//Busco el id del estado que dice "Cursando"
		EstadoDelCurso estado = servicioAlumnoEstado.buscarEstadoCursando();
		
		//Buscar la especialidad del curso elegido
		Especialidad especialidad = servicioAlumnoEspecialidad.consultarEspecialidadCursoElegido(cursoElegido);
		
		//Saber si el alumno ya está haciendo este curso que selecciono
		List <TablaCursoAlumno> cursando = servicioAlumnoCurso.consultarSiYaSeInscribioAUnCurso(idAlumno, estado,especialidad);
		
		
		//Traigo los datos del alumno logueado
				Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
						
				TablaCursoAlumno cursoAlumno = new TablaCursoAlumno();	
		
		if(cursando.isEmpty() ) //Todavia ese curso que eligio no esta anotado
			{
				//Guardar en la tablaCursoAlumno el curso y el alumno
			servicioAlumnoCurso.guardarCurso(alumno, curso, cursoAlumno, estado);
				
				
			}else{ 

					modelo.put("error","No podes agregar otro curso con la misma especialidad"); //Le avisa que no finalizo
					//Trae todo el listado de todos los cursos
					List<Curso> listaCursos =  servicioAlumnoCurso.buscarCursos();
					
					modelo.put("lista", listaCursos);
					return new ModelAndView("cursos", modelo); //Todavia no curso nada
					
			}
		
		//Traer todas las fechas con disponibilidad
		List<Agenda> agendas= servicioAlumnoAgenda.traerAgendasDisponibles();

		TreeSet<Agenda> agendasSinDuplicados = servicioAlumnoAgenda.eliminarLasAgendasConFechasDuplicadas(agendas);

		TreeSet<Agenda> agendasListas = servicioAlumnoAgenda.eliminarAgendasQueSuperanLaCantidadDeClasesDelCurso(agendasSinDuplicados,curso);

		
		if(!agendas.isEmpty())
		{
			//Guardar alumno en la Agenda
			servicioAlumnoAgenda.guardarAlumnoConSuCursoElegidoEnLaAgenda(agendasListas,alumno,curso);
				
		}else{
			modelo.put("error", "No hay mas fechas disponibles para realizar una cursada");
		}
				
		modelo.put("listaAgendas", agendasListas);
		modelo.put("curso", curso.getEspecialidad().getTipo());
		


		return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
		}
		
		return new ModelAndView("redirect:/index");

	}


	
}

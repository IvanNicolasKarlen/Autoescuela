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

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioEspecialidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoInscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioInscripcion;



@Controller
public class ControladorAlumno {

	@Inject
	private ServicioAlumno servicioAlumno;
	@Inject
	private ServicioEspecialidad servicioEspecialidad;
	@Inject
	private ServicioEstadoInscripcion servicioEstadoInscripcion;
	@Inject
	private ServicioAgenda servicioAgenda;
	@Inject
	private ServicioInscripcion servicioInscripcion;
	@Inject
	private ServicioCurso servicioCurso;
	
	
	
	

	
	
	
	
	
	@RequestMapping("/indexAlumno")
	public ModelAndView indexAlumno(HttpServletRequest request) {
		if(request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			ModelMap modelo = new ModelMap();
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
										//servicioAlumnoInscripcion
		List<Inscripcion> cursando = servicioInscripcion.saberSiEstaRealizandoAlgunCurso(idAlumno);
		
		
		if(cursando.isEmpty())
		{
			modelo.put("inscripto", null);
			modelo.put("num", 0);
			modelo.put("tam", cursando.size());
			
		}else{
				modelo.put("inscripto", cursando);
				modelo.put("num", 1);
				modelo.put("tam", cursando.size());
			 }
		
		return new ModelAndView("indexAlumno");
		}
		return new ModelAndView("redirect:/index");
	}
	
	
	@RequestMapping(path="/listadoCursos")
	public ModelAndView mostrarCursos(HttpServletRequest request){
		
	if(request.getSession().getAttribute("ROL").equals("Alumno"))
	{
		ModelMap modelo = new ModelMap();
		
		//Trae todo el listado de todos los cursos
		List<Curso> listaCursos =  servicioCurso.buscarCursos();//servicioAlumnoInscripcion
		
	
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
		Curso curso = servicioCurso.buscarCurso(cursoElegido.getId());//servicioAlumnoInscripcion

		List <Inscripcion> inscripcionCurso = servicioInscripcion.consultarSiYaSeInscribioAUnCurso(idAlumno, cursoElegido); //servicioAlumnoInscripcion
			
		if(inscripcionCurso.isEmpty() ) //Todavia ese curso que eligio no esta anotado
			{
			
			//Seleccionar curso
			modelo.put("cursoSeleccionado", curso);
			
			//Traer todas las fechas con disponibilidad
			TreeSet<Agenda> agendas=servicioAgenda.traerAgendasConFechasNoRepetidas(curso);//servicioAlumnoAgenda

			if(agendas.isEmpty())
			{
				modelo.put("error", "No hay mas fechas disponibles para realizar una cursada");	
			}else{
				modelo.put("listaAgendas", agendas);
				 }
			
			modelo.put("mensaje", "Te ofrecemos este cronograma de clases");
			modelo.put("especialidad", curso.getEspecialidad().getTipo());
			return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
	
				
			}else{ //if inscripcionCurso.isEmpty()
				
				modelo.put("error","No podes agregar otro curso con la misma especialidad"); //Le avisa que no finalizo
				//Trae todo el listado de todos los cursos
				List<Curso> listaCursos =  servicioCurso.buscarCursos();//servicioAlumnoInscripcion
				modelo.put("lista", listaCursos);
				return new ModelAndView("cursos", modelo); //Todavia no curso nada		
			}
		}// fin if rol
				return new ModelAndView("redirect:/index");
	}
	
	
	
	
	
	
	
	
	@RequestMapping(path="/inscripcion")
	public ModelAndView inscribirAlumnoEnElCurso(
			@ModelAttribute("agendasViewModel") AgendasViewModel agendasViewModel,
			HttpServletRequest request )
	{
		ModelMap modelo = new ModelMap();
		if(request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			
			//Sesion
			Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
		//Traigo los datos del alumno logueado
			Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);

			//Datos del curso Elegido
			Curso curso = servicioCurso.buscarCurso(agendasViewModel.getIdCurso());//servicioAlumnoInscripcion
			
			List <Inscripcion> inscripcionCurso = servicioInscripcion.consultarSiYaSeInscribioAUnCurso(idAlumno, curso);//servicioAlumnoInscripcion
							
	if(inscripcionCurso.isEmpty() ) //Todavia ese curso que eligio no esta anotado
		{
						
		//Consultar que no le hayan ocupado esas fechas
		Boolean resultado = servicioAgenda.constatarQueNadieSeAnotaraEnLasFechasAsignadas(agendasViewModel,curso); //servicioAlumnoAgenda
		
		//Si las fechas que me asignaron no fueron ocupadas
			if(resultado == true)
			{
				//Anotarme
					
		/*Va en el servicio de inscripcion*/
		servicioInscripcion.guardarInscripcionEnLaAgendaYEnInscripcion(alumno, curso, agendasViewModel);//servicioAlumnoInscripcion
		modelo.put("mensaje", "Tu inscripcion se realizo con exito");
			
			}
		
			else{ //fin if resultado == true
						
				//Buscarle otras fechas
						
				//Traer todas las fechas con disponibilidad    
				TreeSet<Agenda> agendas= servicioAgenda.traerAgendasConFechasNoRepetidas(curso);//servicioAlumnoAgenda

					if(agendas.isEmpty())
					{
						modelo.put("error", "No hay mas fechas disponibles para realizar una cursada");
								
					}else{
						modelo.put("listaAgendas", agendas);
						modelo.put("listaAgendassize", agendas.size());	
						 }
						
				modelo.put("mensaje", "Una de las clases ha sido ocupada. Te buscamos clases nuevas");
				modelo.put("cursoSeleccionado", curso);
	
				return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
						
			   	 }	
											
							
		}	/////////////////////////if inscripcionCurso.isEmpty()	linea 173
		else{

				modelo.put("error","No podes agregar otro curso con la misma especialidad"); //Le avisa que no finalizo
				
				//Trae todo el listado de todos los cursos
				List<Curso> listaCursos =  servicioCurso.buscarCursos();//servicioAlumnoInscripcion
					
				modelo.put("lista", listaCursos);
				return new ModelAndView("cursos", modelo); //Todavia no curso nada
					
			}
					
			
		modelo.put("curso2", agendasViewModel.getIdCurso());
		modelo.put("agendas2", agendasViewModel.getIdAgendasDepurado());
		modelo.put("agendas2size", agendasViewModel.getIdAgendasDepurado().size());
		
		return new ModelAndView("inscripcionExitosa",modelo); 
		
		
		} //// fin If Session
		return new ModelAndView("redirect:/index");
	}
			
}	
		
		
	


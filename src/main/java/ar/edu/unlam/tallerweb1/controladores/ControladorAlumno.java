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

import org.springframework.format.annotation.DateTimeFormat;
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
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoBuscarAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoBuscarCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoBuscarEstado;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoConsultaEspecialidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoMetodoGuardaCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoMetodoQueBuscaCursos;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoMetodoSiYaSeInscribioOnO;


@Controller
public class ControladorAlumno {

	// La anotacion @Inject indica a Spring que en este atributo se debe setear (inyeccion de dependencias)
	// un objeto de una clase que implemente la interface servicioUsuario, dicha clase debe estar anotada como
	// @Service o @Repository y debe estar en un paquete de los indicados en applicationContext.xml
	
	@Inject
	private ServicioAlumnoMetodoQueBuscaCursos servicioAlumnoBuscaCursos;
	@Inject
	private ServicioAlumnoMetodoSiYaSeInscribioOnO servicioAlumnoSiYaSeInscribioOnO;
	@Inject
	private ServicioAlumnoMetodoGuardaCurso servicioAlumnoGuardaCurso;
	@Inject
	private ServicioAlumnoBuscarAlumno servicioBuscarAlumno;
	@Inject
	private ServicioAlumnoBuscarEstado servicioBuscarEstado;
	@Inject
	private ServicioAlumnoConsultaEspecialidad servicioAlumnoConsultaEspecialidad;
	@Inject
	private ServicioAlumnoBuscarCurso  servicioBuscarCurso;
	@Inject
	private ServicioAlumnoTraerAgendasConFechasNoRepetidas servicioAlumnoTraerAgendasConFechasNoRepetidas;
	@Inject
	private ServicioAlumnoGuardarAlumnoEnAgenda servicioAlumnoGuardarAlumnoEnAgenda;
	
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
		List<Curso> listaCursos =  servicioAlumnoBuscaCursos.buscarCursos();
		
	
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
		Curso curso = servicioBuscarCurso.buscarCurso(cursoElegido);
		
		//Busco el id del estado que dice "Cursando"
		EstadoDelCurso estado = servicioBuscarEstado.buscarEstadoCursando();
		
		//Buscar la especialidad del curso elegido
		Especialidad especialidad = servicioAlumnoConsultaEspecialidad.consultarEspecialidadCursoElegido(cursoElegido);
		
		//Saber si el alumno ya está haciendo este curso que selecciono
		List <TablaCursoAlumno> cursando = servicioAlumnoSiYaSeInscribioOnO.consultarSiYaSeInscribioAUnCurso(idAlumno, estado,especialidad);
		
		
		//Traigo los datos del alumno logueado
				Alumno alumno = servicioBuscarAlumno.buscarAlumno(idAlumno);
						
				TablaCursoAlumno cursoAlumno = new TablaCursoAlumno();	
		
		if(cursando.isEmpty() ) //Todavia ese curso que eligio no esta anotado
			{
				//Guardar en la tablaCursoAlumno el curso y el alumno
				servicioAlumnoGuardaCurso.guardarCurso(alumno, cursoElegido, cursoAlumno, estado);
				
				
			}else{ 

					modelo.put("error","No podes agregar otro curso con la misma especialidad"); //Le avisa que no finalizo
					//Trae todo el listado de todos los cursos
					List<Curso> listaCursos =  servicioAlumnoBuscaCursos.buscarCursos();
					
					modelo.put("lista", listaCursos);
					return new ModelAndView("cursos", modelo); //Todavia no curso nada
					
			}
		
	
	//Traer todas las fechas con disponibilidad
	TreeSet<Agenda> agendas=servicioAlumnoTraerAgendasConFechasNoRepetidas.traerAgendasConFechasNoRepetidas(curso);

	
	if(!agendas.isEmpty())
	{
		//Guardar alumno en la Agenda
		servicioAlumnoGuardarAlumnoEnAgenda.guardarAlumnoConSuCursoElegidoEnLaAgenda(agendas,alumno,curso);
			
	}else{
		modelo.put("error", "No hay mas fechas disponibles para realizar una cursada");
	}
			
	modelo.put("listaAgendas", agendas);
	

		return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
		}
		
		return new ModelAndView("redirect:/index");

	}


	
}

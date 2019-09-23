package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoMetodoQueGuardaFechas;
import ar.edu.unlam.tallerweb1.servicios.ServicioBuscarInstructorPorId;
import ar.edu.unlam.tallerweb1.servicios.ServicioInstructorMetodoQueBuscaTurnos;
import ar.edu.unlam.tallerweb1.servicios.ServicioInstructorQueTraeAlumno;


@Controller
public class ControladorInstructor {

	@Inject
	private ServicioBuscarInstructorPorId servicioInstructor;
	
	
	@Inject
	private ServicioInstructorMetodoQueBuscaTurnos servicioInstructorBuscarTurnos;
	
	@Inject 
	private ServicioInstructorQueTraeAlumno servicioInstructorQueTraeAlumno;

	@RequestMapping ("/indexInstructor")
	public ModelAndView indexInstructor (HttpServletRequest request) {
		return new ModelAndView ("indexInstructor");
	}
	

	@RequestMapping ("/alumnosInstructor")
	public ModelAndView alumnosInstructor (HttpServletRequest request) {
		return new ModelAndView ("alumnosInstructor");
	}

	
	
	@RequestMapping(path="/turnos")
	public ModelAndView BuscarTurnos (HttpServletRequest request,String nombre, String apellido) {
		
		ModelMap model = new ModelMap();
		
		Long idInstructor = (Long) request.getSession().getAttribute("ID");
	
		Instructor i= new Instructor();
		i=servicioInstructor.buscarInstructorPorId(idInstructor);
			
		List <Agenda> agenda = servicioInstructorBuscarTurnos.buscarTurnos(idInstructor);
	
		List<Alumno>alumnos= new ArrayList();
		alumnos= servicioInstructorQueTraeAlumno.buscarAlumnosDeInstructor(idInstructor,nombre,apellido);
		
		
		model.put("turnos", agenda);
		model.put("alumnosInstructor", alumnos);
		model.put("idInst", idInstructor);
		model.put("idInst", i.getUsuario().getNombre());
		
		return new ModelAndView ("indexInstructor",model);
		
	}
}

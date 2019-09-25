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
import ar.edu.unlam.tallerweb1.servicios.ServicioAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioBuscarInstructorPorId;
import ar.edu.unlam.tallerweb1.servicios.ServicioInstructorMetodoQueBuscaTurnos;



@Controller
public class ControladorInstructor {

	@Inject
	private ServicioBuscarInstructorPorId servicioInstructor;
	
	
	@Inject
	private ServicioInstructorMetodoQueBuscaTurnos servicioInstructorBuscarTurnos;
	
	@Inject 
	private ServicioAgenda servicioAgenda;

	
//	@RequestMapping(path="/turnos", method = RequestMethod.GET)
//	public ModelAndView BuscarTurnos (HttpServletRequest request) {
//		
//		ModelMap model = new ModelMap();
//		
//		Long idInstructor = (Long) request.getSession().getAttribute("ID");
//
//		idInstructor=3L;
//		
//		Instructor i= new Instructor();
//		i=servicioInstructor.buscarInstructorPorId(idInstructor);
//		
//		System.out.println("xxxxxxxxxxxx"+ i.getId() +" "+ i.getId()+i.getUsuario().getApellido());
//			
//		List <Agenda> agenda = servicioInstructorBuscarTurnos.buscarTurnos(idInstructor);
//	
//		List<Alumno>alumnos= new ArrayList();
//		alumnos= servicioInstructorQueTraeAlumno.buscarAlumnosDeInstructor(idInstructor,nombre,apellido);
//		
//		model.put("agenda", agenda);
//		model.put("alumnosInstructor", alumnos);
//		model.put("idInst", idInstructor);
//		model.put("listadoFecha", servicioListarFecha.listaFecha());
//		
//		return new ModelAndView ("alumnosInstructor",model);
//		
//	}
	
	@RequestMapping(path="/AlumnosDelInstructor", method = RequestMethod.GET)
	public ModelAndView BuscarTodosLosAlumnosDeUnInstructor (HttpServletRequest request) {
	
		ModelMap model = new ModelMap();
		if(request.getSession().getAttribute("ROL").equals("Instructor"))
		{
			Long idInstructor = (Long) request.getSession().getAttribute("ID");
	
			List<Agenda> listaAgenda = new ArrayList();
			listaAgenda = servicioAgenda.buscarDiaYHorarioDeTurnoDeUnInstructor(idInstructor);
			
			List <Alumno> listaAlumno = new ArrayList();
			listaAlumno = servicioAgenda.buscarNombreyApellidoDeAlumnosDeUnInstructor(idInstructor);
			
			model.put("listaAgenda", listaAgenda);
			model.put("listaAlumno", listaAlumno);
			
			return new ModelAndView ("alumnosInstructor",model);
		
		}
			else {
		return new ModelAndView("login", model);
			     }
	}
}

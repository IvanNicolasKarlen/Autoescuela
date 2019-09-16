package ar.edu.unlam.tallerweb1.controladores;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoMetodoQueGuardaFechas;
import ar.edu.unlam.tallerweb1.servicios.ServicioBuscarInstructorPorId;


@Controller
public class ControladorInstructor {

	@Inject
	private ServicioBuscarInstructorPorId servicioInstructor;

	@RequestMapping(path="/buscarInstructorPorId")
	public ModelAndView buscarInstructorPorId(HttpServletRequest request){
		Long idUsuario = (Long) request.getSession().getAttribute("ID");
		servicioInstructor.buscarInstructorPorId(idUsuario);

		return new ModelAndView("indexInstructor");
		

		
	}
	
	
	@RequestMapping ("/indexInstructor")
	public ModelAndView indexInstructor () {
		
			
		
		
		
		
		
		
		
		
		
		
		return new ModelAndView ("indexInstructor");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package ar.edu.unlam.tallerweb1.controladores;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoMetodoQueGuardaFechas;
import ar.edu.unlam.tallerweb1.servicios.ServicioBuscarInstructorPorId;
import ar.edu.unlam.tallerweb1.servicios.ServicioInstructorMetodoQueBuscaTurnos;


@Controller
public class ControladorInstructor {

	@Inject
	private ServicioBuscarInstructorPorId servicioInstructor;
	
	
	@Inject
	private ServicioInstructorMetodoQueBuscaTurnos servicioInstructorBuscarTurnos;

	@RequestMapping ("/indexInstructor")
	public ModelAndView indexInstructor () {
		
		return new ModelAndView ("indexInstructor");
	}
	
	
	
	@RequestMapping(path="/buscarInstructorPorId")
	public ModelAndView buscarInstructorPorId(HttpServletRequest request){
		Long idUsuario = (Long) request.getSession().getAttribute("ID");
		servicioInstructor.buscarInstructorPorId(idUsuario);

		return new ModelAndView("indexInstructor");
		
	}
	
	
	
	@RequestMapping(path="/turnosI")
	public ModelAndView BuscarTurnos (HttpServletRequest request) {
		
		ModelMap model = new ModelMap();
		
		Long idInstructor= Long.parseLong(request.getSession().getAttribute("ID").toString());
		
		
		List <Agenda> listadoDeTurnos = new ArrayList();
		listadoDeTurnos = servicioInstructorBuscarTurnos.buscarTurnos(idInstructor);
				
		model.put("turnosI", listadoDeTurnos);
		return new ModelAndView ("turnosI",model);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

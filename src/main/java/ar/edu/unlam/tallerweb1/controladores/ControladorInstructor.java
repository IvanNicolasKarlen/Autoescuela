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

@Controller
public class ControladorInstructor {
	
	@Inject 
	private ServicioAgenda servicioAgenda;

	@RequestMapping(path="/AlumnosDelInstructor", method = RequestMethod.GET)
	public ModelAndView BuscarTodosLosAlumnosDeUnInstructor (HttpServletRequest request) {
	
		ModelMap model = new ModelMap();
		if(request.getSession().getAttribute("ROL").equals("Instructor"))
		{
			Long idInstructor = (Long) request.getSession().getAttribute("ID");
			List <Agenda> listaAgenda = servicioAgenda.buscarDiaYHorarioDeTurnoDeUnInstructor(idInstructor);
			
			model.put("listaAgenda", listaAgenda);
		
				return new ModelAndView ("alumnosInstructor",model);
		}else {
				return new ModelAndView("login", model);
			     }
	}
}

package ar.edu.unlam.tallerweb1.controladores;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumnoI;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
@Validated
public class ControladorInstructor {
	
	@Inject 
	private ServicioAgenda servicioAgenda;
	
	@Inject 
	private ServicioUsuario servicioUsuario;
	
	@Inject
	private ServicioAlumnoI servicioAlumno;
	
	@RequestMapping(path="/AlumnosDelInstructor", method = RequestMethod.GET)
	public ModelAndView BuscarTodosLosAlumnosDeUnInstructor (HttpServletRequest request) {
	
		ModelMap model = new ModelMap();
		if(request.getSession().getAttribute("ROL").equals("Instructor"))
		{		
				return new ModelAndView ("alumnosInstructor",model);
		}else {
				return new ModelAndView("login", model);
			     }
	}
	
	
	@RequestMapping(value="/buscadorDeAlumnos", method = RequestMethod.GET)
	public ModelAndView buscarAlumnos ( @RequestParam (name="nombre",required=false)  String nombre,
										@RequestParam (name="apellido",required=false)String apellido,
										HttpServletRequest request){

		ModelMap model = new ModelMap();
		Long idInstructor = (Long) request.getSession().getAttribute("ID");
		List <Agenda> buscarAlumnos =servicioAgenda.buscarAlumnos(nombre,apellido);
		List <Agenda> listaAgenda = servicioAgenda.buscarDiaYHorarioDeTurnoDeUnInstructor(idInstructor);
		List <Usuario> traerAlumnos = servicioUsuario.traerAlumnos(idInstructor);
		
		model.put("listaAgenda", listaAgenda);
		model.put("traerAlumnos",traerAlumnos);
		model.put("buscarAlumnos", buscarAlumnos);
		model.put("ocultar", "mensaje");
				
		return new ModelAndView ("alumnosInstructor",model);
				
		
						}		
	}

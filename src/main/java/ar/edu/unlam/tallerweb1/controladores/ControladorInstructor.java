package ar.edu.unlam.tallerweb1.controladores;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.ViewModel.EstadoDeAgendaViewModel;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;
import ar.edu.unlam.tallerweb1.servicios.ServicioAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoDeAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioVehiculo;

@Controller
@Validated
public class ControladorInstructor {
	
	@Inject 
	private ServicioAgenda servicioAgenda;
	
	@Inject 
	private ServicioUsuario servicioUsuario;
	
	@Inject
	private ServicioEstadoDeAgenda servicioEstadoDeAgenda;
	
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
		List <Agenda> traerAlumnosDisponibles = servicioAgenda.traerFechasDisponibles();
		
		
		model.put("listaAgenda", listaAgenda);
		model.put("traerAlumnos",traerAlumnosDisponibles);
		model.put("buscarAlumnos", buscarAlumnos);
		model.put("ocultar", "mensaje");
				
		return new ModelAndView ("alumnosInstructor",model);
}		
	
	@RequestMapping(value="/claseCancelada", method = RequestMethod.GET)
	public ModelAndView cancelarClase () {

		return new ModelAndView("ClaseCanceladaConExito");
		
	}
	
	@RequestMapping(value="/cancelacionDeClases", method = RequestMethod.GET)
	public ModelAndView cancelarClase (@RequestParam(name="idAgenda",required=false) Long idAgenda,
									   @RequestParam(name="idAgenda",required=false) Long idEstadoAgenda,
									   HttpServletRequest request) {

		ModelMap model = new ModelMap();
		
		List<EstadoDeAgenda> estadosDeAgenda = servicioEstadoDeAgenda.traerListaDeEstadoDeAgenda();
		model.put("estadosDeAgenda",estadosDeAgenda);
		model.put("idAgenda",idAgenda);
		
		
		Agenda agenda = servicioAgenda.buscarAgendaPorId(idAgenda);
		EstadoDeAgenda estadoDeAgenda = servicioEstadoDeAgenda.traerEstadoDeAgendaPorId(idEstadoAgenda);
		agenda.setEstadoDeAgenda(estadoDeAgenda);
		servicioAgenda.updateEstadoDeAgenda(agenda);
		
		return new ModelAndView ("cancelarClase",model);

	}
	
//	@RequestMapping(value="/horasTrabajadas", method = RequestMethod.GET)
//	public ModelAndView horasTrabajadas () {
//
//		
//		
//		LocalDate traerFechasDisponibles = servicioAgenda.traerFechas();
//		
//		ModelMap model = new ModelMap ();
//		model.put("traerFechasDisponibles", traerFechasDisponibles);
//		
//		return new ModelAndView ("horasTrabajadas",model);
//	}
//	

	}
package ar.edu.unlam.tallerweb1.controladores;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoDeAgenda;


@Controller
@Validated
public class ControladorInstructor {
	
	@Inject 
	private ServicioAgenda servicioAgenda;
	
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
	
	
	@RequestMapping(path="/buscadorDeAlumnos", method = RequestMethod.GET)
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

	
	@RequestMapping(value="/claseCanceladaConExito", method = RequestMethod.GET)
	public ModelAndView confirmarCancelacion () {

		return new ModelAndView("ClaseCanceladaConExitoInstructor");
		
	}
	
	@RequestMapping(path="/cancelacionDeAgenda", method = RequestMethod.GET)
	public ModelAndView probar (@RequestParam(name="idAgenda",required=false) Long idAgenda,
								@RequestParam(name="idEstadoAgenda",required=false) Long idEstadoAgenda,
							    @RequestParam(name="confir",required=false,defaultValue="noConfirmado")String confirmacion,
						      	HttpServletRequest request) {		
		
		
		List<EstadoDeAgenda> estadosDeAgenda = servicioEstadoDeAgenda.traerListaDeEstadoDeAgenda();
		EstadoDeAgenda estadoDeAgenda = servicioEstadoDeAgenda.traerEstadoDeAgendaPorId(idEstadoAgenda);
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		String vista = "confirmarCancelacionDeClasesInstructor";
		if(rol.equals("Instructor")){
			model.put("rol", rol);
			model.put("estadosDeAgenda",estadosDeAgenda);
			
		
		if(confirmacion.equals("noConfirmado")){
			model.put("confirmacion", "¿Esta seguro de querer cancelar la clase seleccionada?");
			model.put("idAgenda",idAgenda);
			model.put("idEstadoAgenda", idEstadoAgenda);
		}else {
		switch(confirmacion){
		case "si": 
			Agenda agenda = servicioAgenda.buscarAgendaPorId(idAgenda);
			agenda.setEstadoDeAgenda(estadoDeAgenda);
			servicioAgenda.updateEstadoDeAgenda(agenda);
			model.put("estadoDeAgenda", estadoDeAgenda);
		
			if(servicioAgenda.buscarAgenda(agenda)!=null){
				return new ModelAndView("redirect:/claseCanceladaConExito");
			}
		case "no":
			return new ModelAndView("redirect:/buscadorDeAlumnos");
		
		}}
		
		}	return new ModelAndView(vista,model);
	}
	
	
	
	@RequestMapping(path="/seleccionarMotivo/{idAgenda}", method = RequestMethod.GET)
	public ModelAndView cancelarClase (@PathVariable(value="idAgenda") Long idAgenda,
									   HttpServletRequest request) {

		ModelMap model = new ModelMap();
		
		List<EstadoDeAgenda> estadosDeAgenda = servicioEstadoDeAgenda.traerListaDeEstadoDeAgenda();
		
		model.put("estadosDeAgenda",estadosDeAgenda);
		model.put("idAgenda",idAgenda);

		return new ModelAndView ("cancelarClaseInstructor",model);

	}
	

	@RequestMapping(value="/horasTrabajadas", method = RequestMethod.GET)
	public ModelAndView horasTrabajadas (@RequestParam(name="ids",required=false)Long idInstructor) {

		ModelMap model = new ModelMap ();

		List <Integer> listaMeses = servicioAgenda.horasTrabajadas(idInstructor);
		
		model.put("listaMeses", listaMeses);
		
		System.out.println(listaMeses);
		
		return new ModelAndView ("horasTrabajadasInstructor",model);
	}
	

	}
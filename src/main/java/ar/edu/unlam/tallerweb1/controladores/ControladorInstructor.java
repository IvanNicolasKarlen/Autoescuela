package ar.edu.unlam.tallerweb1.controladores;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;
import ar.edu.unlam.tallerweb1.servicios.ServicioAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoDeAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.servicios.ServicioInstructor;
import ar.edu.unlam.tallerweb1.servicios.ServicioVehiculo;


@Controller
@Validated
public class ControladorInstructor {
	
	@Inject 
	private ServicioAgenda servicioAgenda;
	
	@Inject 
	private ServicioVehiculo servicioVehiculo;
	
	@Inject
	private ServicioEstadoDeAgenda servicioEstadoDeAgenda;
	
	@Inject
	private ServicioEstadoDeVehiculo servicioEstadoDeVehiculo;
	
	@Inject
	private ServicioInstructor servicioInstructor;
	
	@RequestMapping("/indexInstructor")
	public ModelAndView indexAlumno(HttpServletRequest request) {
		
		if(!request.getSession().getAttribute("ROL").equals("Instructor"))
		{
			return new ModelAndView("redirect:/index");
		}
		
		ModelMap modelo = new ModelMap();
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		modelo.put("rol", rol);
			
		//Sesion
		Long idInstructor = (Long) request.getSession().getAttribute("ID");
		Usuario usuario = servicioInstructor.buscarUsuario(idInstructor);
			
		modelo.put("usuario", usuario);
		return new ModelAndView("indexInstructor", modelo);
	}
		
	
	
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
		List <Agenda> buscarAlumnos =servicioAgenda.buscarAlumnos(idInstructor,nombre,apellido);
		List <Agenda> listaAgenda = servicioAgenda.buscarDiaYHorarioDeTurnoDeUnInstructor(idInstructor);
		List <Agenda> traerAlumnosDisponibles = servicioAgenda.traerFechasDisponibles(idInstructor);
		System.out.println(buscarAlumnos);
		System.out.println(listaAgenda);
		System.out.println(traerAlumnosDisponibles);
	
		
		model.put("listaAgenda", listaAgenda);
		model.put("traerAlumnos",traerAlumnosDisponibles);
		model.put("buscarAlumnos", buscarAlumnos);
		model.put("ocultar", "mensaje");
		
		System.out.println(buscarAlumnos);
		System.out.println(listaAgenda);
		System.out.println(traerAlumnosDisponibles);
				
		return new ModelAndView ("alumnosInstructor",model);
	}		

	
	@RequestMapping(value="/claseCanceladaConExito", method = RequestMethod.GET)
	public ModelAndView confirmarCancelacion () {

		return new ModelAndView("ClaseCanceladaConExitoInstructor");
		
	}
	
	@RequestMapping(path="/cancelacionDeAgenda", method = RequestMethod.GET)
	public ModelAndView probar (@RequestParam(name="idAgenda",required=false) Long idAgenda,
								@RequestParam(name="idEstadoAgenda",required=false) Long idEstadoAgenda,
							    @RequestParam(name="mensaje",required=false) String mensaje,
							    @RequestParam(name="idEstadoDeVehiculo",required=false) Long estadoId,
							    @RequestParam(name="confir",required=false,defaultValue="noConfirmado")String confirmacion,
						      	HttpServletRequest request) {		
		
		Long idInstructor = (Long) request.getSession().getAttribute("ID");

		
		List<EstadoDeAgenda> estadosDeAgenda = servicioEstadoDeAgenda.traerListaDeEstadoDeAgenda();
		
		List<EstadoDeVehiculo> estadosDeVehiculo = servicioEstadoDeVehiculo.traerListaDeEstadoDeVehiculo();
		
		EstadoDeAgenda estadoDeAgenda = servicioEstadoDeAgenda.traerEstadoDeAgendaPorId(idEstadoAgenda);
		
		
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		String vista = "confirmarCancelacionDeClasesInstructor";
		if(rol.equals("Instructor")){
			model.put("rol", rol);
			model.put("estadosDeAgenda",estadosDeAgenda);
			model.put("estadosDeVehiculo",estadosDeVehiculo);
			model.put("estadoDeAgenda",estadoDeAgenda);
			
		
		if(confirmacion.equals("noConfirmado")){
			model.put("confirmacion", "¿Esta seguro de querer cancelar la clase seleccionada?");
			model.put("idAgenda",idAgenda);
			model.put("idEstadoAgenda", idEstadoAgenda);
			model.put("mensaje", mensaje);
			model.put("idEstadoDeVehiculo", estadoId);
		}else {
		switch(confirmacion){
		case "si": 
			Agenda agenda = servicioAgenda.buscarAgendaPorId(idAgenda);
			agenda.setEstadoDeAgenda(estadoDeAgenda);
			servicioAgenda.updateEstadoDeAgenda(agenda);
			
			estadoDeAgenda.setDetalle(mensaje);
			servicioEstadoDeAgenda.updateEstadoDeAgenda(estadoDeAgenda);
		
			model.put("estadoDeAgenda", estadoDeAgenda);
			model.put("mensaje", mensaje);

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
		
		List<EstadoDeAgenda> estadosDeAgenda = servicioEstadoDeAgenda.traerListaDeEstadoDeAgendaMenosEstadoDisponible();
		List<EstadoDeVehiculo> estadoDeVehiculo = servicioEstadoDeVehiculo.traerListaDeEstadoDeVehiculo();
		model.put("estadosDeAgenda",estadosDeAgenda);
		model.put("estadoDeVehiculo",estadoDeVehiculo);
		model.put("idAgenda",idAgenda);
	

		return new ModelAndView ("cancelarClaseInstructor",model);

	}
	

	@RequestMapping(path="/horasTrabajadas", method = RequestMethod.GET)
	public ModelAndView horasTrabajadas (@RequestParam(name="ids",required=false)Long idInstructor) {

		ModelMap model = new ModelMap ();
		
		Map<String,Integer> listaMeses = servicioAgenda.horasTrabajadas(idInstructor);

		model.put("listaMeses", listaMeses);

		return new ModelAndView ("horasTrabajadasInstructor",model);
	}

	@RequestMapping(path="/grafico", method = RequestMethod.GET)
	public ModelAndView grafico (@RequestParam(name="ids",required=false)Long idInstructor) {
		
		ModelMap model = new ModelMap ();
		
		Map<String,Integer> listaMeses = servicioAgenda.horasTrabajadas(idInstructor);	
		model.put("listaMeses", listaMeses);
		
		
		return new ModelAndView ("grafico",model);
	}
	
	
	//getters y setters
	public ServicioAgenda getServicioAgenda() {
		return servicioAgenda;
	}


	public void setServicioAgenda(ServicioAgenda servicioAgenda) {
		this.servicioAgenda = servicioAgenda;
	}


	public ServicioEstadoDeAgenda getServicioEstadoDeAgenda() {
		return servicioEstadoDeAgenda;
	}


	public void setServicioEstadoDeAgenda(ServicioEstadoDeAgenda servicioEstadoDeAgenda) {
		this.servicioEstadoDeAgenda = servicioEstadoDeAgenda;
	}
	

	}
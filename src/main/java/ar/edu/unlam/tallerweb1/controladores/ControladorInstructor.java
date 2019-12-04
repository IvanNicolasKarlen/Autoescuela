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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;
import ar.edu.unlam.tallerweb1.servicios.ServicioAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoDeAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.servicios.ServicioInstructor;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
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
	
	@Inject
	private ServicioUsuario servicioUsuario;
	
	@Inject
	private ServicioNotificacion servicioNotificacion;
	
		
	
	
	@RequestMapping(path="/AlumnosDelInstructor", method = RequestMethod.GET)
	public ModelAndView BuscarTodosLosAlumnosDeUnInstructor (HttpServletRequest request) {
		
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
	
		if(rol.equals("Instructor")){
			
			ModelMap model = new ModelMap();
			model.put("rol", rol);
			return new ModelAndView ("alumnosInstructor",model);
			
		}else {
			return new ModelAndView("redirect:/index");
		}
	}
	
	
	@RequestMapping(path="/buscadorDeAlumnos")
	public ModelAndView buscarAlumnos ( 
										@RequestParam (name="nombreDeUsuario",required=false)String nombreDeUsuario,
										HttpServletRequest request){

		ModelMap model = new ModelMap();

		Long idInstructor = (Long) request.getSession().getAttribute("IDROL");
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		if(rol.equals("Instructor")){

		
		List <Agenda> buscarAlumnos =servicioAgenda.buscarAlumnos(idInstructor,nombreDeUsuario);
		List <Agenda> listaAgenda = servicioAgenda.buscarDiaYHorarioDeTurnoDeUnInstructor(idInstructor);
		List <Agenda> traerAlumnosDisponibles = servicioAgenda.traerFechasDisponibles(idInstructor);
		
		model.put("listaAgenda", listaAgenda);
		model.put("traerAlumnos",traerAlumnosDisponibles);
		model.put("buscarAlumnos", buscarAlumnos);
		model.put("rol", rol);
		model.put("ocultar", "mensaje");
		
		return new ModelAndView ("alumnosInstructor",model);
		}else {
			return new ModelAndView("redirect:/index");
		}
		
	}		

	
	@RequestMapping(value="/claseCanceladaConExito", method = RequestMethod.GET)
	public ModelAndView confirmarCancelacion (HttpServletRequest request) {
		
		
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		if(rol.equals("Instructor")){
			
			ModelMap model = new ModelMap();
			model.put("rol",rol);
			return new ModelAndView("ClaseCanceladaConExitoInstructor");
		
	}else {
		return new ModelAndView("redirect:/index");
	}
	}


	
	@RequestMapping(path="/cancelacionDeAgenda", method = RequestMethod.GET)
	public ModelAndView probar (@RequestParam(name="idAgenda",required=false) Long idAgenda,
								@RequestParam(name="idEstadoAgenda",required=false) Long idEstadoAgenda,
							    @RequestParam(name="idEstadoDeVehiculo",required=false) Long estadoId,
							    @RequestParam(name="idV",required=false) Long idV,
							    @RequestParam(name="confir",required=false,defaultValue="noConfirmado")String confirmacion,
						      	HttpServletRequest request) {		
		
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		
		Long id =(long)request.getSession().getAttribute("ID");
		Usuario user = servicioUsuario.traerUsuarioPorId(id);

		if(rol.equals("Instructor")){

		List<EstadoDeAgenda> estadosDeAgenda = servicioEstadoDeAgenda.traerListaDeEstadoDeAgenda();
		
		List<EstadoDeVehiculo> estadosDeVehiculo = servicioEstadoDeVehiculo.traerListaDeEstadoDeVehiculo();
		
		EstadoDeVehiculo estadoVehiculo = servicioEstadoDeVehiculo.traerEstadoVehiculoPorNombre("No funcionando");
		
		EstadoDeAgenda estadoDeAgenda = servicioEstadoDeAgenda.traerEstadoDeAgendaPorId(idEstadoAgenda);		

		ModelMap model = new ModelMap();
		String vista = "confirmarCancelacionDeClasesInstructor";
		if(rol.equals("Instructor")){
			model.put("rol", rol);
			model.put("estadosDeAgenda",estadosDeAgenda);
			model.put("estadosDeVehiculo",estadosDeVehiculo);
			model.put("estadoDeAgenda",estadoDeAgenda);
			model.put("estadoVehiculo",estadoVehiculo);
			
		if(confirmacion.equals("noConfirmado")){
			model.put("confirmacion", "¿Esta seguro de querer cancelar la clase seleccionada?");
			model.put("idAgenda",idAgenda);
			model.put("idEstadoAgenda", idEstadoAgenda);
			model.put("idEstadoDeVehiculo", estadoId);
			model.put("idV", idV);
		}else {
		switch(confirmacion){
		case "si": 
			Agenda agenda = servicioAgenda.buscarAgendaPorId(idAgenda);
//			agenda.setEstadoDeAgenda(estadoDeAgenda);
//			servicioAgenda.updateAgenda(agenda);
		
			Vehiculo ve = agenda.getInstructorVehiculoEspecialidad().getVehiculo();
			ve.setEstadoDeVehiculo(estadoVehiculo);
			servicioVehiculo.updateVehiculo(ve);
			agenda.setEstadoDeAgenda(estadoDeAgenda);
			servicioAgenda.updateAgenda(agenda);
			servicioNotificacion.crearNotificacion(user, agenda);
			
			model.put("estadoDeAgenda", estadoDeAgenda);
			model.put("estadoVehiculo", estadoVehiculo);
			model.put("agenda", agenda);
			

			if(servicioAgenda.buscarAgenda(agenda)!=null){
				return new ModelAndView("redirect:/claseCanceladaConExito");
			}
		case "no":
			return new ModelAndView("redirect:/buscadorDeAlumnos");
		
		}}
		
		}	return new ModelAndView(vista,model);
		}else {
			return new ModelAndView("redirect:/index");

		}
	}
	
	
	@RequestMapping(path="/seleccionarMotivo/{idAgenda}", method = RequestMethod.GET)
	public ModelAndView cancelarClase (@PathVariable(value="idAgenda") Long idAgenda,
									   HttpServletRequest request) {
		
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;

		if(rol.equals("Instructor")){
		
		ModelMap model = new ModelMap();
		
		List<EstadoDeAgenda> estadosDeAgenda = servicioEstadoDeAgenda.traerListaDeEstadoDeAgendaParaInstructor();
		List<EstadoDeVehiculo> estadoDeVehiculo = servicioEstadoDeVehiculo.traerListaDeEstadoDeVehiculo();
		
		
		model.put("estadosDeAgenda",estadosDeAgenda);
		model.put("estadoDeVehiculo",estadoDeVehiculo);
		model.put("idAgenda",idAgenda);
		model.put("rol", rol);
	

		return new ModelAndView ("cancelarClaseInstructor",model);
		}else {
			return new ModelAndView("redirect:/index");
		}
	}
	

	@RequestMapping(path="/horasTrabajadas", method = RequestMethod.GET)
	public ModelAndView horasTrabajadas (HttpServletRequest request) {

		ModelMap model = new ModelMap ();
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;

		if(rol.equals("Instructor")){
		
		Long idInstructor = (Long)request.getSession().getAttribute("IDROL");
		Map<String,Integer> listaMeses = servicioAgenda.horasTrabajadas(idInstructor);

		model.put("listaMeses", listaMeses);
		model.put("rol", rol);

		return new ModelAndView ("horasTrabajadasInstructor",model);
	}else {
		return new ModelAndView("redirect:/index");
	}
}
	
	@RequestMapping(path="/grafico", method = RequestMethod.GET)
	public ModelAndView grafico (HttpServletRequest request) {
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		if(rol.equals("Instructor")){
			
		ModelMap model = new ModelMap ();
		Long idInstructor = (Long)request.getSession().getAttribute("IDROL");
		Map<String,Integer> listaMeses = servicioAgenda.horasTrabajadas(idInstructor);	
		model.put("listaMeses", listaMeses);
		model.put("rol",rol);
		
		
		return new ModelAndView ("grafico",model);
	}else {
		return new ModelAndView("redirect:/index");
	}
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
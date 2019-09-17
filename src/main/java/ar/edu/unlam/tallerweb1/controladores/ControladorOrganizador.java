package ar.edu.unlam.tallerweb1.controladores;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.ViewModel.TurnosViewModel;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorAgregarCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorConvierteFecha;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorCrearAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorValidaFechaElegida;
import ar.edu.unlam.tallerweb1.servicios.ServicioVehiculo;

@Controller
public class ControladorOrganizador {
	
	@Inject
	private ServicioOrganizadorConvierteFecha servicioOrganizadorConvierteFecha;
	@Inject
	private ServicioOrganizadorValidaFechaElegida servicioOrganizadorValidaFechaElegida;
	@Inject
	private ServicioOrganizadorAgregarCurso servicioOrganizadorAgregarCurso;
	@Inject
	private ServicioOrganizadorCrearAgenda servicioOrganizadorCrearAgenda;
	@Inject
	private ServicioVehiculo servicioVehiculo;
	
	@RequestMapping(path="/agregarCurso")
	public ModelAndView elegirFechaDesdeHasta(HttpServletRequest request){
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		ModelMap modelo = new ModelMap();
		if(rol.equals("Organizador")){
			Curso curso = new Curso();
			modelo.put("curso", curso);
		}
		else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarcursoOrg",modelo);
	}
	
	@RequestMapping(path="/validarCurso")
	public ModelAndView validarCurso(@ModelAttribute("curso")Curso curso, HttpServletRequest request){
		ModelMap model = new ModelMap();

		if(curso.getCantClasesPracticas()!=null&&!(curso.getDescripcion().isEmpty())&&curso.getDescripcion()!=null
				&&curso.getPrecio()!=null&&curso.getTitulo()!=null&&!(curso.getTitulo().isEmpty())){
			Boolean estado = servicioOrganizadorAgregarCurso.agregarCurso(curso);
			if(estado){
				model.put("mensaje1", "Curso añadido correctamente");
				LocalDate desde = LocalDate.now();
				LocalDate hasta = desde.plusDays(3);
				String mensaje2 = servicioOrganizadorCrearAgenda.crearAgenda(desde, hasta);
				model.put("mensaje2", mensaje2);
			}else{
				model.put("mensaje1", "Error al añadir curso");
				
			}
		}else{
			model.put("error", "Faltan completar datos");
		}
		return new ModelAndView("cursoOrganizador",model);
	}
	
	@RequestMapping("/agregarVehiculo")
	public ModelAndView agregarVehiculo(HttpServletRequest request){
		ModelMap model = new ModelMap();
		String rol = (String)request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		if(rol.equals("Organizador")){
			Vehiculo vehiculo = new Vehiculo();
			model.put("vehiculo", vehiculo);
		}
		else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarvehiculoOrg",model);
	}
	@RequestMapping("/validarVehiculo")
	public ModelAndView validarVehiculo(@ModelAttribute("vehiculo") Vehiculo miv){
		ModelMap model = new ModelMap();
		if(miv.getEstado().isEmpty()||miv.getEstado()==null||miv.getPatente().isEmpty()||miv.getPatente()==null
				||miv.getTipo().isEmpty()||miv.getTipo()==null){
			model.put("error", "Rellene todos los campos");
		}else{
			if(servicioVehiculo.buscarVehiculo(miv)==null){
				String msj = servicioVehiculo.guardarVehiculo(miv);
				model.put("mensaje", msj);
			}else{
				model.put("error", "Ese vehiculo ya existe");
			}
		}
		Vehiculo vehiculo = new Vehiculo();
		model.put("vehiculo", vehiculo);
		return new ModelAndView("agregarvehiculoOrg",model);
	}
	@RequestMapping("/agregarInstructor")
	public ModelAndView agregarInstructor(HttpServletRequest request){
		ModelMap model = new ModelMap();
		String rol = (String)request.getAttribute("ROL");
		if(rol.equals("Organizador")){
			Instructor ins = new Instructor();
			model.put("instructor", ins);
		}
		else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarInstructorOrg",model);
	}

	
}
	



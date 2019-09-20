package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.ViewModel.TurnosViewModel;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;
import ar.edu.unlam.tallerweb1.modelo.TipoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEspecialidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.servicios.ServicioIVE;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorAgregarCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorAgregarInstructor;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorConvierteFecha;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorCrearAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorValidaFechaElegida;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioVehiculo;
import ar.edu.unlam.tallerweb1.servicios.ServicioTipoDeVehiculo;

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
	@Inject 
	private ServicioEspecialidad servicioEspecialidad;
	@Inject
	private ServicioOrganizadorAgregarInstructor servicioOrganizadorAgregarInstructor;
	@Inject
	private ServicioUsuario servicioUsuario;
	@Inject
	private ServicioIVE servicioIve;
	@Inject
	private ServicioTipoDeVehiculo servicioTipoDeVehiculo;
	@Inject
	private ServicioEstadoDeVehiculo servicioEstadoDeVehiculo;
	
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
	
	@RequestMapping(path="/validarCurso",method=RequestMethod.POST)
	public ModelAndView validarCurso(@ModelAttribute("curso")Curso curso, HttpServletRequest request){
		ModelMap model = new ModelMap();

		if(curso.getCantClasesPracticas()!=null&&!(curso.getDescripcion().isEmpty())&&curso.getDescripcion()!=null
				&&curso.getPrecio()!=null&&curso.getTitulo()!=null&&!(curso.getTitulo().isEmpty())){
			if(servicioOrganizadorAgregarCurso.agregarCurso(curso)!=null){
				model.put("mensaje1", "Curso añadido correctamente");
				
				
			}else{
				model.put("mensaje1", "Error al añadir curso");
				
			}
		}else{
			model.put("error", "Faltan completar datos");
		}
		return new ModelAndView("cursoOrganizador",model);
	}
	@RequestMapping(path="/crearAgenda")
	public ModelAndView crearAgenda(HttpServletRequest request){
		ModelMap model = new ModelMap();		
		if(request.getSession().getAttribute("ROL").equals("Organizador")){
			LocalDate desde = LocalDate.now();
			LocalDate hasta = desde.plusDays(3);
			if(servicioOrganizadorCrearAgenda.crearAgenda(desde, hasta)!=null){
				model.put("mensaje", "Agenda creada exitosamente");
			}else{
				model.put("mensaje", "Error al crear la agenda");
			}
		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("crearAgendaOrg",model);
	}
	
	@RequestMapping("/agregarVehiculo")
	public ModelAndView agregarVehiculo(HttpServletRequest request){
		ModelMap model = new ModelMap();
		String rol = (String)request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		if(rol.equals("Organizador")){
			Vehiculo vehiculo = new Vehiculo();
			model.put("vehiculo", vehiculo);
			List<TipoDeVehiculo> listaTipoVehiculo = servicioTipoDeVehiculo.traerTiposDeVehiculos();
			model.put("listatipovehiculos", listaTipoVehiculo);
			EstadoDeVehiculo estadoVehiculo = servicioEstadoDeVehiculo.buscarEstadoPorEstadoActual("Funcionando");
			model.put("estado",estadoVehiculo);
		}
		else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarvehiculoOrg",model);
	}
	@RequestMapping(path="/validarVehiculo",method=RequestMethod.POST)
	public ModelAndView validarVehiculo(@ModelAttribute("vehiculo") Vehiculo miv){
		ModelMap model = new ModelMap();
		if(miv.getModelo().isEmpty()||miv.getModelo()==null||miv.getPatente().isEmpty()||miv.getPatente()==null||miv.getTipoDeVehiculo()==null||miv.getEstadoDeVehiculo()==null){
			model.put("error", "Rellene todos los campos");
		}else{
			if(servicioVehiculo.buscarVehiculo(miv)==null){
				if(servicioVehiculo.guardarVehiculo(miv)!=null){
					model.put("mensaje", "Vehiculo insertado exitosamente");
				}else{
					model.put("mensaje", "Error al insertar vehiculo");
				}
				
			}else{
				model.put("error", "Ese vehiculo ya existe");
			}
		}
		Vehiculo vehiculo = new Vehiculo();
		model.put("vehiculo", vehiculo);
		List<TipoDeVehiculo> listaTipoVehiculo = servicioTipoDeVehiculo.traerTiposDeVehiculos();
		model.put("listatipovehiculos", listaTipoVehiculo);
		EstadoDeVehiculo estadoVehiculo = servicioEstadoDeVehiculo.buscarEstadoPorEstadoActual("Funcionando");
		model.put("estado",estadoVehiculo);
		return new ModelAndView("agregarvehiculoOrg",model);
	}
	@RequestMapping("/agregarInstructor-1")
	public ModelAndView mostrarEspecialidadParaAgregarInstructor(HttpServletRequest request){
		ModelMap model = new ModelMap();
		String rol = (String)request.getSession().getAttribute("ROL");
		if(rol.equals("Organizador")){
			List<Especialidad> listaEspecialidades = servicioEspecialidad.traerListaDeEspecialidades();
			model.put("especialidades", listaEspecialidades);
		}
		else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarInstructorOrg1",model);
	}
	@RequestMapping("/agregarInstructor-2")
	public ModelAndView agregarInstructor(@RequestParam(value="idE")Long idE, HttpServletRequest request){
		ModelMap model = new ModelMap();
		String rol = (String)request.getSession().getAttribute("ROL");
		if(rol.equals("Organizador")){
			Usuario user = new Usuario();
			Especialidad especialidad = servicioEspecialidad.traerEspecialidadPorId(idE);
			List<Vehiculo> vehiculos = servicioVehiculo.obtenerVehiculoPorEspecialidad(especialidad);
			request.getSession().setAttribute("idEspecialidad", idE);
			model.put("usuario", user);
			model.put("listaVehiculos", vehiculos);
		}else{
			return new ModelAndView("redirect:/index");
		}
		
		
		return new ModelAndView("agregarInstructorOrg2",model);
	}
	@RequestMapping(path="/validarInstructor", method=RequestMethod.POST)
	public ModelAndView validarInstructor(@ModelAttribute("usuario") Usuario user, 
			@RequestParam(value="idV")Long idVehiculo,@RequestParam(value="pass2")String password2, 
			HttpServletRequest request){
		ModelMap model = new ModelMap();
		if(user.getNombre().isEmpty()||user.getNombre()==null||user.getApellido().isEmpty()||user.getApellido()==null||
				user.getDni()==null||user.getDni().toString().length()!=8||user.getPassword().isEmpty()||user.getPassword()==null){
			model.put("error", "Por favor complete los campos obligatorios");
		}
		else{
			if(!(user.getPassword().equals(password2))){
				model.put("error", "Las contraseñas no coinciden");
			}else{
				Usuario usuarioBuscado = servicioUsuario.consultarUsuario(user);
				if(usuarioBuscado != null){
					model.put("error","Ya existe un usuario con esos datos");
				}else{
					user.setRol("Instructor");
					Instructor instructor = new Instructor();
					user.setInstructor(instructor);
					if(servicioUsuario.insertarUsuario(user)!=null){
						Vehiculo vehiculo = servicioVehiculo.buscarVehiculoPorId(idVehiculo);
						Especialidad especialidad = servicioEspecialidad.traerEspecialidadPorId((Long)request
													.getSession().getAttribute("idEspecialidad"));
						InstructorVehiculoEspecialidad ive = new InstructorVehiculoEspecialidad();
						ive.setInstructor(instructor);
						ive.setVehiculo(vehiculo);
						ive.setEspecialidad(especialidad);
						if(servicioIve.guardarIve(ive)!=null){
							model.put("mensaje", "Instructor agregado con éxito!");
						}else{
							model.put("error", "Error al crear Instructor");
						}
					}else{
						model.put("error", "Error al crear Instructor");
					}
					return new ModelAndView("agregarInstructorOrg3",model);
				}
			}
		}
		return new ModelAndView("agregarInstructorOrg2",model);
	}

	
}
	



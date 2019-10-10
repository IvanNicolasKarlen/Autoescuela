package ar.edu.unlam.tallerweb1.controladores;

import java.util.List;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorInstructor;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorConvierteFecha;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorCrearAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorCurso;
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
	private ServicioOrganizadorCurso servicioOrganizadorCurso;
	@Inject
	private ServicioOrganizadorCrearAgenda servicioOrganizadorCrearAgenda;
	@Inject
	private ServicioVehiculo servicioVehiculo;
	@Inject 
	private ServicioEspecialidad servicioEspecialidad;
	@Inject
	private ServicioOrganizadorInstructor servicioOrganizadorInstructor;
	@Inject
	private ServicioUsuario servicioUsuario;
	@Inject
	private ServicioIVE servicioIve;
	@Inject
	private ServicioTipoDeVehiculo servicioTipoDeVehiculo;
	@Inject
	private ServicioEstadoDeVehiculo servicioEstadoDeVehiculo;
	
	@RequestMapping(path="/agregarCurso")
	public ModelAndView agregarCurso(HttpServletRequest request){
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		ModelMap modelo = new ModelMap();
		if(rol.equals("Organizador")){
			Curso curso = new Curso();
			modelo.put("curso", curso);
			List<Especialidad> especialidades = servicioEspecialidad.traerListaDeEspecialidades();
			modelo.put("listaEspecialidades", especialidades);
		}
		else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarcursoOrg",modelo);
	}
	
	@RequestMapping(path="/validarCurso",method=RequestMethod.POST)
	public ModelAndView validarCurso(@ModelAttribute("curso")Curso curso, 
			HttpServletRequest request,
			@RequestParam(name="especialidadId")Long especialidadId){
		ModelMap model = new ModelMap();

		if(curso.getCantClasesPracticas()!=null&&!(curso.getDescripcion().isEmpty())&&curso.getDescripcion()!=null
				&&curso.getPrecio()!=null&&curso.getTitulo()!=null&&!(curso.getTitulo().isEmpty())||especialidadId!=null){
				Especialidad especialidad = servicioEspecialidad.traerEspecialidadPorId(especialidadId);
				curso.setEspecialidad(especialidad);
			if(servicioOrganizadorCurso.buscarCurso(curso)==null){
				if(servicioOrganizadorCurso.agregarCurso(curso)!=null){
					model.put("mensaje","Curso añadido correctamente");
				}else{
					model.put("error", "Error al añadir curso. Intente nuevamente");
				}
			}else{
				model.put("error", "¡Este curso ya existe!");
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
				List<Especialidad> listaEspecialidades = servicioEspecialidad.traerListaDeEspecialidades();
				model.put("listaEspecialidades", listaEspecialidades);
		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("crearAgendaOrg",model);
	}
	
	@RequestMapping(path="/validarAgenda", method=RequestMethod.POST)
	public ModelAndView validarAgenda(@RequestParam(name="especialidadId")Long espid,
			@RequestParam(name="horaComienzo")Integer horaC,
			@RequestParam(name="horaFinal")Integer horaF,
			@RequestParam(name="hastaD") Integer hastaD,
			HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			if((horaC<horaF)&&espid!=null&&hastaD>1&&hastaD<=60&&hastaD!=null){
				LocalDate desde = LocalDate.now();
				LocalDate hasta = desde.plusDays(hastaD);
				
				Especialidad especialidad = servicioEspecialidad.traerEspecialidadPorId(espid);
				List <InstructorVehiculoEspecialidad> listaIvePorEsp = servicioIve.traerListaIvePorEspecialidad(especialidad);
				if(servicioOrganizadorCrearAgenda.crearAgenda(desde, hasta, horaC, horaF,listaIvePorEsp)!=null){
					model.put("mensaje", "¡Agenda Creada con Éxito!");
				}else{
					model.put("error", "Hubo un problema al crear la agenda");
				}
			}else{
				model.put("error", "Hay un error con los datos seleccionados. Verifique que todo sea correcto");
			}
		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("verificarAgenda",model);
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
			model.put("estado",estadoVehiculo.getId());
		}
		else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarvehiculoOrg",model);
	}
	@RequestMapping(path="/agregarVehiculo-2",method=RequestMethod.POST)
	public ModelAndView validarVehiculo(@ModelAttribute("vehiculo") Vehiculo miv,@RequestParam(name="estadoId")Long estadoId){
		ModelMap model = new ModelMap();
		if(miv.getModelo().isEmpty()||miv.getModelo()==null||miv.getPatente().isEmpty()||miv.getPatente()==null||estadoId==null){
			model.put("error", "Rellene todos los campos");
		}else{
			if(servicioVehiculo.buscarVehiculo(miv)==null){
				EstadoDeVehiculo estadoVehiculo = servicioEstadoDeVehiculo.buscarEstadoPorId(estadoId);
				miv.setEstadoDeVehiculo(estadoVehiculo);
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

	
	@RequestMapping("/agregarEspecialidad")
	public ModelAndView agregarEspecialidad(HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		if(rol.equals("Organizador")){
			return new ModelAndView("agregarEspecialidadOrg");
		}
		else{
			return new ModelAndView("redirect:/index");
		}	

	}
	@RequestMapping("/AgregarEspecialidad")
	public ModelAndView validarEspecialidad(@RequestParam(name="tipo")String tipoEsp, HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			if(servicioEspecialidad.traerEspecialidadPorNombre(tipoEsp)==null){
				Especialidad especialidad = new Especialidad();
				especialidad.setTipo(tipoEsp);
				if(servicioEspecialidad.guardarEspecialidad(especialidad)!=null){
					model.put("mensaje", "Especialidad: " +tipoEsp +" Guardada con éxito.");
				}else{
					model.put("error", "Hubo un problema al agregar la especialidad");
				}
			}else{
				model.put("mensaje", "¡Esa Especialidad ya existe!");
			}

		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarEspecialidadOrg",model);
	}
	
	@RequestMapping("/agregarTipoVehiculo")
	public ModelAndView agregarTipoDeVehiculo(HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			TipoDeVehiculo tipoVehiculo = new TipoDeVehiculo();
			List <Especialidad> especialidades = servicioEspecialidad.traerListaDeEspecialidades();
			model.put("listaEspecialidades",especialidades);
			model.put("tipoDeVehiculo", tipoVehiculo);
			return new ModelAndView("agregarTipoVehiculoOrg",model);
		}
		else{
			return new ModelAndView("redirect:/index");
		}	

	}
	@RequestMapping(path="/AgregarTipoVehiculo", method=RequestMethod.POST)
	public ModelAndView validarTipoDeVehiculo(@ModelAttribute("tipoDeVehiculo")TipoDeVehiculo tipoVehiculo,
			HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			if(servicioTipoDeVehiculo.buscarTipoDeVehiculo(tipoVehiculo)==null){
				if(servicioTipoDeVehiculo.guardarTipoDeVehiculo(tipoVehiculo)!=null){
					model.put("mensaje", "Tipo de Vehiculo: " +tipoVehiculo.getTipo() +" Guardado con éxito.");
				}else{
					model.put("error", "Hubo un problema al agregar el tipo de vehiculo");
				}
			}else{
				model.put("mensaje", "¡Esa Tipo de Vehiculo ya existe!");
			}

		}else{
			return new ModelAndView("redirect:/index");
		}
		TipoDeVehiculo tipoDeVehiculoNuevo = new TipoDeVehiculo();
		List <Especialidad> especialidades = servicioEspecialidad.traerListaDeEspecialidades();
		model.put("listaEspecialidades",especialidades);
		model.put("tipoDeVehiculo", tipoDeVehiculoNuevo);
		return new ModelAndView("agregarTipoVehiculoOrg",model);
	}
	
	@RequestMapping(path="/agregarInstructor")
	public ModelAndView agregarInstructor(HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			Usuario usuario = new Usuario();
			List<Especialidad> listaEspecialidades = servicioEspecialidad.traerListaDeEspecialidades();
			model.put("listaEsp", listaEspecialidades);
			model.put("usuario", usuario);
		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarInstructorOrg",model);
	}
	@RequestMapping(path="/agregarInstructor2", method=RequestMethod.POST)
	public ModelAndView agregarInstructor2(@ModelAttribute("usuario")Usuario user,@RequestParam(name="pass2")String password2,
			HttpServletRequest request, @RequestParam(name="listaEsp")List<Especialidad> listaEspecialidad){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			if(user.getNombre().isEmpty()||user.getNombre()==null||user.getApellido().isEmpty()||user.getApellido()==null||
					user.getDni()==null||user.getDni().toString().length()!=8
					||user.getPassword().isEmpty()||user.getPassword()==null||user.getNombreDeUsuario().isEmpty()
					||user.getNombreDeUsuario()==null){
				model.put("error", "Por favor complete los campos obligatorios");
			}else{
				if(!(user.getPassword().equals(password2))){
					model.put("error","Las contraseñas no coinciden.");
				}else{
					if(servicioUsuario.consultarUsuario(user)!=null){
						model.put("error", "Ya existe un Usuario con esos datos");
					}else{
						Instructor instructor = new Instructor();
						instructor.setUsuario(user);
						model.put("ins", instructor.getId());
						
						for(Especialidad esp:listaEspecialidad){
							InstructorVehiculoEspecialidad ive = new InstructorVehiculoEspecialidad();
							ive.setInstructor(instructor);
							ive.setEspecialidad(esp);
							servicioIve.guardarIve(ive);
						}
						model.put("listaEspecialidades", listaEspecialidad);
					}
				}
			}

		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarInstructorOrg2",model);
	}
	@RequestMapping(path="/seleccionarVehiculoParaInstructor", method=RequestMethod.POST)
	public ModelAndView agregarInstructor3(@RequestParam(name="eps")Long idEspecialidad,
			@RequestParam(name="idInstructor") Long idInstructor,
	HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			if(idEspecialidad!=null&&idInstructor!=null){
				Especialidad esp = servicioEspecialidad.traerEspecialidadPorId(idEspecialidad);
				Instructor ins = servicioOrganizadorInstructor.buscarInstructorPorId(idInstructor);
				InstructorVehiculoEspecialidad ive = servicioIve.traerIveProInstructorEspecialidad(esp, ins);
				List<Vehiculo> vehiculos = servicioVehiculo.obtenerVehiculoPorEspecialidad(esp);
				model.put("listaV", vehiculos);
				model.put("iveId", ive.getId());
			}else{
				model.put("error", "Seleccione una especialidad válida");
			}
		}	
	else{
		return new ModelAndView("redirect:/index");
		}
	return new ModelAndView("agregarInstructor3",model);
	}
	@RequestMapping(path="/validarInstructor", method=RequestMethod.POST)
	public ModelAndView agregarInstructor4(@RequestParam(name="idv")Long idVehiculo,
			@RequestParam(name="iveId") Long idIVE,
	HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			Vehiculo v = servicioVehiculo.buscarVehiculoPorId(idVehiculo);
			InstructorVehiculoEspecialidad ive = servicioIve.buscarIvePorId(idIVE);
			ive.setVehiculo(v);
			if(servicioIve.guardarIve(ive)!=null){
				model.put("mensaje", "Instructor añadido con exito");
				model.put("instructor", ive.getInstructor());
			}else{
				model.put("error", "Hubo un problema al agregar el vehiculo al instructor");
			}
		}	
	else{
		return new ModelAndView("redirect:/index");
		}
	return new ModelAndView("finalInstructor",model);
	}



}
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
import ar.edu.unlam.tallerweb1.modelo.Asistencia;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;
import ar.edu.unlam.tallerweb1.modelo.TipoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;
import ar.edu.unlam.tallerweb1.servicios.ServicioAsistencia;
import ar.edu.unlam.tallerweb1.servicios.ServicioEspecialidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoDeVehiculoImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoDelCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioIVE;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorInstructor;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorConvierteFecha;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorCrearAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorInscripcion;
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
	@Inject
	private ServicioEstadoDelCurso servicioEstadoDelCurso;
	@Inject
	private ServicioAsistencia servicioAsistencia;
	@Inject
	private ServicioOrganizadorInscripcion servicioInscripcion;

	@RequestMapping(path="/agregarCurso")
	public ModelAndView agregarCurso(HttpServletRequest request){
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		ModelMap modelo = new ModelMap();
		if(rol.equals("Organizador")){
			Curso curso = new Curso();
			modelo.put("curso", curso);
			List<Especialidad> especialidades = servicioEspecialidad.traerListaDeEspecialidades();
			modelo.put("listaEspecialidades", especialidades);
			modelo.put("rol", rol);
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
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		if(rol.equals("Organizador")){
			if(curso.getCantClasesPracticas()!=null&&!(curso.getDescripcion().isEmpty())&&curso.getDescripcion()!=null
					&&curso.getPrecio()!=null&&curso.getTitulo()!=null&&!(curso.getTitulo().isEmpty())||especialidadId!=null){
				Especialidad especialidad = servicioEspecialidad.traerEspecialidadPorId(especialidadId);
				EstadoDelCurso estadoDelCurso = servicioEstadoDelCurso.traerEstadoDelCursoPorNombre("Disponible");
				curso.setEspecialidad(especialidad);
				curso.setEstadoDelCurso(estadoDelCurso);
				model.put("rol", rol);
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

		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("cursoOrganizador",model);
	}
	@RequestMapping(path="/crearAgenda")
	public ModelAndView crearAgenda(HttpServletRequest request){
		ModelMap model = new ModelMap();		
		if(request.getSession().getAttribute("ROL").equals("Organizador")){
			List<Especialidad> listaEspecialidades = servicioEspecialidad.traerListaDeEspecialidades();
			model.put("listaEspecialidades", listaEspecialidades);
			model.put("rol",(String)request.getSession().getAttribute("ROL"));
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
			model.put("rol", rol);
			if((horaC<horaF)&&espid!=null&&hastaD>1&&hastaD<=60&&hastaD!=null){
				LocalDate desde = LocalDate.now();
				LocalDate hasta = desde.plusDays(hastaD);

				Especialidad especialidad = servicioEspecialidad.traerEspecialidadPorId(espid);
				List <InstructorVehiculoEspecialidad> listaIvePorEsp = servicioIve.traerListaIvePorEspecialidad(especialidad);
				Asistencia asistencia = servicioAsistencia.traerAsistenciaPorNombre("En espera"); 
				if(servicioOrganizadorCrearAgenda.crearAgenda(asistencia, desde, hasta, horaC, horaF,listaIvePorEsp)!=null){
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
			model.put("rol", rol);
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
	public ModelAndView validarVehiculo(@ModelAttribute("vehiculo") Vehiculo miv,
			@RequestParam(name="estadoId")Long estadoId,
			HttpServletRequest request){
		ModelMap model = new ModelMap();
		String rol = (String)request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		if(rol.equals("Organizador")){
			model.put("rol", rol);
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
		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarvehiculoOrg",model);
	}


	@RequestMapping("/agregarEspecialidad")
	public ModelAndView agregarEspecialidad(HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			model.put("rol", rol);
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
			model.put("rol", rol);
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
			model.put("rol", rol);
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
			model.put("rol", rol);
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
			model.put("usuario", usuario);
			model.put("rol", rol);
		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarInstructorOrg",model);
	}
	@RequestMapping(path="/agregarInstructor2", method=RequestMethod.POST)
	public ModelAndView agregarInstructor2(@ModelAttribute("usuario")Usuario user,@RequestParam(name="pass2")String password2,
			HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			model.put("rol", rol);
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
						user.setInstructor(instructor);
						servicioUsuario.insertarUsuario(user);
						Long idInstructor = user.getInstructor().getId();
						if(idInstructor!=null){
							model.put("mensaje", "Instructor añadido con exito.");
							model.put("idInstructor", idInstructor);
						}else{
							model.put("error", "Hubo un problema al crear Instructor");
						}

					}
				}
			}

		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarInstructorOrg2",model);
	}
	@RequestMapping(path="/agregarVehiculoEspecialidad", method=RequestMethod.POST)
	public ModelAndView agregarInstructor3(@RequestParam(name="idIns")Long idInstructor,
			HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			model.put("rol", rol);
			if(idInstructor!=null){
				List<Especialidad> listaEsp= servicioEspecialidad.traerListaDeEspecialidades();
				model.put("listaEspecialidades", listaEsp);
				model.put("idInstructor", idInstructor);
			}else{
				model.put("error", "No hay ningun organizador seleccionado");
			}
		}	
		else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarVEOrg",model);
	}
	@RequestMapping(path="/agregarVehiculoEspecialidad-2", method=RequestMethod.POST)
	public ModelAndView agregarInstructor4(@RequestParam(name="especialidadId")Long espId,
			@RequestParam(name="idInstructor")Long idInstructor,
			HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			model.put("rol", rol);
			Especialidad esp = servicioEspecialidad.traerEspecialidadPorId(espId);
			if(idInstructor!=null&&esp!=null){
				List<Vehiculo>listaV= servicioVehiculo.obtenerVehiculosSinInstructorPorEspecialidad(esp);
				model.put("listaVehiculos", listaV);
				model.put("idInstructor", idInstructor);
				model.put("especialidadId", espId);
			}else{
				model.put("error", "Hay un error con los datos seleccionados. Verifique que seleccionó una especialidad e instructor válidos");
			}

		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarVEOrg2",model);
	}

	@RequestMapping(path="/agregarIVE", method=RequestMethod.POST)
	public ModelAndView agregarInstructor5(@RequestParam(name="idInstructor")Long idIns,
			@RequestParam(name="especialidadId")Long idEsp,
			@RequestParam(name="idVehiculo")Long idVeh,
			HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			model.put("rol", rol);
			Especialidad esp = servicioEspecialidad.traerEspecialidadPorId(idEsp);
			Vehiculo vehiculo = servicioVehiculo.buscarVehiculoPorId(idVeh);
			if(idIns!=null&&esp!=null&&vehiculo!=null){
				Instructor inst = servicioOrganizadorInstructor.buscarInstructorPorId(idIns);
				InstructorVehiculoEspecialidad ive = new InstructorVehiculoEspecialidad();
				ive.setEspecialidad(esp);
				ive.setInstructor(inst);
				ive.setVehiculo(vehiculo);
				model.put("idInstructor", idIns);
				if(servicioIve.guardarIve(ive)!=null){
					model.put("mensaje", "Instructor, Vehiculo y Especialidad guardados con éxito");
					model.put("iveGuardada", true);
				}else{
					model.put("error", "Hubo un error al guardar los datos. Por favor, intente nuevamente.");
				}
			}else{
				model.put("error", "Hay un error con los datos seleccionados. Verifique que seleccionó "
						+ "una especialidad, instructor y vehiculo válidos");
			}
		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("agregarInstructorOrg2", model);
	}
	
	@RequestMapping(path="/verCursos")
	public ModelAndView mostrarCursos(HttpServletRequest request){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		if(rol.equals("Organizador")){
			model.put("rol", rol);
			List<Curso> listaCursos = servicioOrganizadorCurso.traerListaDeCursos();
			if(listaCursos.isEmpty()){
				model.put("mensaje", "Aun no hay ningun curso creado. Añada alguno primero");
			}else{
				model.put("listaCursos", listaCursos);
			}
			
		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView("cursosOrg",model);
	}
	@RequestMapping(path="/modificarCurso/{idCurso}")
	public ModelAndView ModificarCursos(HttpServletRequest request,
			@PathVariable(value="idCurso")Long idCurso){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		String vista = "cursosOrg";
		if(rol.equals("Organizador")){
			model.put("rol", rol);
			Curso curso = servicioOrganizadorCurso.buscarCursoPorId(idCurso);
			if(curso!=null){
				model.put("curso", curso);
				List <EstadoDelCurso> estadosCurso = servicioEstadoDelCurso.traerListaDeEstadoDeLosCursos();
				model.put("estadosCursos", estadosCurso);
				
				vista = "modificarCursosOrg";
			}else{
				model.put("error","El curso seleccionado no existe.");
			}
		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView(vista,model);
	}
	@RequestMapping(path="/eliminarCurso/{idCurso}", method=RequestMethod.GET)
	public ModelAndView EliminarCurso(HttpServletRequest request,
			@PathVariable(value="idCurso")Long idCurso,
			@RequestParam(name="confir",required=false,defaultValue="noConfirmado")String confirmacion){
		String rol = (String)request.getSession().getAttribute("ROL");
		ModelMap model = new ModelMap();
		String vista = "cursosEditDeleteConfirmacionOrg";
		if(rol.equals("Organizador")){
			model.put("rol", rol);
			model.put("idC", idCurso);
			Curso curso = servicioOrganizadorCurso.buscarCursoPorId(idCurso);
			if(curso!=null){
				if(confirmacion.equals("noConfirmado")){
					List<Inscripcion> listaInscripciones = servicioInscripcion.traerInscripcionesDeUnCurso(curso);
					if(listaInscripciones.isEmpty()){
						model.put("confirmacion", "¿Esta seguro de querer eliminar el curso seleccionado: " +curso.getTitulo() +"?");
					}else{
						model.put("error", "El curso seleccionado no puede eliminarse porque hay alumnos aún cursandolo"
								+ "Si lo desea, puede cambiar su estado para que nadie más se inscriba.");
						vista = "cursosOrg";
					}
					
				}else{
					switch(confirmacion){
					case "si": servicioOrganizadorCurso.eliminarCurso(curso);
								if(servicioOrganizadorCurso.buscarCurso(curso)==null){
								model.put("mensaje", "Curso eliminado correctamente");
								}else{
									model.put("error", "Hubo un problema al eliminar el curso, intente nuevamente.");
								}
								vista= "cursosOrg";
					case "no": return new ModelAndView("redirect:/verCursos");
					
					default: return new ModelAndView("redirect:/verCursos");
					}
				}
			}else{
				model.put("error", "El curso seleccionado no existe");
			}

		}else{
			return new ModelAndView("redirect:/index");
		}
		return new ModelAndView(vista,model);
	}

}
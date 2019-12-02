package ar.edu.unlam.tallerweb1.controladores;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioNotificacion;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorUsuario {

	// La anotacion @Inject indica a Spring que en este atributo se debe setear (inyeccion de dependencias)
	// un objeto de una clase que implemente la interface servicioUsuario, dicha clase debe estar anotada como
	// @Service o @Repository y debe estar en un paquete de los indicados en applicationContext.xml
	@Inject
	private ServicioUsuario servicioUsuario;
	@Inject
	private ServicioNotificacion servicioNotificacion;
	
		@RequestMapping("/index")
		public ModelAndView index(HttpServletRequest request) {
			ModelMap model = new ModelMap();
			String rol = request.getSession().getAttribute("ROL") != null?(String) request.getSession().getAttribute("ROL"):"";
			
			model.put("rol", rol);
			String vistaindex = "index";
			if(!(rol==null||rol.isEmpty())){
				Usuario user = servicioUsuario.traerUsuarioPorId((long)request.getSession().getAttribute("ID"));
				List<Notificacion> notificaciones = servicioNotificacion.traerNotificacionesNoLeidas(user);
				model.put("notiSize", notificaciones.size());
			}
			switch(rol){
			case "Alumno": 	vistaindex="indexAlumno";
							break;
			case "Instructor": vistaindex="indexInstructor";
							break;
			case "Organizador": vistaindex="indexOrganizador";
							break;
			default: 
			}
			return new ModelAndView(vistaindex,model);
	
		}

		
	
	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es invocada por metodo http GET
	@RequestMapping("/login") //login lo asocia con el metodo iralogin
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto del tipo Usuario con key 'usuario' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		Usuario usuario = new Usuario();
		modelo.put("usuario", usuario);
		// Se va a la vista login (el nombre completo de la lista se resuelve utilizando el view resolver definido en el archivo spring-servlet.xml)
		// y se envian los datos a la misma  dentro del modelo
		return new ModelAndView("login", modelo);
	}
	
	
	

	
	// Este metodo escucha la URL validar-login siempre y cuando se invoque con metodo http POST
	// El método recibe un objeto Usuario el que tiene los datos ingresados en el form correspondiente y se corresponde con el modelAttribute definido en el
	// tag form:form
	@RequestMapping(path = "/validar-login", method = RequestMethod.POST)
	public ModelAndView validarLogin(@ModelAttribute("usuario") Usuario usuario, HttpServletRequest request) {
		ModelMap model = new ModelMap();

		// invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
		// hace una llamada a otro action a través de la URL correspondiente a ésta
		Usuario usuarioBuscado = servicioUsuario.consultarUsuario(usuario);
		if (usuarioBuscado != null) {	
			
				request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
				request.getSession().setAttribute("ID", usuarioBuscado.getId());
				switch(usuarioBuscado.getRol()){
				case "Organizador": request.getSession().setAttribute("IDROL", usuarioBuscado.getOrganizador().getId());
									break;
				case "Instructor": request.getSession().setAttribute("IDROL", usuarioBuscado.getInstructor().getId());
									break;
				case "Alumno": request.getSession().setAttribute("IDROL", usuarioBuscado.getAlumno().getId());
				}
				return new ModelAndView("redirect:/index");
				
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Nombre de Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}



	// Escucha la url /, y redirige a la URL /index, es lo mismo que si se invoca la url /index directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("redirect:/index");
	}
	
	@RequestMapping(path = "/registro")
	public ModelAndView registrarse(){
		ModelMap model = new ModelMap();
		Usuario usuario = new Usuario();
		model.put("usuario",usuario);
		return new ModelAndView("registro",model);
	}
	
	@RequestMapping(path="/realizarRegistro", method = RequestMethod.POST)
	public ModelAndView validarRegistro(@ModelAttribute("usuario") Usuario user,@RequestParam(name="pass2")String password2){
		ModelMap model = new ModelMap();
		if(user.getNombre().isEmpty()||user.getNombre()==null||user.getApellido().isEmpty()||user.getApellido()==null||
				user.getDni()==null||user.getDni().toString().length()!=8||user.getPassword().isEmpty()||user.getPassword()==null
			
				||user.getNombreDeUsuario().isEmpty()||user.getNombreDeUsuario()==null)
{
			model.put("error", "Por favor complete los campos obligatorios");
		}
		else{
			if(!(user.getPassword().equals(password2))){
				model.put("error", "Las contrase�as no coinciden");
			}else{
				Usuario usuarioBuscado = servicioUsuario.consultarUsuario(user);
				if(usuarioBuscado != null){
					model.put("error","Ya existe un usuario con esos datos");
				}else{
					user.setRol("Alumno");
					Alumno alumno = new Alumno();
					alumno.setUsuario(user);
					user.setAlumno(alumno);
					if(servicioUsuario.insertarUsuario(user)!=null){
						model.put("mensaje", "Usuario creado con exito");
					}else{
						model.put("mensaje", "Error al crear el usuario");
					}
					return new ModelAndView("login",model);
				}
			}
		}

		return new ModelAndView("registro",model);
	}

	@RequestMapping(path = "/cerrarSesion")
	public ModelAndView cerrarSesion(HttpServletRequest request){
		request.getSession().removeAttribute("ROL");
		request.getSession().removeAttribute("ID");
		return new ModelAndView("redirect:/login");
	}
	@RequestMapping(path="notificaciones", method=RequestMethod.GET)
	public ModelAndView notificaciones(HttpServletRequest request,
			@RequestParam(name="filter", required=false, defaultValue="noleidas")String filter,
			@RequestParam(name="leidas", required=false, defaultValue="false")String leidas,
			@RequestParam(name="id", required=false)Long idNotificacion){
		ModelMap model = new ModelMap();
		Long idUser = (long)request.getSession().getAttribute("ID");
		Usuario user = servicioUsuario.traerUsuarioPorId(idUser);
		model.put("rol", (String)request.getSession().getAttribute("ROL"));
		List<Notificacion> notificaciones = servicioNotificacion.traerNotificacionesNoLeidas(user);
		switch(filter){						
			case "leidas": notificaciones = servicioNotificacion.traerNotificacionesLeidas(user);
							break;
			case "todas": notificaciones = servicioNotificacion.traerTodasLasNotificaciones(user);
							break;
		}
		if(leidas=="true"){
			for(Notificacion noti:notificaciones){
				noti.setLeida(true);
				servicioNotificacion.modificarNotificacion(noti);
			}
		}
		if(idNotificacion!=null){
			Notificacion notificacionSeleccionada = servicioNotificacion.traerNotificacionPorId(idNotificacion);
			model.put("notificacion", notificacionSeleccionada);
		}
		model.put("notificaciones", notificaciones);
		return new ModelAndView("notificaciones",model);
	}

}
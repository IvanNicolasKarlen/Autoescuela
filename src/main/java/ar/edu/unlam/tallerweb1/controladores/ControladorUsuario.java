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

import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioUsuario;

@Controller
public class ControladorUsuario {

	// La anotacion @Inject indica a Spring que en este atributo se debe setear (inyeccion de dependencias)
	// un objeto de una clase que implemente la interface servicioUsuario, dicha clase debe estar anotada como
	// @Service o @Repository y debe estar en un paquete de los indicados en applicationContext.xml
	@Inject
	private ServicioUsuario servicioUsuario;
	Usuario usuario = new Usuario();

		@RequestMapping("/index")
		public ModelAndView index() {
			return new ModelAndView("index");
		}

		@RequestMapping("/indexAlumno")
		public ModelAndView indexAlumno() {
			return new ModelAndView("indexAlumno");
		}
	
	// Este metodo escucha la URL localhost:8080/NOMBRE_APP/login si la misma es invocada por metodo http GET
	@RequestMapping("/login") //login lo asocia con el metodo iralogin
	public ModelAndView irALogin() {

		ModelMap modelo = new ModelMap();
		// Se agrega al modelo un objeto del tipo Usuario con key 'usuario' para que el mismo sea asociado
		// al model attribute del form que esta definido en la vista 'login'
		
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
			//request.getSession().setAttribute("ROL", usuarioBuscado.getRol());
			return new ModelAndView("redirect:/indexAlumno");
		} else {
			// si el usuario no existe agrega un mensaje de error en el modelo.
			model.put("error", "Usuario o clave incorrecta");
		}
		return new ModelAndView("login", model);
	}


	// Escucha la url /, y redirige a la URL /index, es lo mismo que si se invoca la url /index directamente.
	@RequestMapping(path = "/", method = RequestMethod.GET)
	public ModelAndView inicio() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(path = "/registro")
	public ModelAndView registrarse(){
		ModelMap model = new ModelMap();
		
		model.put("usuario",usuario);
		return new ModelAndView("registro",model);
	}
	
	@RequestMapping(path="/realizarRegistro", method = RequestMethod.POST)
	public ModelAndView validarRegistro(@ModelAttribute("usuario") Usuario user,@RequestParam(name="pass2")String password2){
		ModelMap model = new ModelMap();
		if(!(user.getPassword().equals(password2))){
			model.put("error", "Las contrase�as no coinciden");
		}else{
			Usuario usuarioBuscado = servicioUsuario.consultarUsuario(user);
			if(usuarioBuscado != null){
				model.put("error","Ya existe un usuario con esos datos");
			}else{
				//user.setRol("Alumno");
				String mensaje = servicioUsuario.insertarUsuario(user);
				model.put("mensaje", mensaje);
				return new ModelAndView("redirect:/login",model);
			}
		}

		return new ModelAndView("registro",model);
	}

	
	
	@RequestMapping(path = "/fechas")
	public ModelAndView registrarFecha(){
		ModelMap model = new ModelMap();
		
		model.put("usuario",usuario);
		return new ModelAndView("fechas",model);
	}
	
	

	@RequestMapping(path = "/validarFechas", method = RequestMethod.POST)
	public ModelAndView validarFechas(@ModelAttribute("clase") @DateTimeFormat(pattern = "dd/MM/yyyy") Date fecha, String hora)
	{
		ModelMap modelo = new ModelMap();
		
		//servicioRegistrarClases.consultoHorariosDisponibles( fecha, usuario);
		
		return new ModelAndView("registrarClases",modelo);

	}
	
	
	@RequestMapping(path = "/horas")
	public ModelAndView registrarHora(){
		ModelMap model = new ModelMap();
		model.put("usuario",usuario);
		return new ModelAndView("horas",model);
	}
	
	@RequestMapping(path = "/cursos")
	public ModelAndView ofertaDeCursos(){
		ModelMap model = new ModelMap();
		
		model.put("usuario",usuario);
		return new ModelAndView("cursos",model);
	}
}

package ar.edu.unlam.tallerweb1.controladores;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.ViewModel.CursosViewModel;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioAlumno;
import ar.edu.unlam.tallerweb1.servicios.ServicioCurso;
import ar.edu.unlam.tallerweb1.servicios.ServicioEspecialidad;
import ar.edu.unlam.tallerweb1.servicios.ServicioEstadoInscripcion;
import ar.edu.unlam.tallerweb1.servicios.ServicioInscripcion;



@Controller
public class ControladorAlumno {

	@Inject
	private ServicioAlumno servicioAlumno;
	@Inject
	private ServicioEspecialidad servicioEspecialidad;
	@Inject
	private ServicioEstadoInscripcion servicioEstadoInscripcion;
	@Inject
	private ServicioAgenda servicioAgenda;
	@Inject
	private ServicioInscripcion servicioInscripcion;
	@Inject
	private ServicioCurso servicioCurso;
	
	
	@RequestMapping("/indexAlumno")
	public ModelAndView indexAlumno(HttpServletRequest request) {
		if(request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			ModelMap modelo = new ModelMap();
			String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
			modelo.put("rol", rol);
			
			
			//Sesion
			Long idAlumno = (Long) request.getSession().getAttribute("ID");
			Usuario usuario = servicioAlumno.buscarUsuario(idAlumno);
			
			modelo.put("usuario", usuario);
		return new ModelAndView("indexAlumno", modelo);
		}
		return new ModelAndView("redirect:/index");
	}
	
	

	
	
	/*Trae todos los cursos cargador por el Organizador*/
	@RequestMapping(path="/listadoCursos")
	public ModelAndView mostrarCursos(HttpServletRequest request){
		
	if(!request.getSession().getAttribute("ROL").equals("Alumno"))
	{
		return new ModelAndView("redirect:/index");
	}	
	
	ModelMap modelo = new ModelMap();
		
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		modelo.put("rol", rol);
		
		//Trae todo el listado de todos los cursos
		List<Curso> listaCursos =  servicioCurso.traerListaDeCursos();
		
	
		modelo.put("lista", listaCursos);
		
		return new ModelAndView("cursos",modelo);
	}

	
	
	@RequestMapping(path="/cursoElegido")
	public ModelAndView guardarCursoSeleccionado( @ModelAttribute("curso") Curso cursoElegido, HttpServletRequest request )
	{
		
		if(!request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
			
	ModelMap modelo = new ModelMap();
	String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
	modelo.put("rol", rol);
	
	//Sesion
	Long idAlumno = (Long) request.getSession().getAttribute("ID");
	
	//Traigo los datos del alumno logueado
	Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);

		//Datos del curso Elegido
		Curso curso = servicioCurso.buscarCursoPorId(cursoElegido.getId());//servicioAlumnoInscripcion

		List <Inscripcion> inscripcionCurso = servicioInscripcion.consultarSiYaSeInscribioAUnCurso(alumno.getId(), cursoElegido); //servicioAlumnoInscripcion
			
		if(inscripcionCurso.isEmpty() ) //Todavia ese curso que eligio no esta anotado
			{
			
			//Seleccionar curso
			modelo.put("cursoSeleccionado", curso);
			
			//Traer todas las fechas con disponibilidad
			TreeSet<Agenda> agendas=servicioAgenda.traerAgendasConFechasNoRepetidas(curso, alumno.getId());//servicioAlumnoAgenda

			if(agendas.isEmpty())
			{
				modelo.put("error", "No hay mas fechas disponibles para realizar una cursada");	
			}else{
				modelo.put("listaAgendas", agendas);
				 }
			
			modelo.put("mensaje", "Te ofrecemos este cronograma de clases");
			modelo.put("especialidad", curso.getEspecialidad().getTipo());
			return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
	
				
			}else{ //if inscripcionCurso.isEmpty()
				
				modelo.put("error","No podes agregar otro curso con la misma especialidad"); //Le avisa que no finalizo
				//Trae todo el listado de todos los cursos
				List<Curso> listaCursos =  servicioCurso.traerListaDeCursos();//servicioAlumnoInscripcion
				modelo.put("lista", listaCursos);
				return new ModelAndView("cursos", modelo); //Todavia no curso nada		
			}
	}
	
	
	
	
	
	
	@RequestMapping(path="/inscripcion")
	public ModelAndView inscribirAlumnoEnElCurso(
			@ModelAttribute("agendasViewModel") AgendasViewModel agendasViewModel,
			HttpServletRequest request )
	{
		
			if(!request.getSession().getAttribute("ROL").equals("Alumno"))
			{
				return new ModelAndView("redirect:/index");
			}
			ModelMap modelo = new ModelMap();
			
			String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
			modelo.put("rol", rol);
			
			//Sesion
			Long idAlumno = (Long) request.getSession().getAttribute("ID");
			//Traigo los datos del alumno logueado
			Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
			
			//Datos del curso Elegido
			Curso curso = servicioCurso.buscarCursoPorId(agendasViewModel.getIdCurso());//servicioAlumnoInscripcion
			
			List <Inscripcion> inscripcionCurso = servicioInscripcion.consultarSiYaSeInscribioAUnCurso(alumno.getId(), curso);//servicioAlumnoInscripcion
							
	if(inscripcionCurso.isEmpty() ) //Todavia ese curso que eligio no esta anotado
		{
		
		//Consultar que no le hayan ocupado esas fechas
		Boolean resultado = servicioAgenda.constatarQueNadieSeAnotaraEnLasFechasAsignadas(agendasViewModel.getIdAgendasDepurado(),curso); //servicioAlumnoAgenda
		
		//Si las fechas que me asignaron no fueron ocupadas
			if(resultado == true)
			{
				//Anotarme
					
				
		servicioInscripcion.guardarInscripcionEnLaAgendaYEnInscripcion(alumno, curso,agendasViewModel.getIdAgendasDepurado());//servicioAlumnoInscripcion
		modelo.put("mensaje", "Tu inscripcion se realizo con exito");
			
			}
		
			else{ //fin if resultado == true
						
				//Buscarle otras fechas
						
				//Traer todas las fechas con disponibilidad    
				TreeSet<Agenda> agendas= servicioAgenda.traerAgendasConFechasNoRepetidas(curso,idAlumno);//servicioAlumnoAgenda

					if(agendas.isEmpty())
					{
						modelo.put("error", "No hay mas fechas disponibles para realizar una cursada");
								
					}else{
						modelo.put("listaAgendas", agendas);
						modelo.put("listaAgendassize", agendas.size());	
						 }
						
				modelo.put("mensaje", "Una de las clases ha sido ocupada. Te buscamos clases nuevas");
				modelo.put("cursoSeleccionado", curso);
	
				return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
						
			   	 }	
											
							
		}	/////////////////////////if inscripcionCurso.isEmpty()	linea 173
		else{

				modelo.put("error","No podes agregar otro curso con la misma especialidad"); //Le avisa que no finalizo
				
				//Trae todo el listado de todos los cursos
				List<Curso> listaCursos =  servicioCurso.traerListaDeCursos();//servicioAlumnoInscripcion
					
				modelo.put("lista", listaCursos);
				return new ModelAndView("cursos", modelo); //Todavia no curso nada
					
			}
					
			
		modelo.put("curso2", agendasViewModel.getIdCurso());
		modelo.put("agendas2", agendasViewModel.getIdAgendasDepurado());
		modelo.put("agendas2size", agendasViewModel.getIdAgendasDepurado().size());
		
		return new ModelAndView("inscripcionExitosa",modelo); 
		
		
	}
	
	

	/*Muestra todas las clases que esta realizando todas juntas*/
	@RequestMapping(path="/listadoFechas")
	public ModelAndView DiasDeCursada(HttpServletRequest request){
		
		if(!request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
		ModelMap modelo = new ModelMap();
			
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		modelo.put("rol", rol);
		
				//Sesion
				Long idAlumno = (Long) request.getSession().getAttribute("ID");
				
				//Traigo los datos del alumno logueado
				Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);

				//Busco el id del estado que dice "Cursando"
				 EstadoInscripcion estado = servicioEstadoInscripcion.buscarEstadoCursando();//alumnoEstadoDao
					
				List<Inscripcion> cursando = servicioInscripcion.saberSiEstaRealizandoAlgunCurso(alumno.getId(), estado);//servicioAlumnoInscripcion
				
				if(cursando.isEmpty())
				{
					modelo.put("num", cursando.size());
				}else{
					
					//Busco el id del estado que dice "Cursando"
					 EstadoInscripcion estadoCursando = servicioEstadoInscripcion.buscarEstadoCursando();
					List<Agenda> listadoDeClases = servicioAgenda.traerTodasLasClasesQueEstaAnotado(alumno.getId(), estadoCursando);
						
					modelo.put("num", cursando.size());
					modelo.put("listadoClases", listadoDeClases);
					modelo.put("listaCursos", cursando);
					 }
				
				return new ModelAndView("fechaYHorasDeCadaCurso", modelo);
	
	}
	
	
	
	/*Trae solo las clases de la especialidad que selecciono en los filtros*/
	@RequestMapping(path="/clasesDelCurso")
	public ModelAndView VistaDePruebas(HttpServletRequest request, @RequestParam(name="id", required=false) Long idEspecialidad ){
		
		if(!request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
		ModelMap modelo = new ModelMap();
		
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		modelo.put("rol", rol);
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
		//Traigo los datos del alumno logueado
		Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion inscripcionEstadoCursando = servicioEstadoInscripcion.buscarEstadoCursando();
		
		
		//Traer las clases del filtro elegido Agenda
		List<Agenda> clasesDeUnSoloCurso = servicioAgenda.traerTodasLasClasesDeUnaSolaEspecialidad(idEspecialidad,alumno.getId(),inscripcionEstadoCursando);//servicioAlumnoInscripcion
		
		
		//Traer solo los filtros Inscripcion
		List<Inscripcion> listadoDeFiltros = servicioInscripcion.traerLosCursosEnQueSeEncuentraAnotado(alumno.getId(), inscripcionEstadoCursando);
		
		List<Agenda> clasesCursando= servicioAgenda.traerClasesQueEsteCursando(clasesDeUnSoloCurso);
		
		
		/*Por si cambia el id de la url*/
		if(clasesDeUnSoloCurso.isEmpty() || idEspecialidad.equals(null)){
			modelo.put("error", "No estas realizando ese curso");
			modelo.put("botonFinalizarAnulado", "Anulado");
		}
		
		/*Si no tiene curso, se le pedira que se registre a alguno*/
		if(listadoDeFiltros.isEmpty())
		{
			modelo.put("num", listadoDeFiltros.size());
		}else{
			/*Sino, se le mostrara las clases que eligio del filtro*/
			modelo.put("num", listadoDeFiltros.size());
			/*Los cursos que esta realizando, para poder eliminarlos*/
			modelo.put("listaCursos", listadoDeFiltros);
			
			modelo.put("listadoDeClases", clasesDeUnSoloCurso);
			modelo.put("cantDeClasesCursando", clasesCursando.size());
		
			 }
		
		
		return new ModelAndView("clasesElegidasEnElFiltroDeAlumno", modelo);
	}

	
	/*Vista que pregunta si esta seguro eliminar la clase seleccionada*/
	@RequestMapping(path="/mostrarAlerta")
	public ModelAndView mostrarSiQuiereEliminarUnaClaseOno(HttpServletRequest request, @ModelAttribute("agenda") AgendasViewModel agendasViewModel ){
		
		
		if(!request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
		ModelMap modelo = new ModelMap();
		
		
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		modelo.put("rol", rol);
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
	
		//Traigo los datos del alumno logueado
		Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
		
		/*Si no envio una clase para eliminar, entonces quiere eliminar un curso*/
		try{
		agendasViewModel.getIdAgendaSeleccionada().equals(null);
		
	
		}catch(NullPointerException e){
		
			/*Si no quiere eliminar una clase*/
			/*Mostramos el curso a eliminar*/
			Inscripcion alumnoEnCurso = servicioInscripcion.buscarCursoAEliminar(agendasViewModel.getIdCurso(), alumno.getId());
			
			modelo.put("nombreEspecialidad", alumnoEnCurso);
			modelo.put("mensaje", "¿Deseas eliminar este curso?");
			
			modelo.put("bandera", 1);
		}
			
		
			/*Si no envio un curso para eliminar, entonces quiere eliminar una clase*/
			try{
		agendasViewModel.getIdCurso().equals(null);
			
			}catch(NullPointerException e)
			{
			//Traer la clase que selecciono para eliminar
			Agenda agenda = servicioAgenda.traerClaseQueQuiereEliminar(agendasViewModel.getIdAgendaSeleccionada(),alumno.getId());
			
			modelo.put("listadoClases", agendasViewModel.getIdAgendas());
			modelo.put("curso", agendasViewModel.getIdCurso());
			modelo.put("agenda", agenda);
			modelo.put("mensaje", "¿Deseas eliminar esta clase?");
			
			
			modelo.put("bandera", 2);
			}
	
			return new ModelAndView("alertaEliminar", modelo);
	}

	
	
	
	@RequestMapping(path="/finalizarCursoAlerta")
	public ModelAndView consultarSiQuiereFinalizarONo(HttpServletRequest request, @ModelAttribute("agenda") AgendasViewModel agendasViewModel ){
		if(!request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
		ModelMap modelo = new ModelMap();
		
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		modelo.put("rol", rol);
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
		//Traigo los datos del alumno logueado
		Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
		
				Inscripcion inscripcionBuscada = servicioInscripcion.buscarInscripcion(alumno.getId(), agendasViewModel.getIdCurso());	
					
				modelo.put("mensaje", "¿Estas seguro?");
				modelo.put("inscripcion", inscripcionBuscada);
					
				modelo.put("bandera", 3);
				
	return new ModelAndView("alertaEliminar", modelo);
	}
	
	
	
	
	/*Confirmado el metodo de Eliminar la clase seleccionada*/
	@RequestMapping(path="/eliminarClase")
	public ModelAndView eliminarUnaClase(HttpServletRequest request, @ModelAttribute("agenda") AgendasViewModel agendasViewModel ){
		
		if(!request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
		ModelMap modelo = new ModelMap();
	
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		modelo.put("rol", rol);
		
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		//Traigo los datos del alumno logueado
		Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
		
		/*Si no envio una clase para eliminar, entonces quiere eliminar un curso*/
		try{
		
			agendasViewModel.getIdAgendaSeleccionada().equals(null);
		
		}catch(NullPointerException e){
		
			servicioInscripcion.eliminarInscripcionDelAlumnoYSusClasesDelCurso(agendasViewModel.getIdCurso(),alumno.getId());
			modelo.put("mensaje", "Se te ha eliminado del curso correctamente");
		}
		
		
		/*Si no envio un curso para eliminar, entonces quiere eliminar una clase*/
		try{
		
			agendasViewModel.getIdCurso().equals(null);

		}catch(NullPointerException e){
			
			
			/*verifico que no se pueda eliminar la agenda con menos de
			 * 2 dias de anticipacion*/
			Boolean result= servicioAgenda.verificarUnaAgendaSePuedaEliminar(agendasViewModel.getIdAgendaSeleccionada());
			
			if(result.equals(true)){
			
			//Eliminar esta clase
			 servicioAgenda.eliminarClaseDeLaAgenda(agendasViewModel.getIdAgendaSeleccionada(),alumno.getId());
			 modelo.put("mensaje", "Se ha eliminado la clase seleccionada correctamente");
			}else{
				modelo.put("mensaje", "No se puede eliminar la clase con menos de dos dias de anticipacion");
			}
		}
		
		
		return new ModelAndView("Eliminada", modelo);
	}
	
	
	
	
	@RequestMapping(path="/finalizado")
	public ModelAndView finalizoElCurso(
			@ModelAttribute("agenda") AgendasViewModel agendasViewModel,
			HttpServletRequest request )
	{
	
		if(!request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
		
			ModelMap modelo = new ModelMap();
			String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
			modelo.put("rol", rol);

			//Sesion
			Long idAlumno = (Long) request.getSession().getAttribute("ID");
			//Traigo los datos del alumno logueado
			Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
			
			
			try{
				
				agendasViewModel.getIdAgendaSeleccionada().equals(null);
			
			}catch(NullPointerException e){
			
			servicioInscripcion.finalizarCursoDelAlumno(alumno.getId(), agendasViewModel.getIdCurso());
			modelo.put("mensaje", "Has finalizado el curso de manera exitosa");
				
			}

			return new ModelAndView("Eliminada", modelo);
		
	}	
	
	
	
	
	@RequestMapping(path="/historial")
	public ModelAndView historialDeClases(HttpServletRequest request )
	{
		ModelMap modelo = new ModelMap();
		if(request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			
			String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
			modelo.put("rol", rol);
			
			//Sesion
			Long idAlumno = (Long) request.getSession().getAttribute("ID");
			//Traigo los datos del alumno logueado
			Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
		
			EstadoInscripcion finalizado = servicioEstadoInscripcion.buscarEstadoFinalizado();
			
			List<Inscripcion> cursando = servicioInscripcion.saberSiEstaRealizandoAlgunCurso(alumno.getId(), finalizado);//servicioAlumnoInscripcion
			
			if(cursando.isEmpty())
			{
				modelo.put("num", cursando.size());
			}else{
				
				//Busco el id del estado que dice "Finalizado"
				 EstadoInscripcion estado = servicioEstadoInscripcion.buscarEstadoFinalizado();
				List<Agenda> listadoDeClases = servicioAgenda.traerTodasLasClasesQueEstaAnotado(alumno.getId(), estado);
					
				modelo.put("num", cursando.size());
				modelo.put("listadoClases", listadoDeClases);
				modelo.put("listaCursos", cursando);
				 }
		

			return new ModelAndView("historial", modelo);
			
		}
		
		return new ModelAndView("redirect:/index");
	}	
	
	
	
	/*Trae solo las clases de la especialidad que selecciono en los filtros*/
	@RequestMapping(path="/mostrarclasesCurso")
	public ModelAndView ClasesDeUnSoloCurso(HttpServletRequest request, @RequestParam(name="id") Long idEspecialidad ){
		
		if(!request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
		ModelMap modelo = new ModelMap();
		
		
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		modelo.put("rol", rol);
		
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
		//Traigo los datos del alumno logueado
		Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
		
		//Busco el id del estado que dice "Finalizado"
		 EstadoInscripcion estadoFinalizado = servicioEstadoInscripcion.buscarEstadoFinalizado();
		
		//Traer las clases del filtro elegido Agenda
		List<Agenda> clasesDeUnSoloCurso = servicioAgenda.traerTodasLasClasesDeUnaSolaEspecialidad(idEspecialidad,alumno.getId(), estadoFinalizado);//servicioAlumnoInscripcion
		
		//Traer solo los filtros Inscripcion
		List<Inscripcion> listadoDeFiltros = servicioInscripcion.traerLosCursosEnQueSeEncuentraAnotado(alumno.getId(),estadoFinalizado);
		
		
		/*Por si cambia el id de la url*/
		if(clasesDeUnSoloCurso.isEmpty() || idEspecialidad.equals(null)){
			modelo.put("error", "No estas realizando ese curso");
			modelo.put("botonFinalizarAnulado", "Anulado");
		}
		
		/*Si no tiene curso, se le pedira que se registre a alguno*/
		if(listadoDeFiltros.isEmpty())
		{
			modelo.put("num", listadoDeFiltros.size());
		}else{
				/*Sino, se le mostrara las clases que eligio del filtro*/
			modelo.put("num", listadoDeFiltros.size());
			/*Los cursos que esta realizando, para poder eliminarlos*/
			modelo.put("listaCursos", listadoDeFiltros);
			 }
		
		modelo.put("listadoDeClases", clasesDeUnSoloCurso);

		return new ModelAndView("HistorialclasesElegidasEnElFiltroDeAlumno", modelo);
	
	}	
	
	
	

	// editar agenda
	
	@RequestMapping(path="/seleccionarAgenda")
	public ModelAndView editarClase(
			@ModelAttribute("agendasViewModel") AgendasViewModel agendasViewModel,
			HttpServletRequest request )
	{
		if(!request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
			ModelMap modelo = new ModelMap();
			String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
			modelo.put("rol", rol);
			
			
			//Sesion
			Long idAlumno = (Long) request.getSession().getAttribute("ID");
			//Traigo los datos del alumno logueado
			Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
			//Datos del curso Elegido
			Curso curso = servicioCurso.buscarCursoPorId(agendasViewModel.getIdCurso());
			
			List <Inscripcion> inscripcionCurso = servicioInscripcion.consultarSiYaSeInscribioAUnCurso(alumno.getId(), curso);
							
	if(inscripcionCurso.isEmpty() ) //Todavia ese curso que eligio no esta anotado
		{
						
		
		List<Long> idAgendasDepurado = agendasViewModel.getIdAgendasDepurado();
		
		//Consultar que no le hayan ocupado esas fechas
		Boolean resultado = servicioAgenda.constatarQueNadieSeAnotaraEnLasFechasAsignadas(idAgendasDepurado,curso);
		
		//Si las fechas que me asignaron no fueron ocupadas
			if(resultado == true)
			{
			//Datos del curso Elegido
			List <Agenda> datosAgendas= servicioAgenda.buscarAgendasElegidas( agendasViewModel.getIdAgendasDepurado(),  curso);
					
			modelo.put("mensaje", "Seleccione la agenda que desee modificar");
			modelo.put("listaAgendas", datosAgendas);
			modelo.put("cursoSeleccionado", curso);

			}
		
			else{ //fin if resultado == true
						
				//Buscarle otras fechas
						
				//Traer todas las fechas con disponibilidad    
				TreeSet<Agenda> agendas= servicioAgenda.traerAgendasConFechasNoRepetidas(curso, idAlumno);

					if(agendas.isEmpty())
					{
						modelo.put("error", "No hay mas fechas disponibles para realizar una cursada");
								
					}else{
						modelo.put("listaAgendas", agendas);
						modelo.put("listaAgendassize", agendas.size());	
						 }
						
				modelo.put("mensaje", "Una de las clases ha sido ocupada. Te buscamos clases nuevas");
				modelo.put("cursoSeleccionado", curso);

				return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
						
			   	 }	
											
							
		}	/////////////////////////if inscripcionCurso.isEmpty()	linea 173
		else{

				modelo.put("error","No podes agregar otro curso con la misma especialidad"); //Le avisa que no finalizo
				
				//Trae todo el listado de todos los cursos
				List<Curso> listaCursos =  servicioCurso.traerListaDeCursos();
					
				modelo.put("lista", listaCursos);
				return new ModelAndView("cursos", modelo); //Todavia no curso nada
					
			}
					
//			
		modelo.put("curso2", agendasViewModel.getIdCurso());
		modelo.put("agendas2size", agendasViewModel.getIdAgendasDepurado().size());
		
		return new ModelAndView("seleccionarAgenda",modelo); 
		
	}

	
	
	
	@RequestMapping(path="/agendasAlternativas")
	public ModelAndView mostrarAgendasAlternativas(
			@ModelAttribute("agendasViewModel") AgendasViewModel agendasViewModel,
			HttpServletRequest request )
	{
		if(!request.getSession().getAttribute("ROL").equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
			ModelMap modelo = new ModelMap();
			String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
			modelo.put("rol", rol);
			
			//Sesion
			Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
			//Traigo los datos del alumno logueado
			Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
		
			//Datos del curso Elegido
			Curso curso = servicioCurso.buscarCursoPorId(agendasViewModel.getIdCurso());
			
			List <Inscripcion> inscripcionCurso = servicioInscripcion.consultarSiYaSeInscribioAUnCurso(alumno.getId(), curso);
							
	if(inscripcionCurso.isEmpty() ) //Todavia ese curso que eligio no esta anotado
		{
						
		//Consultar que no le hayan ocupado esas fechas
		Boolean resultado = servicioAgenda.constatarQueNadieSeAnotaraEnLasFechasAsignadas(agendasViewModel.getIdAgendasDepurado(),curso);
		
		//Si las fechas que me asignaron no fueron ocupadas
			if(resultado == true)
			{
				//traer agendas disponibles diferentes a las fechas seleccionadas 
				List<Agenda> agendasAlternativas=servicioAgenda.traerAgendasParaReemplazarOtra(curso, agendasViewModel.getIdAgendasDepurado(), alumno.getId());

				modelo.put("mensaje", "Elige la nueva agenda");
				modelo.put("listaAgendas", agendasViewModel.getIdAgendasDepurado());
				modelo.put("agendasAlternativas", agendasAlternativas);
				modelo.put("cursoSeleccionado", curso);
				modelo.put("agen",agendasViewModel.getIdAgendaEditar());	

			}
		
			else{ //fin if resultado == true
						
				//Buscarle otras fechas
						
				//Traer todas las fechas con disponibilidad    
				TreeSet<Agenda> agendas= servicioAgenda.traerAgendasConFechasNoRepetidas(curso, idAlumno);

					if(agendas.isEmpty())
					{
						modelo.put("error", "No hay mas fechas disponibles para realizar una cursada");
								
					}else{
						modelo.put("listaAgendas", agendas);
						modelo.put("listaAgendassize", agendas.size());	
						 }
						
				modelo.put("mensaje", "Una de las clases ha sido ocupada. Te buscamos clases nuevas");
				modelo.put("cursoSeleccionado", curso);

				return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
						
			   	 }	
											
							
		}	/////////////////////////if inscripcionCurso.isEmpty()	linea 173
		else{

				modelo.put("error","No podes agregar otro curso con la misma especialidad"); //Le avisa que no finalizo
				
				//Trae todo el listado de todos los cursos
				List<Curso> listaCursos =  servicioCurso.traerListaDeCursos();
					
				modelo.put("lista", listaCursos);
				return new ModelAndView("cursos", modelo); //Todavia no curso nada
					
			}
					
			
		modelo.put("curso2", agendasViewModel.getIdCurso());
		modelo.put("agendas2", agendasViewModel.getIdAgendasDepurado());
		modelo.put("agendas2size", agendasViewModel.getIdAgendasDepurado().size());
		
		return new ModelAndView("agendasAlternativas",modelo); 
	
	}

	

@RequestMapping(path="/modificarAgenda")
public ModelAndView modificarAgenda(
		@ModelAttribute("agendasViewModel") AgendasViewModel agendasViewModel,
		HttpServletRequest request )
{
	
	if(!request.getSession().getAttribute("ROL").equals("Alumno"))
	{
		return new ModelAndView("redirect:/index");
	}
		ModelMap modelo = new ModelMap();
		
		String rol = request.getSession().getAttribute("ROL")!=null?(String)request.getSession().getAttribute("ROL"):null;
		modelo.put("rol", rol);
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		//Traigo los datos del alumno logueado
		Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
		
		//Datos del curso Elegido
		Curso curso = servicioCurso.buscarCursoPorId(agendasViewModel.getIdCurso());
		
		List <Inscripcion> inscripcionCurso = servicioInscripcion.consultarSiYaSeInscribioAUnCurso(alumno.getId(), curso);
						
if(!inscripcionCurso.isEmpty() ) //ese curso que eligio  esta anotado
	{
		modelo.put("error","No podes agregar otro curso con la misma especialidad"); //Le avisa que no finalizo
		
		//Trae todo el listado de todos los cursos
		List<Curso> listaCursos =  servicioCurso.traerListaDeCursos();
			
		modelo.put("lista", listaCursos);
		return new ModelAndView("cursos", modelo); //Todavia no curso nada
	
	}
					
	//Consultar que no le hayan ocupado esas fechas
	Boolean resultado = servicioAgenda.constatarQueNadieSeAnotaraEnLasFechasAsignadas(agendasViewModel.getIdAgendasDepurado(),curso);
	
	//Si las fechas que me asignaron fueron ocupadas
		if(resultado.equals(false))
		{
			//Buscarle otras fechas
					
			//Traer todas las fechas con disponibilidad    
			TreeSet<Agenda> agendas= servicioAgenda.traerAgendasConFechasNoRepetidas(curso, idAlumno);

				if(agendas.isEmpty())
				{
					modelo.put("error", "No hay mas fechas disponibles para realizar una cursada");
							
				}else{
					modelo.put("listaAgendas", agendas);
					 }
					
			modelo.put("mensaje", "Una de las clases ha sido ocupada. Te buscamos clases nuevas");
			modelo.put("cursoSeleccionado", curso);

			return new ModelAndView("fechasAlumnoEnAgenda",modelo); 

		}

			List <Long> idAgendas= servicioAgenda.reemplazarAgenda(agendasViewModel.getIdAgendaSeleccionada(),
					agendasViewModel.getIdAgendasDepurado(),agendasViewModel.getIdAgendaEditar());
			List <Agenda> datosAgendas= servicioAgenda.buscarAgendasElegidas( idAgendas,  curso);

			modelo.put("mensaje", "Agenda modificada con exito");
			modelo.put("listaAgendas", datosAgendas);
			modelo.put("cursoSeleccionado", curso);
			modelo.put("agen",agendasViewModel.getIdAgendaSeleccionada());	

	return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
	
}
	


@RequestMapping(path="/seleccionarClaseAgregar")
public ModelAndView seleccionarClaseAgregar(
		@ModelAttribute("agendasViewModel") AgendasViewModel agendasViewModel,
		HttpServletRequest request )
{
	
	if(!request.getSession().getAttribute("ROL").equals("Alumno"))
	{
		return new ModelAndView("redirect:/index");
	}
	ModelMap modelo = new ModelMap();
		//Datos del curso Elegido
		Curso curso = servicioCurso.buscarCursoPorId(agendasViewModel.getIdCurso());
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
		//Traigo los datos del alumno logueado
		Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
		
		//traer agendas disponibles diferentes a las fechas seleccionadas 
		List<Agenda> agendasAlternativas=servicioAgenda.traerAgendasParaReemplazarOtra(curso, agendasViewModel.getIdAgendasDepurado(), alumno.getId());
	
	

		Inscripcion inscripcion = servicioInscripcion.buscarInscripcion(alumno.getId(),curso.getId());
		
		modelo.put("listaAgendas", agendasAlternativas);
		modelo.put("mensaje", "Selecciona la agenda que desees agregar");
		modelo.put("cursoSeleccionado", curso);
		modelo.put("inscripcion", inscripcion);
		
		return new ModelAndView("seleccionarClaseAgregar",modelo);
			
}


@RequestMapping(path="/agregarClase")
public ModelAndView agregarClase(
		@ModelAttribute("agendasViewModel") AgendasViewModel agendasViewModel,
		HttpServletRequest request )
{

		ModelMap modelo = new ModelMap();
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
		//Traigo los datos del alumno logueado
		Alumno alumno = servicioAlumno.buscarAlumno(idAlumno);
		

		//Datos del curso Elegido
		Curso curso = servicioCurso.buscarCursoPorId(agendasViewModel.getIdCurso());//servicioAlumnoInscripcion
									
	//Consultar que no le hayan ocupado esas fechas
	Boolean resultado = servicioAgenda.constatarQueNadieSeAnotaraEnLasFechasAsignadas(agendasViewModel.getIdAgendasDepurado(),curso); //servicioAlumnoAgenda
	
	//Si las fechas que me asignaron no fueron ocupadas
		if(resultado == true)
		{
			//Anotarme
		servicioInscripcion.agregarInscripcion(alumno,curso,agendasViewModel.getIdAgendaEditar());//servicioAlumnoInscripcion
		modelo.put("mensaje", "La clase se agregó con éxito");
			
		}
	
		else{ //fin if resultado == true
					
			//Buscarle otras fechas
					
			//Traer todas las fechas con disponibilidad    
			TreeSet<Agenda> agendas= servicioAgenda.traerAgendasConFechasNoRepetidas(curso, idAlumno);//servicioAlumnoAgenda

				if(agendas.isEmpty())
				{
					modelo.put("error", "No hay mas fechas disponibles para agregar");
							
				}else{
					modelo.put("listaAgendas", agendas);
					modelo.put("listaAgendassize", agendas.size());	
					 }
					
			modelo.put("mensaje", "Una de las clases ha sido ocupada. Te buscamos clases nuevas");
			modelo.put("cursoSeleccionado", curso);

			return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
					
		   	 }	
										

				
		
	modelo.put("curso2", agendasViewModel.getIdCurso());
	modelo.put("agendas2", agendasViewModel.getIdAgendasDepurado());
	modelo.put("agendas2size", agendasViewModel.getIdAgendasDepurado().size());
	
	return new ModelAndView("inscripcionExitosa",modelo); 
	
}



	


/*****************MOCK*****************/

public void setServicioCurso(ServicioCurso servicioCurso) {
	this.servicioCurso = servicioCurso;
}

			
}	
		
		
	
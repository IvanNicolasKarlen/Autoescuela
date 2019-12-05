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
		
		String rol = request.getSession().getAttribute("ROL")!= null?(String) request.getSession().getAttribute("ROL"):"";
		if(!rol.equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
		
		ModelMap modelo = new ModelMap();
		modelo.put("rol", rol);
			
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		Usuario usuario = servicioAlumno.buscarUsuario(idAlumno);
			
		modelo.put("usuario", usuario);
		return new ModelAndView("indexAlumno", modelo);
	}
		
	
	

	
	
	/*Trae todos los cursos cargador por el Organizador*/
	@RequestMapping(path="/listadoCursos")
	public ModelAndView mostrarCursos(HttpServletRequest request){
		
		
		String rol = (String)request.getSession().getAttribute("ROL");
	if(!rol.equals("Alumno"))
	{
		return new ModelAndView("redirect:/index");
	}	
	
		ModelMap modelo = new ModelMap();
		
		modelo.put("rol", rol);
		
		//Trae todo el listado de todos los cursos
		List<Curso> listaCursos =  servicioCurso.traerListaDeCursos();
		
		modelo.put("lista", listaCursos);
		
		return new ModelAndView("cursos",modelo);
	}

	
	
	
	
	@RequestMapping(path="/cursoElegido")
	public ModelAndView guardarCursoSeleccionado( @ModelAttribute("curso") Curso cursoElegido, HttpServletRequest request )
	{
		
		String rol = (String)request.getSession().getAttribute("ROL");
		if(!rol.equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}	
		ModelMap modelo = new ModelMap();
		modelo.put("rol", rol);
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
	
		//Datos del curso Elegido
		Curso curso = servicioCurso.buscarCursoPorId(cursoElegido.getId());//servicioAlumnoInscripcion
	
		List <Inscripcion> inscripcionCurso = servicioInscripcion.consultarSiYaSeInscribioAUnCurso(idAlumno, cursoElegido); //servicioAlumnoInscripcion
			
		if(inscripcionCurso.isEmpty() ) //Todavia ese curso que eligio no esta anotado
			{
			
				//Seleccionar curso
				modelo.put("cursoSeleccionado", curso);
			
				//Traer todas las fechas con disponibilidad
				TreeSet<Agenda> agendas=servicioAgenda.traerAgendasConFechasNoRepetidas(curso, idAlumno);//servicioAlumnoAgenda

				if(agendas.isEmpty())
				{
					modelo.put("error", "No hay mas fechas disponibles para realizar una cursada");	
				}else{
						modelo.put("listaAgendas", agendas);
					 }
			
				modelo.put("mensaje", "Te ofrecemos este cronograma de clases");
				return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
	
				
			} //if inscripcionCurso.isEmpty()
				
				modelo.put("error","No podes agregar otro curso con la misma especialidad"); //Le avisa que no finalizo
				//Trae todo el listado de todos los cursos
				List<Curso> listaCursos =  servicioCurso.traerListaDeCursos();//servicioAlumnoInscripcion
				modelo.put("lista", listaCursos);
				return new ModelAndView("cursos", modelo); //Todavia no curso nada		
			
	}
	
	
	
	
	
	@RequestMapping(path="/inscripcion")
	public ModelAndView inscribirAlumnoEnElCurso(
			@ModelAttribute("agendasViewModel") AgendasViewModel agendasViewModel,
			HttpServletRequest request )
	{
		
		String rol = (String)request.getSession().getAttribute("ROL");
		if(!rol.equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}		
	ModelMap modelo = new ModelMap();
		
	modelo.put("rol", rol);
			
	//Sesion
	Long idAlumno = (Long) request.getSession().getAttribute("ID");
			
	//Datos del curso Elegido
	Curso curso = servicioCurso.buscarCursoPorId(agendasViewModel.getIdCurso());//servicioAlumnoInscripcion
			
	List <Inscripcion> inscripcionCurso = servicioInscripcion.consultarSiYaSeInscribioAUnCurso(idAlumno, curso);//servicioAlumnoInscripcion
							
	if(inscripcionCurso.isEmpty() ) //Todavia ese curso que eligio no esta anotado
		{
		
			//Consultar que no le hayan ocupado esas fechas
			Boolean resultado = servicioAgenda.constatarQueNadieSeAnotaraEnLasFechasAsignadas(agendasViewModel.getIdAgendasDepurado(),curso); //servicioAlumnoAgenda
		
			//Si las fechas que me asignaron no fueron ocupadas
			if(resultado.equals(false))
			{
				
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
				
			//Anotarme
				
			servicioInscripcion.guardarInscripcionEnLaAgendaYEnInscripcion(idAlumno, curso,agendasViewModel.getIdAgendasDepurado());//servicioAlumnoInscripcion
			modelo.put("mensaje", "Tu inscripcion se realizo con exito");
			modelo.put("curso2", agendasViewModel.getIdCurso());
			modelo.put("agendas2", agendasViewModel.getIdAgendasDepurado());
			modelo.put("agendas2size", agendasViewModel.getIdAgendasDepurado().size());
				
			return new ModelAndView("inscripcionExitosa",modelo); 			
		}	/////////////////////////if inscripcionCurso.isEmpty()	linea 173
		
			
		//Trae todo el listado de todos los cursos
		List<Curso> listaCursos =  servicioCurso.traerListaDeCursos();//servicioAlumnoInscripcion
	
		modelo.put("error","No podes agregar otro curso con la misma especialidad"); //Le avisa que no finalizo
		modelo.put("lista", listaCursos);
		
		return new ModelAndView("cursos", modelo); //Todavia no curso nada					
	}
	
	

	/*Muestra todas las clases que esta realizando todas juntas*/
	@RequestMapping(path="/listadoFechas")
	public ModelAndView DiasDeCursada(HttpServletRequest request){
		
		String rol = (String)request.getSession().getAttribute("ROL");
		if(!rol.equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
		ModelMap modelo = new ModelMap();
			
		modelo.put("rol", rol);
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");

		//Busco el id del estado que dice "Cursando"
		EstadoInscripcion estadoCursando = servicioEstadoInscripcion.buscarEstadoCursando();
				 
		List<Inscripcion> cursando = servicioInscripcion.saberSiEstaRealizandoAlgunCurso(idAlumno, estadoCursando);//servicioAlumnoInscripcion
				
		if(cursando.isEmpty())
		{
			modelo.put("num", cursando.size());
					
			return new ModelAndView("fechaYHorasDeCadaCurso", modelo);
		}
					
				
		List<Agenda> listadoDeClases = servicioAgenda.traerTodasLasClasesQueEstaAnotado(idAlumno, estadoCursando);
						
		
		modelo.put("num", cursando.size());
		modelo.put("listadoClases", listadoDeClases);
		modelo.put("listaCursos", cursando);
					 
		return new ModelAndView("fechaYHorasDeCadaCurso", modelo);
				
	}
	
	
	
	/*Trae solo las clases de la especialidad que selecciono en los filtros*/
	@RequestMapping(path="/clasesDelCurso")
	public ModelAndView ClasesElegidasEnElFiltro(HttpServletRequest request, @RequestParam(name="id", required=false) Long idEspecialidad ){
		
		String rol = (String)request.getSession().getAttribute("ROL");
		if(!rol.equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}	
		ModelMap modelo = new ModelMap();
		
		modelo.put("rol", rol);
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
	
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion inscripcionEstadoCursando = servicioEstadoInscripcion.buscarEstadoCursando();
		
		
		//Traer las clases del filtro elegido Agenda
		List<Agenda> clasesDeUnSoloCurso = servicioAgenda.traerTodasLasClasesDeUnaSolaEspecialidad(idEspecialidad,idAlumno,inscripcionEstadoCursando);//servicioAlumnoInscripcion
		
		
		//Traer solo los filtros Inscripcion
		List<Inscripcion> listadoDeFiltros = servicioInscripcion.traerLosCursosEnQueSeEncuentraAnotado(idAlumno, inscripcionEstadoCursando);
		
		//Clases con el estado ocupado para poder mostrar el boton Agregar Clases
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
			
			return new ModelAndView("clasesElegidasEnElFiltroDeAlumno", modelo);
		}
				
		/*Sino, se le mostrara las clases que eligio del filtro*/
		modelo.put("num", listadoDeFiltros.size());
		/*Los cursos que esta realizando, para poder eliminarlos*/
		modelo.put("listaCursos", listadoDeFiltros);
		modelo.put("listadoDeClases", clasesDeUnSoloCurso);
		modelo.put("cantDeClasesCursando", clasesCursando.size());
			 
		return new ModelAndView("clasesElegidasEnElFiltroDeAlumno", modelo);
	}

	
	
	
	/*Vista que pregunta si esta seguro eliminar la clase seleccionada*/
	@RequestMapping(path="/mostrarAlerta")
	public ModelAndView mostrarSiQuiereEliminarUnaClaseOno(HttpServletRequest request, @ModelAttribute("agenda") AgendasViewModel agendasViewModel ){
		
		String rol = (String)request.getSession().getAttribute("ROL");
		if(!rol.equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}	
		ModelMap modelo = new ModelMap();
		
		modelo.put("rol", rol);
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
	
		
		/*Si no envio una clase para eliminar, entonces quiere eliminar un curso*/
		try{
				agendasViewModel.getIdAgendaSeleccionada().equals(null);
				
		}catch(NullPointerException e)
			{
		
				/*Si no quiere eliminar una clase*/
				/*Mostramos el curso a eliminar*/
				Inscripcion alumnoEnCurso = servicioInscripcion.buscarCursoAEliminar(agendasViewModel.getIdEspecialidad(), idAlumno);
				
				
				modelo.put("nombreEspecialidad", alumnoEnCurso);
				modelo.put("mensaje", "¿Deseas eliminarlas?");
				modelo.put("bandera", 1);
			}
			
		
	/*Si no envio un curso para eliminar, entonces quiere eliminar una clase*/
	try{
			agendasViewModel.getIdEspecialidad().equals(null);
			
		}catch(NullPointerException e)
			{
				//Traer la clase que selecciono para eliminar
				Agenda agenda = servicioAgenda.traerClaseQueQuiereEliminar(agendasViewModel.getIdAgendaSeleccionada(),idAlumno);
					
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
		
		String rol = (String)request.getSession().getAttribute("ROL");
		if(!rol.equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
		ModelMap modelo = new ModelMap();
		
		modelo.put("rol", rol);
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
		Inscripcion inscripcionBuscada = servicioInscripcion.buscarInscripcion(idAlumno, agendasViewModel.getIdCurso());	
					
		modelo.put("mensaje", "¿Estas seguro?");
		modelo.put("inscripcion", inscripcionBuscada);
					
		modelo.put("bandera", 3);
				
	return new ModelAndView("alertaEliminar", modelo);
	}
	
	
	
	
	/*Confirmado el metodo de Eliminar la clase seleccionada*/
	@RequestMapping(path="/eliminarClase")
	public ModelAndView eliminarUnaClase(HttpServletRequest request, @ModelAttribute("agenda") AgendasViewModel agendasViewModel ){
		
		String rol = (String)request.getSession().getAttribute("ROL");
		if(!rol.equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
		
		ModelMap modelo = new ModelMap();
	
		modelo.put("rol", rol);
		
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
		
		/*Si no envio una clase para eliminar, entonces quiere eliminar todas las clases juntas*/
		try{
		
			agendasViewModel.getIdAgendaSeleccionada().equals(null);
		
		   }catch(NullPointerException e)
				{
			   
			   /*verifico que no se pueda eliminar la agenda con menos de
				 * 2 dias de anticipacion*/
				Boolean resultado = servicioAgenda.verificarQueSePuedanEliminarTodasLasClases(idAlumno, agendasViewModel.getIdCurso());
				
				if(resultado.equals(false))
				{
					modelo.put("mensaje", "No se pueden eliminar las clases con menos de dos dias de anticipacion");
					return new ModelAndView("Eliminada", modelo);	
				}
				
				
					servicioInscripcion.eliminarInscripcionDelAlumnoYSusClasesDelCurso(agendasViewModel.getIdCurso(),idAlumno);
					modelo.put("mensaje", "Se te ha eliminado todas las clases del curso correctamente");
				}
		
		
		/*Si no envio un curso para eliminar, entonces quiere eliminar una clase*/
		try{
		
			agendasViewModel.getIdCurso().equals(null);

		   }catch(NullPointerException e)
				{
					/*verifico que no se pueda eliminar la agenda con menos de
					 * 2 dias de anticipacion*/
					Boolean result= servicioAgenda.verificarUnaAgendaSePuedaEliminar(agendasViewModel.getIdAgendaSeleccionada());
					
					if(result.equals(false))
					{
						modelo.put("mensaje", "No se puede eliminar la clase con menos de dos dias de anticipacion");
						return new ModelAndView("Eliminada", modelo);	
					}
					
					//Eliminar esta clase
					servicioAgenda.eliminarClaseDeLaAgenda(agendasViewModel.getIdAgendaSeleccionada(),idAlumno);
					modelo.put("mensaje", "Se ha eliminado la clase seleccionada correctamente");
		
				}//fin Catch
		
		
			return new ModelAndView("Eliminada", modelo);
	}
	
	
	
	
	@RequestMapping(path="/finalizado")
	public ModelAndView finalizoElCurso(
			@ModelAttribute("agenda") AgendasViewModel agendasViewModel,
			HttpServletRequest request )
	{
	
		String rol = (String)request.getSession().getAttribute("ROL");
		if(!rol.equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}	
			ModelMap modelo = new ModelMap();
			modelo.put("rol", rol);

			//Sesion
			Long idAlumno = (Long) request.getSession().getAttribute("ID");
			
			try{
				
				agendasViewModel.getIdAgendaSeleccionada().equals(null);
			
			   }catch(NullPointerException e)
					{
						servicioInscripcion.finalizarCursoDelAlumno(idAlumno, agendasViewModel.getIdCurso());
						modelo.put("mensaje", "Has finalizado el curso de manera exitosa");	
					}

		return new ModelAndView("Eliminada", modelo);
		
	}	
	
	
	
	
	@RequestMapping(path="/historial")
	public ModelAndView historialDeClases(HttpServletRequest request )
	{
		ModelMap modelo = new ModelMap();
		String rol = (String)request.getSession().getAttribute("ROL");
		if(!rol.equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}		
			modelo.put("rol", rol);
			
			//Sesion
			Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
			EstadoInscripcion finalizado = servicioEstadoInscripcion.buscarEstadoFinalizado();
			
			List<Inscripcion> cursando = servicioInscripcion.saberSiEstaRealizandoAlgunCurso(idAlumno, finalizado);//servicioAlumnoInscripcion
			
			if(cursando.isEmpty())
			{
				modelo.put("num", cursando.size());
				
				return new ModelAndView("historial", modelo);
			}
				
				//Busco el id del estado que dice "Finalizado"
				 EstadoInscripcion estado = servicioEstadoInscripcion.buscarEstadoFinalizado();
				List<Agenda> listadoDeClases = servicioAgenda.traerTodasLasClasesQueEstaAnotado(idAlumno, estado);
				List<Especialidad> especialidades = servicioEspecialidad.traerListaDeEspecialidades();
				
				modelo.put("num", cursando.size());
				modelo.put("listadoClases", listadoDeClases);
				modelo.put("listaCursos", especialidades);
				 
			return new ModelAndView("historial", modelo);
		}
		
		
	
	
	/*Trae solo las clases de la especialidad que selecciono en los filtros*/
	@RequestMapping(path="/mostrarclasesCurso")
	public ModelAndView ClasesDeUnSoloCurso(HttpServletRequest request, @RequestParam(name="id", required=false) Long idEspecialidad ){
		
		String rol = (String)request.getSession().getAttribute("ROL");
		if(!rol.equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}
		ModelMap modelo = new ModelMap();
		
		modelo.put("rol", rol);
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
		//Busco el id del estado que dice "Finalizado"
		 EstadoInscripcion estadoFinalizado = servicioEstadoInscripcion.buscarEstadoFinalizado();
		
		//Traer las clases del filtro elegido Agenda
		List<Agenda> clasesDeUnSoloCurso = servicioAgenda.traerTodasLasClasesDeUnaSolaEspecialidad(idEspecialidad,idAlumno, estadoFinalizado);//servicioAlumnoInscripcion
		
		//Traer solo los filtros Inscripcion
		List<Inscripcion> listadoDeFiltros = servicioInscripcion.traerLosCursosEnQueSeEncuentraAnotado(idAlumno,estadoFinalizado);
		
		
		/*Por si cambia el id de la url*/
		if(clasesDeUnSoloCurso.isEmpty() || idEspecialidad.equals(null))
		{
			modelo.put("error", "No has realizado o finalizado ese curso");
			modelo.put("botonFinalizarAnulado", "Anulado");
		}
		
		/*Si no tiene curso, se le pedira que se registre a alguno*/
		if(listadoDeFiltros.isEmpty())
		{
			modelo.put("num", listadoDeFiltros.size());
			modelo.put("listadoDeClases", clasesDeUnSoloCurso);

			return new ModelAndView("HistorialclasesElegidasEnElFiltroDeAlumno", modelo);
		}
			
		List<Especialidad> especialidades = servicioEspecialidad.traerListaDeEspecialidades();
		
		/*Sino, se le mostrara las clases que eligio del filtro*/
		modelo.put("num", listadoDeFiltros.size());
		/*Los cursos que esta realizando, para poder eliminarlos*/
		modelo.put("listaCursos", especialidades);
		modelo.put("listadoDeClases", clasesDeUnSoloCurso);

		return new ModelAndView("HistorialclasesElegidasEnElFiltroDeAlumno", modelo);
	
	}	
	
	
	

	// editar agenda
	
	@RequestMapping(path="/seleccionarAgenda")
	public ModelAndView editarClase(
			@ModelAttribute("agendasViewModel") AgendasViewModel agendasViewModel,
			HttpServletRequest request )
	{
		String rol = (String)request.getSession().getAttribute("ROL");
		if(!rol.equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}	ModelMap modelo = new ModelMap();
			modelo.put("rol", rol);
			
			
			//Sesion
			Long idAlumno = (Long) request.getSession().getAttribute("ID");
			
			//Datos del curso Elegido
			Curso curso = servicioCurso.buscarCursoPorId(agendasViewModel.getIdCurso());
			
			List <Inscripcion> inscripcionCurso = servicioInscripcion.consultarSiYaSeInscribioAUnCurso(idAlumno, curso);
							
	if(inscripcionCurso.isEmpty() ) //Todavia ese curso que eligio no esta anotado
		{
						
		
		
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
						return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
								
					}else{
						modelo.put("listaAgendas", agendas);
						modelo.put("listaAgendassize", agendas.size());	
						 }
						
				modelo.put("mensaje", "Una de las clases ha sido ocupada. Te buscamos clases nuevas");
				modelo.put("cursoSeleccionado", curso);

				return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
				
			}	
				
			//Datos del curso Elegido
			List <Agenda> datosAgendas= servicioAgenda.buscarAgendasElegidas( agendasViewModel.getIdAgendasDepurado(),  curso);
					
			modelo.put("mensaje", "Seleccione la agenda que desee modificar");
			modelo.put("listaAgendas", datosAgendas);
			modelo.put("cursoSeleccionado", curso);
				
		}	/////////////////////////if inscripcionCurso.isEmpty()	linea 173
		else{

				modelo.put("error","No podes agregar otro curso con la misma especialidad"); //Le avisa que no finalizo
				
				//Trae todo el listado de todos los cursos
				List<Curso> listaCursos =  servicioCurso.traerListaDeCursos();
					
				modelo.put("lista", listaCursos);
				return new ModelAndView("cursos", modelo); //Todavia no curso nada
					
			}
					
		
		modelo.put("curso2", agendasViewModel.getIdCurso());
		modelo.put("agendas2size", agendasViewModel.getIdAgendasDepurado().size());
		
		return new ModelAndView("seleccionarAgenda",modelo); 
		
	}

	
	
	
	@RequestMapping(path="/agendasAlternativas")
	public ModelAndView mostrarAgendasAlternativas(
			@ModelAttribute("agendasViewModel") AgendasViewModel agendasViewModel,
			HttpServletRequest request )
	{
		String rol = (String)request.getSession().getAttribute("ROL");
		if(!rol.equals("Alumno"))
		{
			return new ModelAndView("redirect:/index");
		}	
		
		ModelMap modelo = new ModelMap();
		modelo.put("rol", rol);
			
			//Sesion
			Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
			//Datos del curso Elegido
			Curso curso = servicioCurso.buscarCursoPorId(agendasViewModel.getIdCurso());
			
			List <Inscripcion> inscripcionCurso = servicioInscripcion.consultarSiYaSeInscribioAUnCurso(idAlumno, curso);
							
	if(inscripcionCurso.isEmpty() ) //Todavia ese curso que eligio no esta anotado
		{
						
		//Consultar que no le hayan ocupado esas fechas
		Boolean resultado = servicioAgenda.constatarQueNadieSeAnotaraEnLasFechasAsignadas(agendasViewModel.getIdAgendasDepurado(),curso);
		
		//Si las fechas que me asignaron no fueron ocupadas
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
						modelo.put("listaAgendassize", agendas.size());	
						 }
						
				modelo.put("mensaje", "Una de las clases ha sido ocupada. Te buscamos clases nuevas");
				modelo.put("cursoSeleccionado", curso);

				return new ModelAndView("fechasAlumnoEnAgenda",modelo); 
						
			}	
				
				
			
				//traer agendas disponibles diferentes a las fechas seleccionadas 
				List<Agenda> agendasAlternativas=servicioAgenda.traerAgendasParaReemplazarOtra(curso, agendasViewModel.getIdAgendasDepurado(), idAlumno);

				modelo.put("mensaje", "Elige la nueva agenda");
				modelo.put("listaAgendas", agendasViewModel.getIdAgendasDepurado());
				modelo.put("agendasAlternativas", agendasAlternativas);
				modelo.put("cursoSeleccionado", curso);
				modelo.put("agen",agendasViewModel.getIdAgendaEditar());	

						
			
											
							
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
	
	String rol = (String)request.getSession().getAttribute("ROL");
	if(!rol.equals("Alumno"))
	{
		return new ModelAndView("redirect:/index");
	}
	
	ModelMap modelo = new ModelMap();
		
		modelo.put("rol", rol);
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
		//Datos del curso Elegido
		Curso curso = servicioCurso.buscarCursoPorId(agendasViewModel.getIdCurso());

					
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

		
		List<Agenda> datosAgendas= servicioAgenda.reemplazarAgenda(agendasViewModel.getIdAgendaSeleccionada(),
				agendasViewModel.getIdAgendasDepurado(),agendasViewModel.getIdAgendaEditar(), curso);
			
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
	
	String rol = (String)request.getSession().getAttribute("ROL");
	if(!rol.equals("Alumno"))
	{
		return new ModelAndView("redirect:/index");
	}
	ModelMap modelo = new ModelMap();
	modelo.put("rol", rol);
	
		//Datos del curso Elegido
		Curso curso = servicioCurso.buscarCursoPorId(agendasViewModel.getIdCurso());
		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
		
		//traer agendas disponibles diferentes a las fechas seleccionadas 
		List<Agenda> agendasAlternativas=servicioAgenda.traerAgendasParaReemplazarOtra(curso, agendasViewModel.getIdAgendasDepurado(), idAlumno);
	
	
		Inscripcion inscripcion = servicioInscripcion.buscarInscripcion(idAlumno,curso.getId());
		
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

	String rol = (String)request.getSession().getAttribute("ROL");
	if(!rol.equals("Alumno"))
	{
		return new ModelAndView("redirect:/index");
	}
	
	ModelMap modelo = new ModelMap();
	modelo.put("rol", rol);

		
		//Sesion
		Long idAlumno = (Long) request.getSession().getAttribute("ID");
		
		//Datos del curso Elegido
		Curso curso = servicioCurso.buscarCursoPorId(agendasViewModel.getIdCurso());//servicioAlumnoInscripcion
									
	//Consultar que no le hayan ocupado esas fechas
	Boolean resultado = servicioAgenda.constatarQueNadieSeAnotaraEnLasFechasAsignadas(agendasViewModel.getIdAgendasDepurado(),curso); //servicioAlumnoAgenda
	
	//Si las fechas que me asignaron no fueron ocupadas
		if(resultado.equals(false))
		{
			
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
			//Anotarme
		servicioInscripcion.agregarInscripcion(idAlumno, curso,agendasViewModel.getIdAgendaEditar());//servicioAlumnoInscripcion
		modelo.put("mensaje", "La clase se agregó con éxito");
		modelo.put("curso2", agendasViewModel.getIdCurso());
		modelo.put("agendas2", agendasViewModel.getIdAgendasDepurado());
		modelo.put("agendas2size", agendasViewModel.getIdAgendasDepurado().size());
		
	return new ModelAndView("inscripcionExitosa",modelo); 
	
}



	


/*****************MOCK*****************/


public void setServicioCurso(ServicioCurso servicioCurso) {
	this.servicioCurso = servicioCurso;
}
public void setServicioAlumno(ServicioAlumno servicioAlumno) {
	this.servicioAlumno = servicioAlumno;
}
public void setServicioEstadoInscripcion(ServicioEstadoInscripcion servicioEstadoInscripcion) {
	this.servicioEstadoInscripcion = servicioEstadoInscripcion;
}
public void setServicioAgenda(ServicioAgenda servicioAgenda) {
	this.servicioAgenda = servicioAgenda;
}
public void setServicioInscripcion(ServicioInscripcion servicioInscripcion) {
	this.servicioInscripcion = servicioInscripcion;
}

			
}	
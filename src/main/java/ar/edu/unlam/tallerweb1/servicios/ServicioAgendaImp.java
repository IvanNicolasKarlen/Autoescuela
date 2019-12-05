package ar.edu.unlam.tallerweb1.servicios;

import java.sql.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.ViewModel.CursosViewModel;
import ar.edu.unlam.tallerweb1.dao.AgendaDao;
import ar.edu.unlam.tallerweb1.dao.AlumnoDao;
import ar.edu.unlam.tallerweb1.dao.EstadoDeAgendaDao;
import ar.edu.unlam.tallerweb1.dao.EstadoInscripcionDao;
import ar.edu.unlam.tallerweb1.dao.InscripcionDao;
import ar.edu.unlam.tallerweb1.dao.InstructorDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
@Service("servicioAgenda")
@Transactional
public class ServicioAgendaImp implements ServicioAgenda{
	@Inject
	private AgendaDao  agendaDao;
	@Inject
	private EstadoInscripcionDao estadoinscripcionDao;
	@Inject
	private EstadoDeAgendaDao estadoDeAgendaDao;
	@Inject
	private ServicioEstadoInscripcion servicioEstadoInscripcion;
	@Inject
	private AlumnoDao alumnoDao;
	@Inject
	private InscripcionDao inscripcionDao;
	@Inject
	private InstructorDao instructorDao;
	@Inject
	private ServicioNotificacion servicioNotificacion;
	
	
	/**************************INSTRUCTOR*******************************/

	@Override
	public List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor) {
		

		Instructor instructor = instructorDao.buscarInstructor(idInstructor);
		
		return agendaDao.buscarDiaYHorarioDeTurnoDeUnInstructor(instructor.getId());
	}
	
	@Override
	public List<Agenda> buscarAlumnos(Long idInstructor,String nombreDeUsuario) {
			
		//Traigo los datos del alumno logueado
		Instructor instructor = instructorDao.buscarInstructor(idInstructor);
		
			return agendaDao.buscarAlumnos(instructor.getId(),nombreDeUsuario);
}
	

	
	@Override
	public void updateAgenda(Agenda agenda) {
			agendaDao.updateAgenda(agenda);
	}
	
	@Override
	public List<Agenda> traerFechas() {
			return agendaDao.traerFechas();
	}
	
	
	@Override
	public Map<String, Integer> horasTrabajadas(Long idInstructor){
		
		Map<String,Integer> meses = new HashMap<String,Integer>();

		Integer enero = 0, febrero = 0, marzo = 0, abril = 0, mayo = 0, junio = 0, julio = 0, agosto = 0,
				septiembre = 0, octubre = 0, noviembre = 0, diciembre = 0;

		List<Agenda> listaAgenda = agendaDao.traerFechas();/*Traigo las fechas para luego recorrer esta lista en el for*/
		
		List<LocalDate> listaLocalDate = new ArrayList<>(); /*Lista creada para añadirle el localdate de la linea 83*/
		
		/* Con un for recorro la listaAgenda, esas fechas las conviertos a localdate y las guardo en una lista LocalDate*/
		for (Agenda lag : listaAgenda) { 
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate localdate = LocalDate.parse(lag.getFecha(), formatter);
			lag.getFecha();
			listaLocalDate.add(localdate);
		}
		
		for (LocalDate lisld : listaLocalDate) { /*Recorro la lista LocalDate y de esa lista obtengo los meses y hago un switch 
													contado que cada vez que recorra y haya x mes se sume a la variable*/
			
			switch (lisld.getMonth()) {
			case JANUARY:
				enero ++;
				break;
			case FEBRUARY:
				febrero ++;
				break;
			case MARCH:
				marzo ++;
				break;
			case APRIL:
				abril ++;
				break;
			case MAY:
				mayo ++;
				break;
			case JUNE:
				junio ++;
				break;
			case JULY:
				julio ++;
				break;
			case AUGUST:
				agosto ++;
				break;
			case SEPTEMBER:
				septiembre ++;
				break;
			case OCTOBER:
				octubre ++;
				break;
			case NOVEMBER:
				noviembre ++;
				break;
			case DECEMBER:
				diciembre ++;
				break;
			}
	}
		
		meses.put("enero", enero);
		meses.put("febrero", febrero);
		meses.put("marzo", marzo);
		meses.put("abril", abril);
		meses.put("mayo", mayo);
		meses.put("junio", junio);
		meses.put("julio", julio);
		meses.put("agosto", agosto);
		meses.put("septiembre", septiembre);
		meses.put("octubre", octubre);
		meses.put("noviembre", noviembre);
		meses.put("diciembre", diciembre);		
		
		
		
		Map<String, Integer> meses1 = meses.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));	
		
		return meses1;

	}
	
	@Override
	public List<Agenda> traerFechasDisponibles(Long idInstructor) {
		
		Instructor instructor = instructorDao.buscarInstructor(idInstructor);

		
		
		return agendaDao.traerFechasDisponibles(instructor.getId());	
	}
	
	@Override
	public Agenda buscarAgenda(Agenda agenda) {
		return agendaDao.buscarAgenda(agenda);
	}
	/**********************************************************************************************/
	
	@Override
	public TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso Curso,Long idAlumno) {
		
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		
		 EstadoDeAgenda disponible = estadoDeAgendaDao.traigoElEstadoDisponible();

		 EstadoInscripcion estadoCursando = servicioEstadoInscripcion.buscarEstadoCursando();
		 
		 List<Agenda> listadoDeClases = agendaDao.traerTodasLasClasesQueEstaAnotado(alumno.getId(), estadoCursando);
		 
		 List<Agenda> listadoDeClasesOcupadas = new ArrayList<Agenda>();
		 
		 List<String> fechasOcupadas = new ArrayList<String>();
		
		 
		 for(Agenda clase: listadoDeClases)
		 {
			 if(clase.getEstadoDeAgenda().getEstado().equals("Ocupada"))
			 {
				 listadoDeClasesOcupadas.add(clase);
				 fechasOcupadas.add(clase.getFecha());
			 }
		 }
		 
	 
TreeSet<Agenda> agendasSinDuplicados= agendaDao.traerAgendasConFechasNoRepetidas(Curso, alumno.getId(), disponible);

TreeSet<Agenda> returnClases = new TreeSet<Agenda>();
		 
		 if(!listadoDeClasesOcupadas.isEmpty())
			 {
				 for(Agenda ag:listadoDeClasesOcupadas)
				 {
					 for(Agenda aSinDuplicado: agendasSinDuplicados)
					 {
						 
						 if(   !aSinDuplicado.getFecha().equals(ag.getFecha())) 
						 {
							 
							 if(!fechasOcupadas.contains(aSinDuplicado.getFecha()))
							 {
								 	returnClases.add(aSinDuplicado);
							 } 
						 }
						 		 
					 }
					 		
				 }
			 }else
				 {	 
				 returnClases= agendaDao.traerAgendasConFechasNoRepetidas(Curso, alumno.getId(), disponible);
				  
				}
		 
	
	 
		 LocalDate hoy = LocalDate.now();
			// reverseOrder ordena los elementos
			// en forma descendente
		 TreeSet<Agenda> listaClases = new TreeSet<Agenda>(java.util.Collections.reverseOrder());

		
		 
			 //Guardo las agendas mayores o iguales a hoy
			for(Agenda a: returnClases)
			{
				//Parseo la fecha
				 
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				 LocalDate localDate = LocalDate.parse(a.getFecha(), formatter);
				 
				 if(localDate.isAfter(hoy))
		        	{
		        		listaClases.add(a);		        			 
		        	}	 	 
			}
			
			
			//eliminarAgendasQueSuperanLaCantidadDeClasesDelCurso
			listaClases.removeIf((Agenda a) -> listaClases.size() > Curso.getCantClasesPracticas());
			
			// Ordeno las agendas con las fechas en forma ascendente
			TreeSet<Agenda> agendasAsc = new TreeSet<Agenda>();
			agendasAsc.addAll(listaClases);	
		return agendasAsc;
	

	}
	
	
	@Override
	public Boolean constatarQueNadieSeAnotaraEnLasFechasAsignadas(List<Long> idAgendasDepurado, Curso curso) {
		//Declaramos una lista para guardar las agendas buscadas
		List<Agenda> Agendas= new ArrayList<Agenda>();
		
		//Reorremos los ID de las agendas seleccionadas que pasamos por parametro
		for(Long a: idAgendasDepurado)
		{	
			//tratamos la excepcion nullPointer en caso que el
			// *metodo devuelva null
			try
			{
				// *metodo
				Agenda aBuscada=agendaDao.buscarAgendasElegidas(a, curso); //alumnoAgendaDao
				
				//comparamos que el id de la agenda buscada
				// sea igual que el de la agenda que le pasamos por parametro
				if(a.equals(aBuscada.getId())){
					
					Agendas.add(aBuscada);
					}else{
						return false;
					}
				
			}
			catch(NullPointerException e)
			{
				break; //finalizamos el for
			}

		}

		// si la cantidad de la lista con las agendas buscadas
		// es igual a la cant de las agendas pasadas por parametro,
		// las agendas estan disponibles y retorna true
		if(Agendas.size() == idAgendasDepurado.size())
			{
			 return true;
				
			}
		
		return false;
	}
	
	
	@Override
	public List<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno, EstadoInscripcion estado) {
		
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		return agendaDao.traerTodasLasClasesQueEstaAnotado(alumno.getId(), estado);
	}
	
	
	
	@Override
	public TreeSet<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(CursosViewModel cursosViewModel, Long idAlumno) {
		
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion estado = estadoinscripcionDao.buscarEstadoCursando();
		
		 TreeSet<Agenda> listaAgregarInscripcion  = new TreeSet<Agenda>();
		 
		 for(Long c: cursosViewModel.getListaCursos())
		 {
			 
			 TreeSet<Agenda> listaInscripcion = agendaDao.traerTodasLasClasesQueSeEncuentraAnotado(c, estado, alumno.getId());
		
		listaAgregarInscripcion.addAll(listaInscripcion);
		 }
		 
		return listaAgregarInscripcion;
	}
	@Override
	public List<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idEspecialidad, Long idAlumno , EstadoInscripcion estado) {
		
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		
		return agendaDao.traerTodasLasClasesDeUnaSolaEspecialidad( idEspecialidad, alumno.getId(), estado);
	}
	@Override
	public Agenda traerClaseQueQuiereEliminar(Long idAgendaSeleccionado, Long idAlumno) {
		
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		
		return agendaDao.traerClaseQueQuiereEliminar( idAgendaSeleccionado,  alumno.getId());
	}
	
	@Override
	public void eliminarClaseDeLaAgenda(Long idAgendaSeleccionado, Long idAlumno) {
	
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		
		//Traigo clase que quiero eliminar
		Agenda agenda= agendaDao.traerClaseQueQuiereEliminar( idAgendaSeleccionado,  alumno.getId());
	
		 EstadoDeAgenda canceladaPorAlumno = estadoDeAgendaDao.traigoElEstadoCanceladaPorAlumno();
		 
		agenda.setEstadoDeAgenda(canceladaPorAlumno);
		
		//Eliminar esta clase
		 agendaDao.updateAgenda(agenda);
		 
		 //Creo la notificacion
		 servicioNotificacion.crearNotificacion(usuario, agenda);
		 
		
		//Creo la misma clase para que la pueda ocupar otro alumno con estado disponible
		 Agenda claseAGuardar = new Agenda();
		 EstadoDeAgenda disponible = estadoDeAgendaDao.traigoElEstadoDisponible();
		 
		 claseAGuardar.setClasePagada(agenda.getClasePagada());
		 claseAGuardar.setEstadoDeAgenda(disponible);
		 claseAGuardar.setFecha(agenda.getFecha());
		 claseAGuardar.setHora(agenda.getHora());
		 claseAGuardar.setInscripcion(null);
		 claseAGuardar.setInstructorVehiculoEspecialidad(agenda.getInstructorVehiculoEspecialidad());
		 
		 
		 //Guardas la misma clase pero disponible para otro alumno
		 agendaDao.crearAgenda(claseAGuardar); //Es un save(agenda)
		 
		 
		 
		 
		
	}


	@Override
	public List<Agenda> buscarAgendasElegidas(List<Long> idAgendasDepurado, Curso curso) {
		List<Agenda> listaAgendas  = new ArrayList<Agenda>();
		for(Long id: idAgendasDepurado){
			Agenda agendaBuscada = agendaDao.buscarAgendasElegidas(id, curso);
			listaAgendas.add(agendaBuscada);
		}
		
		return listaAgendas;
	}


	@Override
	public List<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas, Long idAlumno) 
	{
		
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		
		System.out.println("ID ALUMNO"+ alumno.getId());
		System.out.println("CURSO"+ curso.getId());
				
	   	 LocalDate hoy = LocalDate.now();
		 List<Agenda> listaClases = new ArrayList<Agenda>();

		 EstadoInscripcion estadoCursando = servicioEstadoInscripcion.buscarEstadoCursando();
		 
		 System.out.println("ESTADO CURSANDO"+ estadoCursando.getEstado());
		 
		 List<Agenda> listadoDeClases = agendaDao.traerTodasLasClasesQueEstaAnotado(alumno.getId(), estadoCursando);
		 
		 List<Agenda> agendas = agendaDao.traerAgendasParaReemplazarOtra(curso, idAgendas);	
		 
		 System.out.println("MIS CLASES(2) "+listadoDeClases.size());
		
		 System.out.println("NUEVAS(2) "+agendas.size());
		 
		 		 
 List<Agenda> listadoDeClasesOcupadas = new ArrayList<Agenda>();
		 for(Agenda clase: listadoDeClases)
		 {
			 if(clase.getEstadoDeAgenda().getEstado().equals("Ocupada"))
			 {
				 listadoDeClasesOcupadas.add(clase);
			 }
		 }
		 


		 for(Agenda a: agendas)
			{
			 System.out.println("AGENDA PARA REEMPLAZAAAAR "+ a.getFecha() + "HORA " + a.getHora());
				}
		 
	 
		 if(!listadoDeClasesOcupadas.isEmpty())
			 {
		 
			 System.out.println("ENTRA AL IF(2) ");
				
				 for(Agenda ag:listadoDeClasesOcupadas)
				 {
					 System.out.println("MI CLASE(2) "+ ag.getFecha()+" HORA "+ag.getHora());
				  agendas.removeIf((Agenda a)->
				  a.getFecha().equals(ag.getFecha())
				  && a.getHora().equals(ag.getHora()));
			
				  
				  System.out.println("REMUEVEE "+ ag.getFecha()+ " HORA "+ ag.getHora());
				  
				 }
			 }else
				 {
					 
				 agendas= agendaDao.traerAgendasParaReemplazarOtra(curso, idAgendas);
					 
				 }
		 
		 for(Agenda clase: agendas)
		 {
			System.out.println("AGENDA A MOSTRAR " +clase.getFecha() + "HORA" + clase.getHora());
		 }
		 
		 
		// returnClases.addAll(agendas);
		 
		 for(Long idAgenda: idAgendas)
			{
				//Elimino las agendas que ya habia seleccionado antes
			 agendas.removeIf((Agenda a) -> a.getId().equals(idAgenda));
			}
		 
		
			 //Guardo las agendas mayores a hoy
			for(Agenda a: agendas)
			{
				
	
				 //validar que las fechas sean mayores a la fecha de hoy

				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				 LocalDate localDate = LocalDate.parse(a.getFecha(), formatter);
				 
				 if(localDate.isAfter(hoy))
		        	{
		        		listaClases.add(a);
		        	}
			}
			
			
			
			Collections.sort(listaClases);
			
		return listaClases;
		
		
	}


	@Override
	public List<Agenda> reemplazarAgenda(Long idAgendaSeleccionada, List<Long> idAgendas, Long idAgendaEditar, Curso curso) {
	
		
		idAgendas.removeIf((Long id )-> id == idAgendaEditar);
		idAgendas.add(idAgendaSeleccionada);
	
		
		List<Agenda> listaAgendas  = new ArrayList<Agenda>();
		
		for(Long id: idAgendas){
			Agenda agendaBuscada = agendaDao.buscarAgendasElegidas(id, curso);
			listaAgendas.add(agendaBuscada);
		}
		
		return listaAgendas;
		
	}
	
	
	@Override
	public List<Agenda> traerClasesQueEsteCursando(List<Agenda> clasesDeUnSoloCurso) {
		List<Agenda> clasesCursandoSolamente = new ArrayList<Agenda>();
		for(Agenda clase : clasesDeUnSoloCurso)
		{
			if(clase.getEstadoDeAgenda().getEstado().equals("Ocupada"))
			{
				clasesCursandoSolamente.add(clase);
			}
		}

		return clasesCursandoSolamente;
	}


	
	
	@Override
	public Boolean verificarQueSePuedanEliminarTodasLasClases(Long idAlumno, Long idCurso)
	{
		
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion estadoCursando = estadoinscripcionDao.buscarEstadoCursando();
		
		 List<Agenda> listadoDeClases = agendaDao.traerTodasLasClasesQueEstaAnotado(alumno.getId(), estadoCursando);
			
		 
		 Integer returnTrue = 0;
		 Integer returnFalse = 0;
		 
		 for(Agenda misClases: listadoDeClases)
		 {
		 
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate fechaComparar = LocalDate.parse(misClases.getFecha(),formatter);
				String fechaHoy = LocalDate.now().format(formatter);
				LocalDate hoy = LocalDate.parse(fechaHoy, formatter);
				
				Long diferenciaDias = ChronoUnit.DAYS.between(hoy, fechaComparar);
			
				if(diferenciaDias>2){
					returnTrue++;
				}else{
					returnFalse++;
				}
		 }
		 
		 
		 
		 if(returnFalse > 0)
		 {
			 return false;
		 }
		 
		return true;
		
	}
	
	
	
	
	
	
	public Boolean crearAgenda(EstadoDeAgenda estadoDeAgenda, LocalDate desde, LocalDate hasta, Integer horaC, Integer horaF, List<InstructorVehiculoEspecialidad> listaIve){
		List <Agenda> agendas = new ArrayList<Agenda>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		for(LocalDate date = desde; date.isBefore(hasta); date = date.plusDays(1)){
			for(InstructorVehiculoEspecialidad ive:listaIve){
				for(Integer i=horaC;i<=horaF;i=i+100){
					String fecha = date.format(formatter);
					if(agendaDao.traerAgendaPorFechaHoraInstructor(fecha, i, ive.getInstructor())==null){
						Agenda ag = new Agenda();
						ag.setFecha(fecha);
						ag.setHora(i);
						ag.setInstructorVehiculoEspecialidad(ive);
						ag.setEstadoDeAgenda(estadoDeAgenda);
						ag.setClasePagada(false);
						agendas.add(ag);
					}
				}
			}
		}
		
		Long id=null;
		Integer cantidad = 0;
		for(Agenda varAgendas:agendas){
			id = agendaDao.crearAgenda(varAgendas);
			if(id!=null){
				cantidad++;
			}
		}
		
		return (cantidad.equals(agendas.size()));
		
	}


	@Override
	public List<Agenda> traerAgendaPorFechayHora(String fecha, Integer hora) {
		return agendaDao.traerAgendaPorFechayHora(fecha,hora);
	}


	@Override
	public Agenda traerAgendaPorFechaYAlumno(Alumno alumno, String fecha) {
		return agendaDao.traerAgendaPorFechaYAlumno(alumno,fecha);
	}


	@Override
	public List<Agenda> traerTodasLasClasesDeUnAlumno(Long id) {
		return agendaDao.traerTodasLasClasesDeUnAlumno(id);
	}


	@Override
	public void modificarAgenda(Agenda agenda) {
		if(agenda.getEstadoDeAgenda().getEstado().equals("Finalizada")){
			agenda.setClasePagada(true);
		}
		agendaDao.updateAgenda(agenda);
	}


	@Override
	public Agenda buscarAgendaPorId(Long idAgenda) {
		return agendaDao.buscarAgendaPorId(idAgenda);
	}

	@Override
	public Boolean verificarUnaAgendaSePuedaEliminar(Long idAgendaSeleccionada) {
		Agenda agenda=agendaDao.buscarAgendaPorId(idAgendaSeleccionada); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate fechaComparar = LocalDate.parse(agenda.getFecha(),formatter);
		String fechaHoy = LocalDate.now().format(formatter);
		LocalDate hoy = LocalDate.parse(fechaHoy, formatter);
		
		Long diferenciaDias = ChronoUnit.DAYS.between(hoy, fechaComparar);
	
		if(diferenciaDias>2){
			return true;
		}else{
			return false;
		}
	}


	
	
	@Override
	public void setAlumnoDao(AlumnoDao alumnoDao) {
		this.alumnoDao= alumnoDao;
		
	}

	@Override
	public void setEstadoInscripcionDao(EstadoInscripcionDao estadoinscripcionDao) {
		this.estadoinscripcionDao= estadoinscripcionDao;
		
	}

	@Override
	public void setAgendaDao(AgendaDao agendaDao) {
		this.agendaDao= agendaDao;
		
	}

}
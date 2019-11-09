package ar.edu.unlam.tallerweb1.servicios;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
<<<<<<< HEAD
=======
import java.time.temporal.ChronoUnit;
>>>>>>> Diana
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.ViewModel.CursosViewModel;
import ar.edu.unlam.tallerweb1.dao.AgendaDao;
import ar.edu.unlam.tallerweb1.dao.EstadoDeAgendaDao;
import ar.edu.unlam.tallerweb1.dao.EstadoInscripcionDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;
@Service("servicioAgenda")
@Transactional
public class ServicioAgendaImp implements ServicioAgenda{
	@Inject
	private AgendaDao  agendaDao;
	@Inject
	private EstadoInscripcionDao estadoinscripcionDao;
	@Inject
	private EstadoDeAgendaDao estadoDeAgendaDao;
	
<<<<<<< HEAD
	/**************************INSTRUCTOR*******************************/

=======
>>>>>>> Diana
	@Override
	public List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor) {
		return agendaDao.buscarDiaYHorarioDeTurnoDeUnInstructor(idInstructor);
	}
	
<<<<<<< HEAD
	@Override
	public List<Agenda> buscarAlumnos(String nombre,String apellido) {
			return agendaDao.buscarAlumnos(nombre,apellido);
}
	
	@Override
	public void updateEstadoDeAgenda(Agenda agenda) {
			agendaDao.updateEstadoDeAgenda(agenda);
	}
	
	@Override
	public List<Agenda> traerFechasDisponibles() {
		return agendaDao.traerFechasDisponibles();	
	}
=======
	
>>>>>>> Diana
	/********************************* Alumno ********************************/
	@Override
	public TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso Curso) {

<<<<<<< HEAD
		
//		LocalDate hoy = LocalDate.now();
//		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
//		 String fechaDeHoy = hoy.format(formatter);
//			//Guardo las agendas mayores o iguales a hoy
//		for(Agenda a: agendasAsc)
//		{
//			//Parseo la fecha
//			 LocalDate agendas = LocalDate.parse(a.getFecha());
//			 LocalDate hoy = LocalDate.now();
//			 TreeSet<LocalDate> listaClases = new TreeSet<LocalDate>();
//			 
//			 
//			 List<String> horasString = new ArrayList<String>();
//			 
//			 if(hoy.getDayOfMonth() <= agendas.getDayOfMonth() && hoy.getYear() < agendas.getYear())
//			 {
//				 listaClases.add(agendas);
//			 
//				 
//			 }
	
		 EstadoDeAgenda disponible = estadoDeAgendaDao.traigoElEstadoDisponible();
		 TreeSet<Agenda> agendasSinDuplicados= agendaDao.traerAgendasConFechasNoRepetidas(Curso, disponible);
		
			LocalDate hoy = LocalDate.now();
=======
		 EstadoDeAgenda disponible = estadoDeAgendaDao.traigoElEstadoDisponible();
		 TreeSet<Agenda> agendasSinDuplicados= agendaDao.traerAgendasConFechasNoRepetidas(Curso, disponible);
		
		LocalDate hoy = LocalDate.now();
>>>>>>> Diana
		 TreeSet<Agenda> listaClases = new TreeSet<Agenda>(java.util.Collections.reverseOrder());
			 
		 System.out.println("Fecha de hoy:");
		 System.out.println(hoy);
			 

		
<<<<<<< HEAD
			 //Guardo las agendas mayores o iguales a hoy
			for(Agenda a: agendasSinDuplicados)
			{
				//Parseo la fecha
				 //LocalDate agendas = LocalDate.parse(a.getFecha());
				 
				 
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate localDate = LocalDate.parse(a.getFecha(), formatter);
				 System.out.println("Fecha de Agenda");		 
				 System.out.println(localDate);
				
=======
			 //Guardo las agendas mayores a hoy
			for(Agenda a: agendasSinDuplicados)
			{
				
				 //validar que las fechas sean mayores a la fecha de hoy
				 
				 
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				 LocalDate localDate = LocalDate.parse(a.getFecha(), formatter);
				 System.out.println("Fecha de Agenda");		 
				 System.out.println(localDate);
				
				 
>>>>>>> Diana
				 if(localDate.isAfter(hoy))
		        	{
		        		listaClases.add(a);

						 System.out.println("Fecha Guardada");
						 System.out.println(a.getFecha());
		        	}
		
				 	 
			}
			
			
			//eliminarAgendasQueSuperanLaCantidadDeClasesDelCurso
			listaClases.removeIf((Agenda a) -> listaClases.size() > Curso.getCantClasesPracticas());
			
			// Ordeno las agendas con las fechas en forma ascendente
			TreeSet<Agenda> agendasAsc = new TreeSet<Agenda>();
			agendasAsc.addAll(listaClases);	
		
			
		return agendasAsc;
<<<<<<< HEAD
			 
			//return agendasAsc;
=======
>>>>>>> Diana

	}
	
	
	
	@Override
<<<<<<< HEAD
	public Boolean constatarQueNadieSeAnotaraEnLasFechasAsignadas(List<Long> idAgendasDepurado, Curso curso) {
=======
	public Boolean constatarQueNadieSeAnotaraEnLasFechasAsignadas(AgendasViewModel agendasViewModel, Curso curso) {
>>>>>>> Diana
		//Declaramos una lista para guardar las agendas buscadas
		List<Agenda> Agendas= new ArrayList();
		
		//Reorremos los ID de las agendas seleccionadas que pasamos por parametro
<<<<<<< HEAD
		for(Long a: idAgendasDepurado)
=======
		for(Long a: agendasViewModel.getIdAgendasDepurado())
>>>>>>> Diana
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
<<<<<<< HEAD
		if(Agendas.size() == idAgendasDepurado.size())
=======
		if(Agendas.size() == agendasViewModel.getIdAgendasDepurado().size())
>>>>>>> Diana
			{
			 return true;
				
			}
		
		return false;
	}
	
	
	@Override
<<<<<<< HEAD
	public TreeSet<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno, EstadoInscripcion estado) {
		
		
		return agendaDao.traerTodasLasClasesQueEstaAnotado(idAlumno, estado);
=======
	public TreeSet<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno) {
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion estado = estadoinscripcionDao.buscarEstadoCursando();
		
		return agendaDao.traerTodasLasClasesQueEstaAnotado(idAlumno);
>>>>>>> Diana
	}
	
	
	
	@Override
	public TreeSet<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(CursosViewModel cursosViewModel, Long idAlumno) {
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion estado = estadoinscripcionDao.buscarEstadoCursando();
		
		 TreeSet<Agenda> listaAgregarInscripcion  = new TreeSet<Agenda>();
		 
		 for(Long c: cursosViewModel.getListaCursos())
		 {
			 
			 TreeSet<Agenda> listaInscripcion = agendaDao.traerTodasLasClasesQueSeEncuentraAnotado(c, estado, idAlumno);
		
		listaAgregarInscripcion.addAll(listaInscripcion);
		 }
		 
		return listaAgregarInscripcion;
	}
	@Override
<<<<<<< HEAD
	public TreeSet<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idEspecialidad, Long idAlumno , EstadoInscripcion estado) {
		
		return agendaDao.traerTodasLasClasesDeUnaSolaEspecialidad( idEspecialidad, idAlumno, estado);
=======
	public TreeSet<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idEspecialidad, Long idAlumno) {
		
		return agendaDao.traerTodasLasClasesDeUnaSolaEspecialidad(idEspecialidad, idAlumno);
>>>>>>> Diana
	}
	@Override
	public Agenda traerClaseQueQuiereEliminar(Long idAgendaSeleccionado, Long idAlumno) {
		
		
		
		return agendaDao.traerClaseQueQuiereEliminar( idAgendaSeleccionado,  idAlumno);
	}
	
	@Override
	public void eliminarClaseDeLaAgenda(Long idAgendaSeleccionado, Long idAlumno) {
	
		
		//Traigo clase que quiero eliminar
		Agenda agenda= agendaDao.traerClaseQueQuiereEliminar( idAgendaSeleccionado,  idAlumno);
	
		 EstadoDeAgenda canceladaPorAlumno = estadoDeAgendaDao.traigoElEstadoCanceladaPorAlumno();
		 
<<<<<<< HEAD
		//agenda.setInscripcion(null);
=======
		agenda.setInscripcion(null);
>>>>>>> Diana
		agenda.setEstadoDeAgenda(canceladaPorAlumno);
		
		//Eliminar esta clase
		 agendaDao.eliminarClaseDeLaAgenda(agenda);
		 
		
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
		 agendaDao.guardarClaseQueEliminoElAlumnoParaQueSePuedaInscribirOtroAlumno(claseAGuardar); //Es un save(agenda)
		 
		 
		 
		 
		
	}


	@Override
	public List<Agenda> buscarAgendasElegidas(List<Long> idAgendasDepurado, Curso curso) {
		List<Agenda> listaAgendas  = new ArrayList();
		for(Long id: idAgendasDepurado){
			Agenda agendaBuscada = agendaDao.buscarAgendasElegidas(id, curso);
			listaAgendas.add(agendaBuscada);
		}
		
		return listaAgendas;
	}


	@Override
<<<<<<< HEAD
	public List<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas) 
	{

		List<Agenda> agen = agendaDao.traerAgendasParaReemplazarOtra(curso, idAgendas);
		for(Long idAgenda: idAgendas)
		{
			agen.removeIf((Agenda a) -> a.getId().equals(idAgenda));
		}
	
		return agen ;
=======
	public TreeSet<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas) 
	{

//		List<Agenda> agen = agendaDao.traerAgendasParaReemplazarOtra(curso, idAgendas);
//	
//		System.out.println("AGENNNNNNNNDA"+agen.size());
//		
//		for(Long idAgenda: idAgendas)
//		{
//			//Elimino las agendas que ya habia seleccionado antes
//			agen.removeIf((Agenda a) -> a.getId().equals(idAgenda));
//		}
//	
//		System.out.println("Tamaño de agenda retornada: "+agen);
//		return agen ;
//		
//		
		
		
		
		 EstadoDeAgenda disponible = estadoDeAgendaDao.traigoElEstadoDisponible();
		 TreeSet<Agenda> agen = agendaDao.traerAgendasParaReemplazarOtra(curso, idAgendas);		
	   	 LocalDate hoy = LocalDate.now();
		 TreeSet<Agenda> listaClases = new TreeSet<Agenda>();
			 
		 System.out.println("Fecha de hoy:");
		 System.out.println(hoy);
			 

		 for(Long idAgenda: idAgendas)
			{
				//Elimino las agendas que ya habia seleccionado antes
				agen.removeIf((Agenda a) -> a.getId().equals(idAgenda));
			}
		 
		 
		
			 //Guardo las agendas mayores a hoy
			for(Agenda a: agen)
			{
				
				 //validar que las fechas sean mayores a la fecha de hoy
				 
				 
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				 LocalDate localDate = LocalDate.parse(a.getFecha(), formatter);
				 System.out.println("Fecha de Agenda");		 
				 System.out.println(localDate);
				
				 if(localDate.isAfter(hoy))
		        	{
		        		listaClases.add(a);

						 System.out.println("Fecha Guardada");
						 System.out.println(a.getFecha());
		        	}
		
				 	 
			}
			
			
				
		
			
		return listaClases;
		
		
		
>>>>>>> Diana
	}


	@Override
	public List<Long> reemplazarAgenda(Long idAgendaSeleccionada, List<Long> idAgendas, Long idAgendaEditar) {
		idAgendas.removeIf((Long id )-> id == idAgendaEditar);
		idAgendas.add(idAgendaSeleccionada);
		return idAgendas;
	}
<<<<<<< HEAD
/*********************************o r g a n i z a d x r *************************/
	
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
=======
	
	public Long crearAgenda(EstadoDeAgenda estadoDeAgenda, LocalDate desde, LocalDate hasta, Integer horaC, Integer horaF, List<InstructorVehiculoEspecialidad> listaIve){
		List <Agenda> agendas = new ArrayList<Agenda>();
		for(LocalDate date = desde; date.isBefore(hasta); date = date.plusDays(1)){
			for(InstructorVehiculoEspecialidad ive:listaIve){
				for(Integer i=horaC;i<=horaF;i=i+100){
					Agenda ag = new Agenda();
					ag.setFecha(date.toString());
					ag.setHora(i);
					ag.setInstructorVehiculoEspecialidad(ive);
					ag.setEstadoDeAgenda(estadoDeAgenda);
					ag.setClasePagada(false);
					agendas.add(ag);
				}
			}
		}
		Long id=null;
		for(Agenda varAgendas:agendas){
			id = agendaDao.crearAgenda(varAgendas);
		}
		return id;
>>>>>>> Diana
	}


	@Override
<<<<<<< HEAD
	public List<Agenda> traerTodasLasClasesDeUnAlumno(Long id) {
		return agendaDao.traerTodasLasClasesDeUnAlumno(id);
=======
	public Agenda buscarAgendaPorId(Long idAgendaEditar) {
		return agendaDao.buscarAgendaPorId(idAgendaEditar);
>>>>>>> Diana
	}


	@Override
<<<<<<< HEAD
	public void modificarAgenda(Agenda agenda) {
		if(agenda.getEstadoDeAgenda().getEstado().equals("Finalizada")){
			agenda.setClasePagada(true);
		}
		agendaDao.modificarAgenda(agenda);
	}


	@Override
	public Agenda buscarAgendaPorId(Long idAgenda) {
		return agendaDao.buscarAgendaPorId(idAgenda);
	}

	

	

=======
	public Boolean verificarUnaAgendaSePuedaEliminar(Long idAgendaSeleccionada) {
		Agenda agenda=agendaDao.buscarAgendaPorId(idAgendaSeleccionada); 
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
	
>>>>>>> Diana
}

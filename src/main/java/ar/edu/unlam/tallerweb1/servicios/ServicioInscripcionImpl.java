package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.ViewModel.CursosViewModel;
import ar.edu.unlam.tallerweb1.dao.EspecialidadDao;
import ar.edu.unlam.tallerweb1.dao.EstadoDao;
import ar.edu.unlam.tallerweb1.dao.EstadoInscripcionDao;
import ar.edu.unlam.tallerweb1.dao.InscripcionDao;
import ar.edu.unlam.tallerweb1.dao.AgendaDao;
import ar.edu.unlam.tallerweb1.dao.AlumnoDao;
import ar.edu.unlam.tallerweb1.dao.EstadoDeAgendaDao;
import ar.edu.unlam.tallerweb1.dao.CursoDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service("ServicioInscripcion")
@Transactional
public class ServicioInscripcionImpl implements ServicioInscripcion {
	
	@Inject
	private InscripcionDao inscripcionDao;
	@Inject
	private EstadoInscripcionDao estadoinscripcionDao;
	@Inject
	private EspecialidadDao especialidadDao;
	@Inject
	private AgendaDao agendaDao;
	@Inject
	private CursoDao  cursoDao;
	@Inject
	private AlumnoDao alumnoDao;
	@Inject
	private EstadoDeAgendaDao estadoDeAgendaDao;
	@Inject
	private ServicioAgenda servicioAgenda;
	@Inject
	private ServicioEstadoDeAgenda servicioEstadoDeAgenda;
	@Inject
	private ServicioNotificacion servicioNotificacion;
	
	
	
	/********************************** ALUMNO **************************************************/
	@Override
	public List<Inscripcion> saberSiEstaRealizandoAlgunCurso(Long idAlumno, EstadoInscripcion estado) {
		
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		
		return inscripcionDao.saberSiEstaRealizandoAlgunCurso(alumno.getId(), estado);
	}
	
	
	
	@Override
	public List<Inscripcion> consultarSiYaSeInscribioAUnCurso(Long idAlumno, Curso cursoElegido) {
		
		
			Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
			//Traigo los datos del alumno logueado
			Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
			
		//Busco el id del estado que dice "Cursando"
		/**/ EstadoInscripcion estado = estadoinscripcionDao.buscarEstadoCursando();
		
		//Buscar la especialidad del curso elegido
		/**/ Especialidad especialidad = especialidadDao.consultarEspecialidadCursoElegido(cursoElegido);
		
		
		
		return inscripcionDao.consultarSiYaSeInscribioAUnCurso( alumno.getId(), estado, especialidad);
	}



	@Override
	public void guardarInscripcionEnLaAgendaYEnInscripcion(Long idAlumno, Curso curso, List<Long> idAgendasDepurado) {

		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		
		// Datos de las agendas elegidas buscamos objetos agenda con los id de agendas
		List<Agenda> listaAgendas  = new ArrayList();
		
		Inscripcion Tablainscripcion =new Inscripcion(); 
		
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion estado = estadoinscripcionDao.buscarEstadoCursando();
		 inscripcionDao.guardarInscripcion(alumno, curso, Tablainscripcion, estado);//alumnoInscripcionDao
		
		
		for(Long id: idAgendasDepurado){
			Agenda agendaBuscada = agendaDao.buscarAgendasElegidas(id, curso);//alumnoAgendaDao
			listaAgendas.add(agendaBuscada);
		}
		
		
		Inscripcion inscripcionBuscada = inscripcionDao.buscarInscripcion(alumno.getId(), curso.getId());//alumnoInscripcionDao
		
		EstadoDeAgenda estadoOcupado = estadoDeAgendaDao.buscarEstadoOcupado();
		
		// guardamos los objetos agenda buscados
		for(Agenda a: listaAgendas)
		{
			a.setEstadoDeAgenda(estadoOcupado);
			a.setInscripcion(inscripcionBuscada);
			inscripcionDao.guardarInscripcionEnLaAgenda(a);//alumnoInscripcionDao
	
		}

	}



	@Override
	public List<Inscripcion> traerLosCursosEnQueSeEncuentraAnotado(Long idAlumno, EstadoInscripcion estado) {
				
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		return inscripcionDao.traerLosCursosEnQueSeEncuentraAnotado(alumno.getId(), estado);
	}



	@Override
	public Inscripcion buscarCursoAEliminar(Long idEspecialidad, Long idAlumno) {
		
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		
		return inscripcionDao.buscarCursoAEliminar( idEspecialidad,  alumno.getId());
	}



	@Override
	public void eliminarInscripcionDelAlumnoYSusClasesDelCurso(Long idCurso, Long idAlumno) {
		
						/*Eliminar las clases de la agenda del alumno tal y de la inscripcion tal*/
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		
		Inscripcion inscripcionBuscada = inscripcionDao.cursoQueQuieroEliminar(idCurso, alumno.getId());
		
		
		System.out.println("INSCRIPCION");
		System.out.println(inscripcionBuscada.getId());
		
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion inscripcionEstadoCursando = estadoinscripcionDao.buscarEstadoCursando();
			
		
		//Estados
			EstadoDeAgenda ocupada = estadoDeAgendaDao.traigoElEstadoOcupada();
			
			EstadoDeAgenda canceladaPorAlumno = estadoDeAgendaDao.traigoElEstadoCanceladaPorAlumno();
			
		//Trae todas las clases a eliminar
		TreeSet<Agenda> misClases = agendaDao.traerTodasLasClasesParaEliminarYCrearlasEnLimpio(alumno.getId(), inscripcionBuscada.getCurso().getEspecialidad().getId(), inscripcionEstadoCursando, ocupada);
	
		
		System.out.println("Cant clases");
		for(Agenda d: misClases){
		System.out.println(d.getFecha());
		}
		
		//Creo las mismas clases para que la puedan ocupar otros alumnos con estado disponible
		List<Agenda> clasesNuevas = new ArrayList<Agenda>();
		
		//Estados
		EstadoDeAgenda disponible = estadoDeAgendaDao.traigoElEstadoDisponible();

		//Valido que no se creen dos veces la misma clase
	
		TreeSet<Agenda> clasesACrear = new TreeSet<Agenda>();
		
		for(Agenda ag: misClases)
		{
			List<Agenda> agendaLimpia = agendaDao.validoQueNoSeCreenDosVecesLaMismaClase(ag, disponible);
		
			if(agendaLimpia.isEmpty())
			{
				clasesACrear.add(ag);
			}
		}
		
		
		
		//Creo clases nuevas
		for(Agenda a: clasesACrear)
		{
			Agenda clases = new Agenda();
		
			clases.setClasePagada(false);
			clases.setEstadoDeAgenda(disponible);
			clases.setFecha(a.getFecha());
			clases.setHora(a.getHora());
			clases.setInscripcion(null);
			clases.setInstructorVehiculoEspecialidad(a.getInstructorVehiculoEspecialidad());
				
			
			clasesNuevas.add(clases);
			
		}
		
		
		//Guardas la misma clase pero disponible para otro alumno
		for(Agenda NuevasClases: clasesNuevas)
		{
			
			 agendaDao.crearAgenda(NuevasClases); //Es un save(agenda)
		}
		
		
		
		
		//Elimino las clases que ya estaban
				for(Agenda a: misClases)
				{
				
				a.setClasePagada(false);
				a.setEstadoDeAgenda(canceladaPorAlumno);
				
				inscripcionDao.guardarInscripcionEnLaAgenda(a); //Update
				
				servicioNotificacion.crearNotificacion(usuario, a);
				}
		
				
				
				
	

		
	}



	@Override
	public Inscripcion buscarInscripcion( Long idAlumno, Long idCurso) {
	 
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
				
		return inscripcionDao.buscarInscripcion( alumno.getId(), idCurso);
	}

	
	
	
//	@Override
//	public void verificarEstadoDeLasClases(Long idAlumno){
//		
//		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
//		//Traigo los datos del alumno logueado
//		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
//		
//				
//		//Busco el id del estado que dice "Cursando"
//		EstadoInscripcion estadoCursando = estadoinscripcionDao.buscarEstadoCursando();
//		
//		List<Inscripcion> misInscripciones = inscripcionDao.traerLosCursosEnQueSeEncuentraAnotado(alumno.getId(), estadoCursando);
//			
//		
//		List<Agenda> clasesHechas = new ArrayList<Agenda>();
//		
//		EstadoDeAgenda finalizado = estadoDeAgendaDao.traigoElEstadoFinalizado();
//		EstadoDeAgenda perdida = estadoDeAgendaDao.buscarEstadoPerdida();
//		
//		for(Inscripcion ins: misInscripciones)
//			{
//			
//				List<Agenda> todasMisClases = agendaDao.buscarAgendasDeUnSoloCurso(ins);
//				
//				
//				for(Agenda misClases: todasMisClases)
//				{
//					
//					
//					if(misClases.getEstadoDeAgenda().equals(finalizado) || misClases.getEstadoDeAgenda().equals(perdida))
//					{
//						
//						clasesHechas.add(misClases);
//					}
//					
//					
//				}
//				
//				
//			}
//		
//	}
//	
//	
//	for(Inscripcion ins: misInscripciones)
//	{
//		
//	}
//	


	@Override
	public void finalizarCursoDelAlumno(Long idAlumno,Long idCurso ) {
			
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		
			 System.out.println("PARAMETRO IDALUMNO"+alumno.getId());
			 System.out.println("PARAMETRO idCURSOO"+idCurso);
			 
			 
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion inscripcionEstadoCursando = estadoinscripcionDao.buscarEstadoCursando();
		 System.out.println("IIIIIIInscripcionEstadoCursando "+inscripcionEstadoCursando.getId());
			
		
		
		Inscripcion inscripcionBuscada = inscripcionDao.buscarInscripcion( alumno.getId(), idCurso);
		
		System.out.println("INSCRIPCIOooooN "+  inscripcionBuscada.getId());
		
		
			
		
		//Trae todas las clases a eliminar
		TreeSet<Agenda> misClases = agendaDao.traerTodasLasClasesAEliminarDeUnaSolaEspecialidad(alumno.getId(), inscripcionBuscada.getId(),inscripcionEstadoCursando);
	
		
		/*System.out.println("Cant clases");
		for(Agenda d: misClases){
		System.out.println(d.getFecha());
		}*/
		
		//Creo las mismas clases para que la puedan ocupar otros alumnos con estado disponible
		List<Agenda> clasesNuevas = new ArrayList<Agenda>();
		
		//Estados
		EstadoDeAgenda finalizado = estadoDeAgendaDao.traigoElEstadoFinalizado();
		EstadoDeAgenda disponible = estadoDeAgendaDao.traigoElEstadoDisponible();
			
		
		
		//Valido que no se creen dos veces la misma clase
		
			TreeSet<Agenda> clasesACrear = new TreeSet<Agenda>();
			
			for(Agenda ag: misClases)
			{
				
				
				List<Agenda> agendaLimpia = agendaDao.validoQueNoSeCreenDosVecesLaMismaClase(ag, disponible);
				
				
				
				if(agendaLimpia.isEmpty())
				{
				clasesACrear.add(ag);
				}
			}
		
		
		//Creo clases nuevas
		for(Agenda a: clasesACrear)
		{
		
			Agenda clases = new Agenda();
			
		
			clases.setClasePagada(false);
			clases.setEstadoDeAgenda(disponible);
			clases.setFecha(a.getFecha());
			clases.setHora(a.getHora());
			clases.setInscripcion(null);
			clases.setInstructorVehiculoEspecialidad(a.getInstructorVehiculoEspecialidad());
				
			
			clasesNuevas.add(clases);
			
		
		}
		
		
		//Guardas la misma clase pero disponible para otro alumno
		for(Agenda NuevasClases: clasesNuevas)
		{
			
			 agendaDao.crearAgenda(NuevasClases); //Es un save(agenda)
		}
		
		
		//Estados
		EstadoDeAgenda abandonada = estadoDeAgendaDao.traigoElEstadoAbandonada();
		//Estados
		EstadoDeAgenda ocupada = estadoDeAgendaDao.traigoElEstadoOcupada();
				
				
				
				
		//Valido para mantener los estados de las clases y finalizar las que estaban ocupadas		
	
		TreeSet<Agenda> clasesQueMantienenElEstado = new TreeSet<Agenda>();
		
		
		for(Agenda a: misClases)
			{
			//Traigo todas las clases con estado ocupada
			TreeSet<Agenda> claseEstadoOcupada = agendaDao.traigoSoloLasClasesConEstadoOcupada(a,ocupada);
			
				for(Agenda b: claseEstadoOcupada)
				{
					clasesQueMantienenElEstado.add(b);
				}
			}
		
		
		//Recorro TreSeet para saber cuales son Ocupada y cuales no para no alterar su estado
		for(Agenda c: misClases)
		{
			
			if(c.getEstadoDeAgenda().getId().equals(ocupada.getId()))
			{
				
				c.setEstadoDeAgenda(abandonada);
		
				inscripcionDao.guardarInscripcionEnLaAgenda(c);
			
			}else{
					
				inscripcionDao.guardarInscripcionEnLaAgenda(c);
				 }
			
		}
			
			
			

		EstadoInscripcion inscripcionFinalizada = estadoinscripcionDao.buscarEstadoFinalizado();
		
		inscripcionBuscada.setAlumno(alumno);
		inscripcionBuscada.setCurso(inscripcionBuscada.getCurso());
		inscripcionBuscada.setEstadoInscripcion(inscripcionFinalizada);
		
		
		inscripcionDao.eliminarInscripcionDelAlumno(inscripcionBuscada);
		
		
	}



	@Override
	public void agregarInscripcion(Long idAlumno, Curso curso, Long idAgendaEditar) {
		
	
		
		Usuario usuario = alumnoDao.buscarUsuario(idAlumno);	
		//Traigo los datos del alumno logueado
		Alumno alumno = alumnoDao.buscarAlumno(usuario.getAlumno().getId());
		
		Agenda agenda =servicioAgenda.buscarAgendaPorId(idAgendaEditar);
		Inscripcion inscripcion = inscripcionDao.buscarInscripcion(alumno.getId(),curso.getId());
		EstadoDeAgenda estado =servicioEstadoDeAgenda.traerEstadoDeAgendaPorNombre("Ocupada");
		agenda.setInscripcion(inscripcion);
		agenda.setEstadoDeAgenda(estado);
		inscripcionDao.guardarInscripcionEnLaAgenda(agenda);
	}






/*****************MOCK*****************/

public void setInscripcionDao(InscripcionDao inscripcionDao) {
	this.inscripcionDao = inscripcionDao;
}

	

	
}
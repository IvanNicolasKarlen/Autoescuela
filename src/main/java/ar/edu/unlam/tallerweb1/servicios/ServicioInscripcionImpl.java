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
	
	
	/********************************** ALUMNO **************************************************/
	@Override
	public List<Inscripcion> saberSiEstaRealizandoAlgunCurso(Long idAlumno) {
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion estado = estadoinscripcionDao.buscarEstadoCursando();//alumnoEstadoDao
		 
		 
		
		return inscripcionDao.saberSiEstaRealizandoAlgunCurso(idAlumno, estado);
	}
	
	
	
	@Override
	public List<Inscripcion> consultarSiYaSeInscribioAUnCurso(Long idAlumno, Curso cursoElegido) {
		
		
		//Busco el id del estado que dice "Cursando"
		/**/ EstadoInscripcion estado = estadoinscripcionDao.buscarEstadoCursando();
		
		//Buscar la especialidad del curso elegido
		/**/ Especialidad especialidad = especialidadDao.consultarEspecialidadCursoElegido(cursoElegido);
		//alumnoEspecialidadDao
		
		
		return inscripcionDao.consultarSiYaSeInscribioAUnCurso( idAlumno, estado, especialidad);
	}



	@Override
	public void guardarInscripcionEnLaAgendaYEnInscripcion(Alumno alumno, Curso curso, List<Long> idAgendasDepurado) {

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
		
		
		Inscripcion inscripcionBuscada = inscripcionDao.buscarInscripcion(alumno, curso);//alumnoInscripcionDao
		
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
	public List<Inscripcion> traerLosCursosEnQueSeEncuentraAnotado(Long idAlumno) {
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion estado = estadoinscripcionDao.buscarEstadoCursando();
				
		 
		return inscripcionDao.traerLosCursosEnQueSeEncuentraAnotado(idAlumno, estado);
	}



	@Override
	public Inscripcion buscarCursoAEliminar(Long idEspecialidad, Long idAlumno) {
		// 
		return inscripcionDao.buscarCursoAEliminar( idEspecialidad,  idAlumno);
	}



	@Override
	public void eliminarInscripcionDelAlumnoYSusClasesDelCurso(Long idCurso, Long idAlumno) {
		
						/*Eliminar las clases de la agenda del alumno tal y de la inscripcion tal*/
		
		
		Inscripcion inscripcionBuscada = inscripcionDao.cursoQueQuieroEliminar(idCurso, idAlumno);
		
		
		System.out.println("INSCRIPCION");
		System.out.println(inscripcionBuscada.getId());
		
		
		
		
		//Trae todas las clases a eliminar
		TreeSet<Agenda> misClases = agendaDao.traerTodasLasClasesDeUnaSolaEspecialidad(idAlumno, inscripcionBuscada.getId());
	
		
		System.out.println("Cant clases");
		for(Agenda d: misClases){
		System.out.println(d.getFecha());
		}
		
		//Creo las mismas clases para que la puedan ocupar otros alumnos con estado disponible
		List<Agenda> clasesNuevas = new ArrayList<Agenda>();
		
		//Estados
		EstadoDeAgenda canceladaPorAlumno = estadoDeAgendaDao.traigoElEstadoCanceladaPorAlumno();
		EstadoDeAgenda disponible = estadoDeAgendaDao.traigoElEstadoDisponible();
			
		
		
		//Creo clases nuevas
		for(Agenda a: misClases)
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
			
			 agendaDao.guardarClaseQueEliminoElAlumnoParaQueSePuedaInscribirOtroAlumno(NuevasClases); //Es un save(agenda)
		}
		
		
		
		
		//Elimino las clases que ya estaban
				for(Agenda a: misClases)
				{
				a.setInscripcion(null);
				a.setClasePagada(false);
				a.setEstadoDeAgenda(canceladaPorAlumno);
				
				inscripcionDao.guardarInscripcionEnLaAgenda(a); //Update
				
				}
		
	
								/*Eliminar la inscripcion del alumno tal con el curso tal*/
		 
		Alumno alumno = alumnoDao.buscarAlumno( idAlumno);
		
		Inscripcion inscripcionEliminar = inscripcionDao.buscarInscripcion(alumno, inscripcionBuscada.getCurso());
		
		
		inscripcionEliminar.setAlumno(null);
		inscripcionEliminar.setCurso(null);
		inscripcionEliminar.setEstadoInscripcion(null);
		
		
		inscripcionDao.eliminarInscripcionDelAlumno(inscripcionEliminar);
		
	}



	@Override
	public Inscripcion buscarInscripcion(Curso curso, Alumno alumno) {
		return inscripcionDao.buscarInscripcion(alumno, curso);
	}



	@Override
	public void agregarInscripcion(Alumno alumno, Curso curso, Long idAgendaEditar) {
		Agenda agenda =servicioAgenda.buscarAgendaPorId(idAgendaEditar);
		Inscripcion inscripcion = inscripcionDao.buscarInscripcion(alumno,curso);
		EstadoDeAgenda estado =servicioEstadoDeAgenda.traerEstadoDeAgendaPorNombre("Ocupada");
		agenda.setInscripcion(inscripcion);
		agenda.setEstadoDeAgenda(estado);
		inscripcionDao.guardarInscripcionEnLaAgenda(agenda);
		
	}



	@Override
	public void guardarInscripcionEnLaAgenda(Agenda agenda) {
		inscripcionDao.guardarInscripcionEnLaAgenda(agenda);
		
	}


	
}

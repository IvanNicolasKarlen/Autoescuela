package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import java.util.TreeSet;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface AgendaDao {
	
	
	List<Agenda> traerAgendaPorFechayHora(String fecha, Integer hora);
	List<Agenda> traerTodasLasClasesDeUnAlumno(Long id);
	Agenda buscarAgendaPorId(Long idAgenda);
	/************************ORGANIZADOR**************************************/
	Long crearAgenda(Agenda agenda);
	Agenda traerAgendaPorFechaYAlumno(Alumno alumno, String fecha);
	Agenda traerAgendaPorFechaHoraInstructor(String fecha, Integer hora, Instructor instructor);


	
	/*********************** INSTRUCTOR *********************************/
	List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor);
	List<Agenda> buscarAlumnos(Long idInstructor,String nombreDeUsuario);
	List<Agenda> traerFechasDisponibles(Long idInstructor);
	Agenda buscarAgenda(Agenda agenda);
	List<Agenda> traerFechas();
	void updateAgenda(Agenda agenda);	
	
	
	/******************************************************************/
	/***************************** Alumno 
	 * @param disponible 
	 * @param disponible 
	 * @param string *******************************/

	TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso curso, Long idAlumno, EstadoDeAgenda disponible);


	Agenda buscarAgendasElegidas(Long a, Curso curso);

	List<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno, EstadoInscripcion estado);
	
	TreeSet<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(Long c, EstadoInscripcion estado, Long idAlumno);

	List<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idAlumno, Long idEspecialidad, EstadoInscripcion estado);

	Agenda traerClaseQueQuiereEliminar(Long idAgendaSeleccionado, Long idAlumno);


	List<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas);




	Agenda traerClaseQueQuiereEliminarParaAgregarlaEnLimpio(Long idAgendaSeleccionado, Long idAlumno);


	TreeSet<Agenda> traerTodasLasClasesAEliminarDeUnaSolaEspecialidad(Long idAlumno, Long id,
			EstadoInscripcion inscripcionEstadoCursando);


	List<Agenda> validoQueNoSeCreenDosVecesLaMismaClase(Agenda a, EstadoDeAgenda disponible);


	TreeSet<Agenda> traerTodasLasClasesParaEliminarYCrearlasEnLimpio(Long idAlumno, Long id,
			EstadoInscripcion inscripcionEstadoCursando, EstadoDeAgenda ocupada);


	TreeSet<Agenda> traigoSoloLasClasesConEstadoOcupada(Agenda a, EstadoDeAgenda ocupada);


	List<Agenda> buscarAgendasDeUnSoloCurso(Inscripcion ins);
	


	/*************MOCK**********/
	

	void setSessionFactory(SessionFactory sessionFactory);
	

	
	/*******************************************************************/
}
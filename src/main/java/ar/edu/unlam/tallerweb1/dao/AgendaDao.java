package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Instructor;

public interface AgendaDao {
	
	
	
	List<Agenda> traerAgendaPorFechayHora(String fecha, Integer hora);
	List<Agenda> traerTodasLasClasesDeUnAlumno(Long id);
	void modificarAgenda(Agenda agenda);
	Agenda buscarAgendaPorId(Long idAgenda);
	
	/************************ORGANIZADOR**************************************/
	Long crearAgenda(Agenda agenda);
	Agenda traerAgendaPorFechaYAlumno(Alumno alumno, String fecha);
	Agenda traerAgendaPorFechaHoraInstructor(String fecha, Integer hora, Instructor instructor);


	
	/*********************** INSTRUCTOR *********************************/
	List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor);
	List<Agenda> buscarAlumnos(String nombre,String apellido);
	List<Agenda> traerFechasDisponibles();
//	List<Agenda> traerFechas();
	void updateEstadoDeAgenda(Agenda agenda);	
	
	
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

	void eliminarClaseDeLaAgenda(Agenda agenda);

	List<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas);


	void guardarClaseQueEliminoElAlumnoParaQueSePuedaInscribirOtroAlumno(Agenda agenda);


	Agenda traerClaseQueQuiereEliminarParaAgregarlaEnLimpio(Long idAgendaSeleccionado, Long idAlumno);


	TreeSet<Agenda> traerTodasLasClasesAEliminarDeUnaSolaEspecialidad(Long idAlumno, Long id,
			EstadoInscripcion inscripcionEstadoCursando);


	List<Agenda> validoQueNoSeCreenDosVecesLaMismaClase(Agenda a, EstadoDeAgenda disponible);


	TreeSet<Agenda> traerTodasLasClasesParaEliminarYCrearlasEnLimpio(Long idAlumno, Long id,
			EstadoInscripcion inscripcionEstadoCursando, EstadoDeAgenda ocupada);


	TreeSet<Agenda> traigoSoloLasClasesConEstadoOcupada(Agenda a, EstadoDeAgenda ocupada);

	List<Agenda> buscarAgendasDeUnSoloCurso(Inscripcion ins);
	


	
	/*******************************************************************/
}

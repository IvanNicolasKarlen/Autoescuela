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
	void updateEstadoDeAgenda(Agenda agenda);	
	Agenda buscarAgenda(Agenda agenda);
	List<Agenda> traerFechas();
	
	/******************************************************************/
	/***************************** Alumno 
	 * @param disponible *******************************/
	TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso curso, EstadoDeAgenda disponible);

	Agenda buscarAgendasElegidas(Long a, Curso curso);

	TreeSet<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno, EstadoInscripcion estado);
	
	TreeSet<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(Long c, EstadoInscripcion estado, Long idAlumno);

	TreeSet<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idAlumno, Long idEspecialidad, EstadoInscripcion estado);

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

	
	/*******************************************************************/
}
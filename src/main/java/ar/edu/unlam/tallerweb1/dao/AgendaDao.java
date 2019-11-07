package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface AgendaDao {
	
	/************************ORGANIZADOR**************************************/
	Long crearAgenda(Agenda agenda);
	
	
	/*********************** INSTRUCTOR *********************************/
	List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor);

	
	
	/******************************************************************/
	/***************************** Alumno 
	 * @param disponible *******************************/
	TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso curso, EstadoDeAgenda disponible);

	Agenda buscarAgendasElegidas(Long a, Curso curso);

	TreeSet<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno);
	
	TreeSet<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(Long c, EstadoInscripcion estado, Long idAlumno);

	TreeSet<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idEspecialidad, Long idAlumno);

	Agenda traerClaseQueQuiereEliminar(Long idAgendaSeleccionado, Long idAlumno);

	void eliminarClaseDeLaAgenda(Agenda agenda);

	TreeSet<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas);


	void guardarClaseQueEliminoElAlumnoParaQueSePuedaInscribirOtroAlumno(Agenda agenda);


	Agenda traerClaseQueQuiereEliminarParaAgregarlaEnLimpio(Long idAgendaSeleccionado, Long idAlumno);


	Agenda buscarAgendaPorId(Long idAgendaEditar);

	
	/*******************************************************************/
}

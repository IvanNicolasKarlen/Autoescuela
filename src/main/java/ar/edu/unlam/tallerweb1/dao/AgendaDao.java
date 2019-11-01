package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface AgendaDao {
	
	/************************ORGANIZADOR**************************************/
	Long crearAgenda(Agenda agenda);
	
	
	/*********************** INSTRUCTOR *********************************/
	List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor);

	
	
	/******************************************************************/
	/***************************** Alumno *******************************/
	TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso curso);

	Agenda buscarAgendasElegidas(Long a, Curso curso);

	List<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno);
	
	List<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(Long c, EstadoInscripcion estado, Long idAlumno);

	List<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idEspecialidad, Long idAlumno);

	Agenda traerClaseQueQuiereEliminar(Long idAgendaSeleccionado, Long idAlumno);

	void eliminarClaseDeLaAgenda(Agenda agenda);

	List<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas);


	List<Agenda> traerAgendaPorFechayHora(String fecha, Integer hora);


	
	/*******************************************************************/
}

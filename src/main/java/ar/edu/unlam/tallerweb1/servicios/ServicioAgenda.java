package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.ViewModel.CursosViewModel;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;

public interface ServicioAgenda {

	
	/******************************INSTRUCTOR******************************/
	List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor);
	List<Agenda> buscarAlumnos(String nombre,String apellido);
	List<Agenda> traerFechasDisponibles();
//	LocalDate traerFechas();
	void updateEstadoDeAgenda(Agenda agenda);
	Agenda buscarAgendaPorId(Long idAgenda);
	/******************************Alumno
	 * @param long1 ******************************/

	TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso Curso, Long idAlumno);

	Boolean constatarQueNadieSeAnotaraEnLasFechasAsignadas(List<Long> idAgendasDepurado, Curso curso);

	List<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno, EstadoInscripcion estado);
	
	TreeSet<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(CursosViewModel cursosViewModel,Long idAlumno);

	List<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idEspecialidad, Long idAlumno, EstadoInscripcion estado);

	Agenda traerClaseQueQuiereEliminar(Long idAgendaSeleccionado, Long idAlumno);

	void eliminarClaseDeLaAgenda(Long idAgendaSeleccionado, Long idAlumno);

	List<Agenda> buscarAgendasElegidas(List<Long> idAgendasDepurado, Curso curso);

	List<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas, Long idAlumno);

	List<Long> reemplazarAgenda(Long idAgendaSeleccionada, List<Long> idAgendasDepurado, Long idAgendaEditar);

	void modificarAgenda(Agenda agenda);
	
	Boolean verificarUnaAgendaSePuedaEliminar(Long idAgendaSeleccionada);
	
	List<Agenda> traerClasesQueEsteCursando(List<Agenda> clasesDeUnSoloCurso);
	
	
	/***********************************ORGANIZADOR*********************************/
	/********************************************************************/
	Boolean crearAgenda(EstadoDeAgenda estadoDeAgenda, LocalDate desde, LocalDate hasta, Integer horaC, Integer horaF, List<InstructorVehiculoEspecialidad> listaIve);
	List<Agenda> traerAgendaPorFechayHora(String fecha, Integer hora);
	Agenda traerAgendaPorFechaYAlumno(Alumno alumno, String fecha);


	List<Agenda> traerTodasLasClasesDeUnAlumno(Long id);
	
	

	
	
	
}

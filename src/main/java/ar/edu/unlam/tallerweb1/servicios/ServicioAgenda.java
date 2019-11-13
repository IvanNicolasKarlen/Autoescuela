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
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;

public interface ServicioAgenda {
	/******************************INSTRUCTOR******************************/
	List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor);
	List<Agenda> buscarAlumnos(String nombre,String apellido);
	List<Agenda> traerFechasDisponibles();
	void updateEstadoDeAgenda(Agenda agenda);
	Agenda buscarAgendaPorId(Long idAgenda);
	Agenda buscarAgenda(Agenda agenda);
	List <Agenda> traerAgendaInstructor(Long idInstructor);
	List <Integer> horasTrabajadas(Long idInstructor);
	
	/******************************Alumno******************************/
	TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso curso);

	Boolean constatarQueNadieSeAnotaraEnLasFechasAsignadas(AgendasViewModel agendasViewModel, Curso curso);

	List<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno);
	
	List<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(CursosViewModel cursosViewModel,Long idAlumno);

	List<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idEspecialidad, Long idAlumno);

	Agenda traerClaseQueQuiereEliminar(Long idAgendaSeleccionado, Long idAlumno);

	void eliminarClaseDeLaAgenda(Long idAgendaSeleccionado, Long idAlumno);

	List<Agenda> buscarAgendasElegidas(List<Long> idAgendasDepurado, Curso curso);

	List<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas);

	List<Long> reemplazarAgenda(Long idAgendaSeleccionada, List<Long> idAgendasDepurado, Long idAgendaEditar);

	
	/********************************************************************/
	Long crearAgenda(EstadoDeAgenda estadoDeAgenda, LocalDate desde, LocalDate hasta, Integer horaC, Integer horaF, List<InstructorVehiculoEspecialidad> listaIve);

	
}

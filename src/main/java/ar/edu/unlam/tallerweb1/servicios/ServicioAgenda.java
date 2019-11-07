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

	List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor);

	
	/******************************Alumno******************************/
	TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso curso);

	Boolean constatarQueNadieSeAnotaraEnLasFechasAsignadas(AgendasViewModel agendasViewModel, Curso curso);

	TreeSet<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno);
	
	TreeSet<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(CursosViewModel cursosViewModel,Long idAlumno);

	TreeSet<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idEspecialidad, Long idAlumno);

	Agenda traerClaseQueQuiereEliminar(Long idAgendaSeleccionado, Long idAlumno);

	void eliminarClaseDeLaAgenda(Long idAgendaSeleccionado, Long idAlumno);

	List<Agenda> buscarAgendasElegidas(List<Long> idAgendasDepurado, Curso curso);

	TreeSet<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas);

	List<Long> reemplazarAgenda(Long idAgendaSeleccionada, List<Long> idAgendasDepurado, Long idAgendaEditar);

	Agenda buscarAgendaPorId(Long idAgendaEditar);

	Boolean verificarUnaAgendaSePuedaEliminar(Long idAgendaSeleccionada);


	/********************************************************************/
	Long crearAgenda(EstadoDeAgenda estadoDeAgenda, LocalDate desde, LocalDate hasta, Integer horaC, Integer horaF, List<InstructorVehiculoEspecialidad> listaIve);



	
}

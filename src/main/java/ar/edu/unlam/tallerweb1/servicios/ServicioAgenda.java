package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.ViewModel.CursosViewModel;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface ServicioAgenda {

	List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor);

	
	/******************************Alumno******************************/
	TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso curso);

	Boolean constatarQueNadieSeAnotaraEnLasFechasAsignadas(AgendasViewModel agendasViewModel, Curso curso);

	List<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno);
	
	List<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(CursosViewModel cursosViewModel,Long idAlumno);

	List<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idEspecialidad, Long idAlumno);

	Agenda traerClaseQueQuiereEliminar(Long idAgendaSeleccionado, Long idAlumno);

	void eliminarClaseDeLaAgenda(Long idAgendaSeleccionado, Long idAlumno);

	
	/********************************************************************/

	
}

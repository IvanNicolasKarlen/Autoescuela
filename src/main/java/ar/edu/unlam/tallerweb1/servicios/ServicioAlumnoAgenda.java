package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;

public interface ServicioAlumnoAgenda {
	
	
	TreeSet<Agenda> traerAgendasDisponibles(Curso curso);
	
	TreeSet<Agenda> eliminarLasAgendasConFechasDuplicadas(List<Agenda> agendas);

	TreeSet<Agenda> eliminarAgendasQueSuperanLaCantidadDeClasesDelCurso(TreeSet<Agenda> agendasSinDuplicados, Curso curso);

	Boolean constatarQueNadieSeAnotaraEnLasFechasAsignadas(AgendasViewModel agendasViewModel, Curso curso);

	List<Agenda> buscarAgendasElegidas(List<Long> idAgendasDepurado, Curso curso);

}

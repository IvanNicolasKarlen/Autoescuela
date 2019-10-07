package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;

public interface ServicioAlumnoAgenda {
	
	
	List<Agenda> traerAgendasDisponibles();
	
	TreeSet<Agenda> eliminarLasAgendasConFechasDuplicadas(List<Agenda> agendas);

	TreeSet<Agenda> eliminarAgendasQueSuperanLaCantidadDeClasesDelCurso(TreeSet<Agenda> agendasSinDuplicados, Curso curso);

}

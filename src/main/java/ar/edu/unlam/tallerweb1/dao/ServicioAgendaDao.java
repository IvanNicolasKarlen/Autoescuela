package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;

public interface ServicioAgendaDao {
	List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor);
	
	List<Alumno> buscarNombreyApellidoDeAlumnosDeUnInstructor(Long idInstructor);
}

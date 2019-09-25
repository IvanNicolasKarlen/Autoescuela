package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;

public interface ServicioAgenda {

	List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor);
	
	List <Alumno> buscarNombreyApellidoDeAlumnosDeUnInstructor(Long idInstructor);
}

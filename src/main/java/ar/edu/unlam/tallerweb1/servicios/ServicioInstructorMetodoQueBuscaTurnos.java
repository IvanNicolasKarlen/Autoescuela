package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Curso;

public interface ServicioInstructorMetodoQueBuscaTurnos {

	List<Agenda> buscarTurnos(Long idInstructor);

}

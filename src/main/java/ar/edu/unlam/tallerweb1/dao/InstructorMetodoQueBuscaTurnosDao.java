package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;


public interface InstructorMetodoQueBuscaTurnosDao {

	
	List<Agenda> buscarTurnos(Long idInstructor);
}

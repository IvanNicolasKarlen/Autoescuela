package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;

public interface AlumnoAgendaDao {
	TreeSet<Agenda> traerAgendasDisponibles(Curso curso);

	Agenda buscarAgendasElegidas(Long a, Curso curso);


}

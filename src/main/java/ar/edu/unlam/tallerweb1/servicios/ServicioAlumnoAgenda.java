package ar.edu.unlam.tallerweb1.servicios;

import java.util.TreeSet;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;

public interface ServicioAlumnoAgenda {
	TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso curso);
	void guardarAlumnoConSuCursoElegidoEnLaAgenda(TreeSet<Agenda> agenda, Alumno alumno, Curso cursoElegido);

}

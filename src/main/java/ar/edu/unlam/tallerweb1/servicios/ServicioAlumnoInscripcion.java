package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface ServicioAlumnoInscripcion {

	List<Curso> buscarCursos();

	Curso buscarCurso(Long long1);

	List<Inscripcion> consultarSiYaSeInscribioAUnCurso(Long idAlumno, EstadoInscripcion estado,
			Especialidad especialidad);
	
	void guardarInscripcion(Alumno alumno, Curso curso, Inscripcion tablainscripcion, EstadoInscripcion estado);

	Inscripcion buscarInscripcion(Alumno alumno, Curso curso);

	//void guardarInscripcionEnLaAgenda(TreeSet<Agenda> agendasListas, Inscripcion inscripcion);

	void guardarInscripcionEnLaAgenda(List<Agenda> agendasElegidas, Inscripcion inscripcion);




}

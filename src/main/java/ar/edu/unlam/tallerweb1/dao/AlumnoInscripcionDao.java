package ar.edu.unlam.tallerweb1.dao;

import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface AlumnoInscripcionDao {

	List<Curso> buscarCursos();

	Curso buscarCurso(Long cursoElegido);

	List<Inscripcion> consultarSiYaSeInscribioAUnCurso(Long idAlumno, EstadoInscripcion estado, Especialidad especialidad);

	void guardarInscripcion(Alumno alumno, Curso curso, Inscripcion tablaInscripcion, EstadoInscripcion estado);

	Inscripcion buscarInscripcion(Alumno alumno, Curso curso);

	void guardarInscripcionEnLaAgenda(Agenda a);

	List<Inscripcion> saberSiEstaRealizandoAlgunCurso(Long idAlumno, EstadoInscripcion estado);


}

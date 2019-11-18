package ar.edu.unlam.tallerweb1.dao;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface InscripcionDao {

	/***************************** Alumno ************************************/
	List<Inscripcion> saberSiEstaRealizandoAlgunCurso(Long idAlumno, EstadoInscripcion estado);

	List<Inscripcion> consultarSiYaSeInscribioAUnCurso(Long idAlumno, EstadoInscripcion estado,
			Especialidad especialidad);
	
	void guardarInscripcion(Alumno alumno, Curso curso, Inscripcion tablainscripcion, EstadoInscripcion estado);
	
	//Inscripcion buscarInscripcion(Alumno alumno, Curso curso);

	void guardarInscripcionEnLaAgenda(Agenda a);

	List<Inscripcion> traerLosCursosEnQueSeEncuentraAnotado(Long idAlumno, EstadoInscripcion estado);

	Inscripcion buscarCursoAEliminar(Long idCurso, Long idAlumno);

	void eliminarInscripcionDelAlumno(Inscripcion inscripcionEliminar);

	Inscripcion buscarCursoAEliminarDelAlumno(Long idAlumno, Long idEspecialidad);

	Inscripcion cursoQueQuieroEliminar(Long idCurso, Long idAlumno);

	Inscripcion buscarInscripcion(Long idAlumno, Long idCurso);
	
	
	/****************************************************************/

	

	

}
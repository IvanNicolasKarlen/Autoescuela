package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.ViewModel.CursosViewModel;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface ServicioInscripcion {

	
	/************************** Alumno *****************************************/
	List<Inscripcion> saberSiEstaRealizandoAlgunCurso(Long idAlumno, EstadoInscripcion estado);

	List<Inscripcion> consultarSiYaSeInscribioAUnCurso( Long idAlumno, Curso cursoElegido);

	void guardarInscripcionEnLaAgendaYEnInscripcion(Long idAlumno, Curso curso, List<Long> idAgendasDepurado);

	List<Inscripcion> traerLosCursosEnQueSeEncuentraAnotado(Long idAlumno, EstadoInscripcion estado);

	Inscripcion buscarCursoAEliminar(Long idCurso, Long idAlumno);

	void eliminarInscripcionDelAlumnoYSusClasesDelCurso(Long idCurso, Long idAlumno);

	Inscripcion buscarInscripcion( Long idAlumno,Long idEspecialidad);

	void finalizarCursoDelAlumno( Long idAlumno, Long idInscripcion);

	void agregarInscripcion(Long idAlumno, Curso curso, Long idAgendaEditar);

	//void verificarEstadoDeLasClases(Long idAlumno);


	

/********************************************************************************/

	
}
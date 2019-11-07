package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.ViewModel.CursosViewModel;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface ServicioInscripcion {

	
	/************************** Alumno *****************************************/
	List<Inscripcion> saberSiEstaRealizandoAlgunCurso(Long idAlumno);

	List<Inscripcion> consultarSiYaSeInscribioAUnCurso(Long idAlumno, Curso cursoElegido);

	void guardarInscripcionEnLaAgendaYEnInscripcion(Alumno alumno, Curso curso, AgendasViewModel agendasViewModel);

	List<Inscripcion> traerLosCursosEnQueSeEncuentraAnotado(Long idAlumno);

	Inscripcion buscarCursoAEliminar(Long idCurso, Long idAlumno);

	void eliminarInscripcionDelAlumnoYSusClasesDelCurso(Long idCurso, Long idAlumno);

	Inscripcion buscarInscripcion(Curso curso, Alumno alumno);

	void agregarInscripcion(Alumno alumno, Curso curso, Long idAgendaEditar);

	void guardarInscripcionEnLaAgenda(Agenda agenda);


/********************************************************************************/

	
}

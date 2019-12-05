package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.ViewModel.CursosViewModel;
import ar.edu.unlam.tallerweb1.dao.AgendaDao;
import ar.edu.unlam.tallerweb1.dao.AlumnoDao;
import ar.edu.unlam.tallerweb1.dao.EstadoInscripcionDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface ServicioAgenda {

	
	/******************************INSTRUCTOR******************************/
	List<Agenda> buscarDiaYHorarioDeTurnoDeUnInstructor(Long idInstructor);
	List<Agenda> buscarAlumnos(Long idInstructor,String nombreDeUsuario);
	List<Agenda> traerFechasDisponibles(Long idInstructor);
	void updateAgenda(Agenda agenda);
	Agenda buscarAgendaPorId(Long idAgenda);
	Agenda buscarAgenda(Agenda agenda);
	Map <String,Integer> horasTrabajadas(Long idInstructor);
	List<Agenda> traerFechas();
	
	/******************************Alumno
	 * @param long1 ******************************/

	TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso Curso, Long idAlumno);

	Boolean constatarQueNadieSeAnotaraEnLasFechasAsignadas(List<Long> idAgendasDepurado, Curso curso);

	List<Agenda> traerTodasLasClasesQueEstaAnotado(Long idAlumno, EstadoInscripcion estado);
	
	TreeSet<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(CursosViewModel cursosViewModel,Long idAlumno);

	List<Agenda> traerTodasLasClasesDeUnaSolaEspecialidad(Long idEspecialidad, Long idAlumno, EstadoInscripcion estado);

	Agenda traerClaseQueQuiereEliminar(Long idAgendaSeleccionado, Long idAlumno);

	void eliminarClaseDeLaAgenda(Long idAgendaSeleccionado, Long idAlumno);

	List<Agenda> buscarAgendasElegidas(List<Long> idAgendasDepurado, Curso curso);

	List<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas, Long idAlumno);

	List<Agenda> reemplazarAgenda(Long idAgendaSeleccionada, List<Long> idAgendasDepurado, Long idAgendaEditar, Curso curso);

	void modificarAgenda(Agenda agenda);
	
	Boolean verificarUnaAgendaSePuedaEliminar(Long idAgendaSeleccionada);
	
	List<Agenda> traerClasesQueEsteCursando(List<Agenda> clasesDeUnSoloCurso);
	
	Boolean verificarQueSePuedanEliminarTodasLasClases( Long idAlumno, Long idCurso);
	
	
	/***********************************ORGANIZADOR*********************************/
	/********************************************************************/
	Boolean crearAgenda(EstadoDeAgenda estadoDeAgenda, LocalDate desde, LocalDate hasta, Integer horaC, Integer horaF, List<InstructorVehiculoEspecialidad> listaIve);
	List<Agenda> traerAgendaPorFechayHora(String fecha, Integer hora);
	Agenda traerAgendaPorFechaYAlumno(Alumno alumno, String fecha);


	List<Agenda> traerTodasLasClasesDeUnAlumno(Long id);

	
	/*************MOCK*******************/
	
	 void setAlumnoDao(AlumnoDao alumnoDao);
	
	 void setEstadoInscripcionDao(EstadoInscripcionDao estadoinscripcionDao);
		
	 void setAgendaDao(AgendaDao agendaDao);
	
	

	
	
	
}
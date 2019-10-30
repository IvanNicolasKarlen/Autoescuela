package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.ViewModel.CursosViewModel;
import ar.edu.unlam.tallerweb1.dao.EspecialidadDao;
import ar.edu.unlam.tallerweb1.dao.EstadoDao;
import ar.edu.unlam.tallerweb1.dao.EstadoInscripcionDao;
import ar.edu.unlam.tallerweb1.dao.InscripcionDao;
import ar.edu.unlam.tallerweb1.dao.AgendaDao;
import ar.edu.unlam.tallerweb1.dao.AlumnoDao;
import ar.edu.unlam.tallerweb1.dao.EstadoDeAgendaDao;
import ar.edu.unlam.tallerweb1.dao.CursoDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

@Service("ServicioInscripcion")
@Transactional
public class ServicioInscripcionImpl implements ServicioInscripcion {
	
	@Inject
	private InscripcionDao inscripcionDao;
	@Inject
	private EstadoInscripcionDao estadoinscripcionDao;
	@Inject
	private EspecialidadDao especialidadDao;
	@Inject
	private AgendaDao agendaDao;
	@Inject
	private CursoDao  cursoDao;
	@Inject
	private AlumnoDao alumnoDao;
	@Inject
	private EstadoDeAgendaDao estadoDeAgendaDao;
	
	/********************************** ALUMNO **************************************************/
	@Override
	public List<Inscripcion> saberSiEstaRealizandoAlgunCurso(Long idAlumno) {
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion estado = estadoinscripcionDao.buscarEstadoCursando();//alumnoEstadoDao
		 
		 
		
		return inscripcionDao.saberSiEstaRealizandoAlgunCurso(idAlumno, estado);
	}
	
	
	
	@Override
	public List<Inscripcion> consultarSiYaSeInscribioAUnCurso(Long idAlumno, Curso cursoElegido) {
		
		
		//Busco el id del estado que dice "Cursando"
		/**/ EstadoInscripcion estado = estadoinscripcionDao.buscarEstadoCursando();
		
		//Buscar la especialidad del curso elegido
		/**/ Especialidad especialidad = especialidadDao.consultarEspecialidadCursoElegido(cursoElegido);
		//alumnoEspecialidadDao
		
		
		return inscripcionDao.consultarSiYaSeInscribioAUnCurso( idAlumno, estado, especialidad);
	}



	@Override
	public void guardarInscripcionEnLaAgendaYEnInscripcion(Alumno alumno, Curso curso, AgendasViewModel agendasViewModel) {

		// Datos de las agendas elegidas buscamos objetos agenda con los id de agendas
		List<Agenda> listaAgendas  = new ArrayList();
		
		Inscripcion Tablainscripcion =new Inscripcion(); 
		
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion estado = estadoinscripcionDao.buscarEstadoCursando();
		 inscripcionDao.guardarInscripcion(alumno, curso, Tablainscripcion, estado);//alumnoInscripcionDao
		
		
		for(Long id: agendasViewModel.getIdAgendasDepurado()){
			Agenda agendaBuscada = agendaDao.buscarAgendasElegidas(id, curso);//alumnoAgendaDao
			listaAgendas.add(agendaBuscada);
		}
		
		
		Inscripcion inscripcionBuscada = inscripcionDao.buscarInscripcion(alumno, curso);//alumnoInscripcionDao
		
		// guardamos los objetos agenda buscados
		for(Agenda a: listaAgendas)
		{
		
			a.setInscripcion(inscripcionBuscada);
			inscripcionDao.guardarInscripcionEnLaAgenda(a);//alumnoInscripcionDao
	
		}

	}



	@Override
	public List<Inscripcion> traerLosCursosEnQueSeEncuentraAnotado(Long idAlumno) {
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion estado = estadoinscripcionDao.buscarEstadoCursando();
				
		 
		return inscripcionDao.traerLosCursosEnQueSeEncuentraAnotado(idAlumno, estado);
	}



	@Override
	public Inscripcion buscarCursoAEliminar(Long idEspecialidad, Long idAlumno) {
		// 
		return inscripcionDao.buscarCursoAEliminar( idEspecialidad,  idAlumno);
	}



	@Override
	public void eliminarInscripcionDelAlumnoYSusClasesDelCurso(Long idCurso, Long idAlumno) {
		
						/*Eliminar las clases de la agenda del alumno tal y de la inscripcion tal*/
		
		
		Inscripcion inscripcionBuscada = inscripcionDao.cursoQueQuieroEliminar(idCurso, idAlumno);
		
		
		System.out.println("INSCRIPCION");
		System.out.println(inscripcionBuscada.getId());
		
		
		List<Agenda> misClases = agendaDao.traerTodasLasClasesDeUnaSolaEspecialidad(idAlumno, inscripcionBuscada.getId());
		
		EstadoDeAgenda asistenciaEnEspera = estadoDeAgendaDao.traigoElEstadoEnEspera();
		
		for(Agenda a: misClases)
		{
			a.setInscripcion(null);
			a.setClasePagada(null);
			a.setAsistencia(asistenciaEnEspera);
			
			inscripcionDao.guardarInscripcionEnLaAgenda(a);
		}
		
		
		/*
		List<Agenda> misClases = agendaDao.traerTodasLasClasesDeUnaSolaEspecialidad(idAlumno, idEspecialidad);
		
		EstadoDeAgenda asistenciaEnEspera = estadoDeAgendaDao.traigoElEstadoEnEspera();
		for(Agenda a: misClases)
		{
			a.setInscripcion(null);
			a.setClasePagada(null);
			a.setAsistencia(asistenciaEnEspera);
			
			inscripcionDao.guardarInscripcionEnLaAgenda(a);
		}
		*/
								/*Eliminar la inscripcion del alumno tal con el curso tal
		
		Traigo el obj inscripcion para saber el curso y el obj Alumno para poder reutilizar el metodo de abajo
		Inscripcion inscripcion = inscripcionDao.buscarCursoAEliminarDelAlumno( idAlumno, idEspecialidad); 
		Alumno alumno = alumnoDao.buscarAlumno( idAlumno);
		
		Inscripcion inscripcionEliminar = inscripcionDao.buscarInscripcion(alumno, inscripcion.getCurso());
		
		
		inscripcionEliminar.setAlumno(null);
		inscripcionEliminar.setCurso(null);
		inscripcionEliminar.setEstadoInscripcion(null);
		inscripcionDao.eliminarInscripcionDelAlumno(inscripcionEliminar);*/
		
	}







	

	
}

package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.tallerweb1.dao.AlumnoAgendaDao;
import ar.edu.unlam.tallerweb1.dao.AlumnoEspecialidadDao;
import ar.edu.unlam.tallerweb1.dao.AlumnoEstadoDao;
import ar.edu.unlam.tallerweb1.dao.AlumnoInscripcionDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

@Service("ServicioAlumnoInscripcionImpl")
@Transactional
public class ServicioAlumnoInscripcionImpl implements ServicioAlumnoInscripcion {
	
	@Inject
	private AlumnoInscripcionDao alumnoInscripcionDao;

	@Inject
	private AlumnoEstadoDao alumnoEstadoDao;
	
	@Inject
	private AlumnoEspecialidadDao alumnoEspecialidadDao;
	
	@Inject
	private AlumnoAgendaDao alumnoAgendaDao;
	 
	
	@Override
	public List<Curso> buscarCursos() {
	
		return alumnoInscripcionDao.buscarCursos() ;
	}

	@Override
	public Curso buscarCurso(Long cursoElegido) {
		
		return alumnoInscripcionDao.buscarCurso( cursoElegido);
	}

	@Override
	public List<Inscripcion> consultarSiYaSeInscribioAUnCurso(Long idAlumno, Curso cursoElegido) {
		
		
		//Busco el id del estado que dice "Cursando"
		/**/ EstadoInscripcion estado = alumnoEstadoDao.buscarEstadoCursando();
		
		//Buscar la especialidad del curso elegido
		/**/ Especialidad especialidad = alumnoEspecialidadDao.consultarEspecialidadCursoElegido(cursoElegido);
		
		
		
		return alumnoInscripcionDao.consultarSiYaSeInscribioAUnCurso( idAlumno, estado, especialidad);
	}

	

	@Override
	public void guardarInscripcion(Alumno alumno, Curso curso, Inscripcion tablaInscripcion, EstadoInscripcion estado) {
		alumnoInscripcionDao.guardarInscripcion( alumno,  curso, tablaInscripcion,  estado);		
	}

	@Override
	public Inscripcion buscarInscripcion(Alumno alumno, Curso curso) {
		return alumnoInscripcionDao.buscarInscripcion( alumno,  curso);	
	}

	@Override
	public void guardarInscripcionEnLaAgenda(List<Agenda> agendasListas, Inscripcion inscripcion) {
		for(Agenda a: agendasListas)
		{
			a.setInscripcion(inscripcion);
			alumnoInscripcionDao.guardarInscripcionEnLaAgenda(a);
	
		}	
	
	}

	@Override
	public void guardarInscripcionEnLaAgendaYEnInscripcion(Alumno alumno, Curso curso, AgendasViewModel agendasViewModel) {

		// Datos de las agendas elegidas buscamos objetos agenda con los id de agendas
		List<Agenda> listaAgendas  = new ArrayList();
		
		Inscripcion Tablainscripcion =new Inscripcion(); 
		
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion estado = alumnoEstadoDao.buscarEstadoCursando();
	     alumnoInscripcionDao.guardarInscripcion(alumno, curso, Tablainscripcion, estado);
		
		
		for(Long id: agendasViewModel.getIdAgendasDepurado()){
			Agenda agendaBuscada = alumnoAgendaDao.buscarAgendasElegidas(id, curso);
			listaAgendas.add(agendaBuscada);
		}
		
		
		Inscripcion inscripcionBuscada = alumnoInscripcionDao.buscarInscripcion(alumno, curso);
		
		// guardamos los objetos agenda buscados
		for(Agenda a: listaAgendas)
		{
		
			a.setInscripcion(inscripcionBuscada);
			alumnoInscripcionDao.guardarInscripcionEnLaAgenda(a);
	
		}

	}

	@Override
	public List<Inscripcion> saberSiEstaRealizandoAlgunCurso(Long idAlumno) {
		
		//Busco el id del estado que dice "Cursando"
		 EstadoInscripcion estado = alumnoEstadoDao.buscarEstadoCursando();
		 
		 
		
		return alumnoInscripcionDao.saberSiEstaRealizandoAlgunCurso(idAlumno, estado);
	}

}

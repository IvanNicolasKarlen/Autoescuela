package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.ViewModel.AgendasViewModel;
import ar.edu.unlam.tallerweb1.dao.AlumnoEspecialidadDao;
import ar.edu.unlam.tallerweb1.dao.EspecialidadDao;
import ar.edu.unlam.tallerweb1.dao.EstadoDao;
import ar.edu.unlam.tallerweb1.dao.EstadoInscripcionDao;
import ar.edu.unlam.tallerweb1.dao.InscripcionDao;
import ar.edu.unlam.tallerweb1.dao.AgendaDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
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

	
}

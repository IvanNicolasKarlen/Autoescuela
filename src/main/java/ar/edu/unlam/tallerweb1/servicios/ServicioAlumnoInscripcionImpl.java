package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private AlumnoInscripcionDao alumoInscripcionDao;

	@Override
	public List<Curso> buscarCursos() {
	
		return alumoInscripcionDao.buscarCursos() ;
	}

	@Override
	public Curso buscarCurso(Long cursoElegido) {
		
		return alumoInscripcionDao.buscarCurso( cursoElegido);
	}

	@Override
	public List<Inscripcion> consultarSiYaSeInscribioAUnCurso(Long idAlumno, EstadoInscripcion estado,
			Especialidad especialidad) {
		
		return alumoInscripcionDao.consultarSiYaSeInscribioAUnCurso( idAlumno, estado, especialidad);
	}

	

	@Override
	public void guardarInscripcion(Alumno alumno, Curso curso, Inscripcion tablaInscripcion, EstadoInscripcion estado) {
		alumoInscripcionDao.guardarInscripcion( alumno,  curso, tablaInscripcion,  estado);		
	}

	@Override
	public Inscripcion buscarInscripcion(Alumno alumno, Curso curso) {
		return alumoInscripcionDao.buscarInscripcion( alumno,  curso);	
	}

	@Override
	public void guardarInscripcionEnLaAgenda(List<Agenda> agendasListas, Inscripcion inscripcion) {
		for(Agenda a: agendasListas)
		{
			a.setInscripcion(inscripcion);
			alumoInscripcionDao.guardarInscripcionEnLaAgenda(a);
	
		}	
	
	}

}

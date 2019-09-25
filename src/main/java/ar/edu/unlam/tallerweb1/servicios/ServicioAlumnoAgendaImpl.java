package ar.edu.unlam.tallerweb1.servicios;

import java.util.TreeSet;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoAgendaDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;

@Service("ServicioAlumnoAgenda")
@Transactional
public class ServicioAlumnoAgendaImpl implements ServicioAlumnoAgenda {

	@Inject
	private AlumnoAgendaDao  alumnoAgendaDao;
	
	@Override
	public TreeSet<Agenda> traerAgendasConFechasNoRepetidas(Curso Curso) {

		return alumnoAgendaDao.traerAgendasConFechasNoRepetidas(Curso);
	}
	


	@Override
	public void guardarAlumnoConSuCursoElegidoEnLaAgenda(TreeSet<Agenda> agenda, Alumno alumno, Curso cursoElegido) {
		for(Agenda a: agenda)
		{
			
			a.setAlumno(alumno);
			a.setCurso(cursoElegido);
			alumnoAgendaDao.guardarAlumnoConSuCursoElegidoEnLaAgenda(a);
	
		}
		
	}

}

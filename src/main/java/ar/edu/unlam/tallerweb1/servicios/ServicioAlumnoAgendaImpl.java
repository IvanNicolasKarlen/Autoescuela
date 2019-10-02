package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;
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
	public TreeSet<Agenda> eliminarLasAgendasConFechasDuplicadas(List<Agenda> agendas) {

		// creamos un treeSet para agregar las agendas sin que se 
		// repitan las fechas
															// reverseOrder ordena los elementos
															// en forma descendente
				TreeSet<Agenda> agendasDesc = new TreeSet<Agenda>(java.util.Collections.reverseOrder());
				agendasDesc.addAll(agendas);
				
		return agendasDesc;
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



	@Override
	public List<Agenda> traerAgendasDisponibles() {
		return alumnoAgendaDao.traerAgendasDisponibles();
	}



	@Override
	public TreeSet<Agenda> eliminarAgendasQueSuperanLaCantidadDeClasesDelCurso(TreeSet<Agenda> agendasSinDuplicados, Curso curso) {
		//Elimino los objetos cunando la cantidad de elementos supera la cantidad
		// de clases del curso 
		agendasSinDuplicados.removeIf((Agenda a) -> agendasSinDuplicados.size() > curso.getCantClasesPracticas());
					
		// Ordeno las agendas con las fechas en forma ascendente
		TreeSet<Agenda> agendasAsc = new TreeSet<Agenda>();
		agendasAsc.addAll(agendasSinDuplicados);	
		return agendasAsc;
					
	}

}

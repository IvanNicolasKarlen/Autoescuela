package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.ViewModel.AgendasViewModel;
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
	public TreeSet<Agenda> traerAgendasDisponibles(Curso curso) {
		return alumnoAgendaDao.traerAgendasDisponibles(curso);
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






	@Override
	public Boolean constatarQueNadieSeAnotaraEnLasFechasAsignadas(AgendasViewModel agendasViewModel, Curso curso) {
		//Declaramos una lista para guardar las agendas buscadas
		List<Agenda> Agendas= new ArrayList();
		
		//Reorremos los ID de las agendas seleccionadas que pasamos por parametro
		for(Long a: agendasViewModel.getIdAgendasDepurado())
		{	
			//tratamos la excepcion nullPointer en caso que el
			// *metodo devuelva null
			try
			{
				// *metodo
				Agenda aBuscada=alumnoAgendaDao.buscarAgendasElegidas(a, curso);
				
				//comparamos que el id de la agenda buscada
				// sea igual que el de la agenda que le pasamos por parametro
				if(a.equals(aBuscada.getId())){
					
					Agendas.add(aBuscada);
					}else{
						return false;
					}
				
			}
			catch(NullPointerException e)
			{
				break; //finalizamos el for
			}

		}

		// si la cantidad de la lista con las agendas buscadas
		// es igual a la cant de las agendas pasadas por parametro,
		// las agendas estan disponibles y retorna true
		if(Agendas.size() == agendasViewModel.getIdAgendasDepurado().size())
			{
			 return true;
				
			}
		
		return false;
	}






	@Override
	public List<Agenda> buscarAgendasElegidas(List<Long> idAgendasDepurado, Curso curso) {
		List<Agenda> listaAgendas  = new ArrayList();
		for(Long id: idAgendasDepurado){
			Agenda agendaBuscada = alumnoAgendaDao.buscarAgendasElegidas(id, curso);
			listaAgendas.add(agendaBuscada);
		}
		
		return listaAgendas;
	}

}

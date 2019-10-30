package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.dao.EstadoInscripcionDao;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

@Service("ServicioEstadoInscripcion")
public class ServicioEstadoInscripcionImpl implements ServicioEstadoInscripcion {
	
	@Inject
	private EstadoInscripcionDao estadoInscripcionDao;
	
	@Override
	public Boolean verificarQueElCursoNoTengaInscripcionesEnCurso(List<Inscripcion> listaInscripciones) {
		Boolean verificacion = null;
		for(Inscripcion inscripcion:listaInscripciones){
			if(inscripcion.getEstadoInscripcion().getEstado().equals("Cursando")){
				verificacion = false;
				break;
			}else{
				verificacion = true;
			}
		}
		return verificacion;
	}
}

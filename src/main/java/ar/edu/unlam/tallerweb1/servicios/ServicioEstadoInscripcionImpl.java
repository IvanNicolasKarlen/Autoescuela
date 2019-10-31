package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.dao.EstadoInscripcionDao;

@Service("ServicioEstadoInscripcion")
public class ServicioEstadoInscripcionImpl implements ServicioEstadoInscripcion {
	
	@Inject
	private EstadoInscripcionDao estadoInscripcionDao;

}

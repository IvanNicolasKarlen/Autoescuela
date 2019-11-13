package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface ServicioEstadoInscripcion {
	
	/***********************ORGANIZADOR************************/
	Boolean verificarQueElCursoNoTengaInscripcionesEnCurso(List<Inscripcion> listaInscripciones);
	
	
	/******************ALUMNO*****************************/
	EstadoInscripcion buscarEstadoCursando();

	EstadoInscripcion buscarEstadoFinalizado();

}
package ar.edu.unlam.tallerweb1.dao;

import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;

public interface EstadoInscripcionDao {
	
	
	/*************************ALUMNO*******************/
	EstadoInscripcion buscarEstadoCursando();

	EstadoInscripcion buscarEstadoFinalizado();

	EstadoInscripcion buscarEstadoEliminadoPorAlumno();
}
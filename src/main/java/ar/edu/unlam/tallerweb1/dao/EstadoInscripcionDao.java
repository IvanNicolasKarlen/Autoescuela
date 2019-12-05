package ar.edu.unlam.tallerweb1.dao;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;

public interface EstadoInscripcionDao {
	
	
	/*************************ALUMNO*******************/
	EstadoInscripcion buscarEstadoCursando();

	EstadoInscripcion buscarEstadoFinalizado();

	EstadoInscripcion buscarEstadoEliminadoPorAlumno();
	
/*************MOCK**********/
	

	void setSessionFactory(SessionFactory sessionFactory);
	
}
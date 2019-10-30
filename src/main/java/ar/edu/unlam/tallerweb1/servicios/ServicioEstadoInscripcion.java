package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Inscripcion;

public interface ServicioEstadoInscripcion {
	Boolean verificarQueElCursoNoTengaInscripcionesEnCurso(List<Inscripcion> listaInscripciones);
}

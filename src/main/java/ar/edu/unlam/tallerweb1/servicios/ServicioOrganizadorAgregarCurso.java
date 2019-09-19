package ar.edu.unlam.tallerweb1.servicios;

import org.springframework.stereotype.Service;

import ar.edu.unlam.tallerweb1.modelo.Curso;

public interface ServicioOrganizadorAgregarCurso {
	Long agregarCurso(Curso curso);
}

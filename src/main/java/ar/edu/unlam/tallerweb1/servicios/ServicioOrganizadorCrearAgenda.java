package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.Date;

import ar.edu.unlam.tallerweb1.modelo.Curso;

public interface ServicioOrganizadorCrearAgenda {
	Long crearAgenda(Curso curso, LocalDate desde, LocalDate hasta, Integer horaC, Integer horaF);
}

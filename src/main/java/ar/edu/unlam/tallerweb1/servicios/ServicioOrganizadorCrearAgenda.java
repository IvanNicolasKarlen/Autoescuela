package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.Date;

public interface ServicioOrganizadorCrearAgenda {
	String crearAgenda(LocalDate desde, LocalDate hasta);
}

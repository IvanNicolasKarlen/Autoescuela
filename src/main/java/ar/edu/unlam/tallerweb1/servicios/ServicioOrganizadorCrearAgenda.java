package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Asistencia;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;

public interface ServicioOrganizadorCrearAgenda {
	Long crearAgenda(Asistencia asistencia, LocalDate desde, LocalDate hasta, Integer horaC, Integer horaF, List<InstructorVehiculoEspecialidad> listaIve);
}

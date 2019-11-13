package ar.edu.unlam.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Curso;

public class AgendasViewModel {

	private List<Long> idAgendas;
	private Long idCurso;
	private Long idAgendaSeleccionada;
	private Long idAgendaEditar;
	
	private Long idEspecialidad;
	private Long idInscripcion;
	private Integer cantClasesPracticas;
	private Integer cantDeClasesCursando;
	

	public List<Long> getIdAgendasDepurado() {
		List<Long> ret = new ArrayList<>();

		if (this.idAgendas == null)
			return ret;

		for (Long idAgenda : this.idAgendas) {
			if (idAgenda != null) {
				ret.add(idAgenda);
			}
		}
		return ret;
	}

	public List<Long> getIdAgendas() {
		return this.idAgendas;
	}

	public void setIdAgendas(List<Long> idAgendas) {
		this.idAgendas = idAgendas;
	}

	public Long getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Long idCurso) {
		this.idCurso = idCurso;
	}

	public Long getIdAgendaSeleccionada() {
		return idAgendaSeleccionada;
	}

	public void setIdAgendaSeleccionada(Long idAgendaSeleccionada) {
		this.idAgendaSeleccionada = idAgendaSeleccionada;
	}

	public Long getIdAgendaEditar() {
		return idAgendaEditar;
	}

	public void setIdAgendaEditar(Long idAgendaEditar) {
		this.idAgendaEditar = idAgendaEditar;
	}

	public Long getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(Long idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	public Long getIdEspecialidad() {
		return idEspecialidad;
	}

	public void setIdEspecialidad(Long idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}

	public Integer getCantClasesPracticas() {
		return cantClasesPracticas;
	}

	public void setCantClasesPracticas(Integer cantClasesPracticas) {
		this.cantClasesPracticas = cantClasesPracticas;
	}

	public Integer getCantDeClasesCursando() {
		return cantDeClasesCursando;
	}

	public void setCantDeClasesCursando(Integer cantDeClasesCursando) {
		this.cantDeClasesCursando = cantDeClasesCursando;
	}

	
	

	
	
}
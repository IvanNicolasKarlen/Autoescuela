package ar.edu.unlam.ViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Curso;

public class AgendasViewModel {

	private List<Long> idAgendas;
	private Long idCurso;

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

}

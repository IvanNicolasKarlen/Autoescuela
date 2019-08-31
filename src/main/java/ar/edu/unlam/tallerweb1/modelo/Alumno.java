package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Alumno extends Usuario {

	@OneToMany
	private List<Cuota> cuota;
	
	
	@OneToMany
	private List<Asistencia> asistencia;
	
	
	@OneToMany
	private List<Clase> clase;
	

	public List<Cuota> getCuota() {
		return cuota;
	}

	public void setCuota(List<Cuota> cuota) {
		this.cuota = cuota;
	}

	public List<Asistencia> getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(List<Asistencia> asistencia) {
		this.asistencia = asistencia;
	}

	public List<Clase> getClase() {
		return clase;
	}

	public void setClase(List<Clase> clase) {
		this.clase = clase;
	}
	
	
	
	
}

package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Especialidad {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipo;
	@OneToMany
	private List<InstructorVehiculoEspecialidad> instructoresVehiculosEspecialidades;
	@OneToMany
	private List<TipoDeVehiculo> tiposDeVehiculos;
	@OneToMany
	private List<Curso> cursos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<InstructorVehiculoEspecialidad> getInstructoresVehiculosEspecialidades() {
		return instructoresVehiculosEspecialidades;
	}

	public void setInstructoresVehiculosEspecialidades(
			List<InstructorVehiculoEspecialidad> instructoresVehiculosEspecialidades) {
		this.instructoresVehiculosEspecialidades = instructoresVehiculosEspecialidades;
	}

	public List<TipoDeVehiculo> getTiposDeVehiculos() {
		return tiposDeVehiculos;
	}

	public void setTiposDeVehiculos(List<TipoDeVehiculo> tiposDeVehiculos) {
		this.tiposDeVehiculos = tiposDeVehiculos;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	
}

package ar.edu.unlam.tallerweb1.modelo;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Instructor {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Usuario usuario; 
	
	@OneToMany
	private List<InstructorVehiculoEspecialidad> instructoresVehiculosEspecialidades;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<InstructorVehiculoEspecialidad> getInstructoresVehiculosEspecialidades() {
		return instructoresVehiculosEspecialidades;
	}
	public void setInstructoresVehiculosEspecialidades(
			List<InstructorVehiculoEspecialidad> instructoresVehiculosEspecialidades) {
		this.instructoresVehiculosEspecialidades = instructoresVehiculosEspecialidades;
	}

	
}
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
	private List<Especialidad> especialidad;
	@OneToMany
	private List<Vehiculo> vehiculos;
	
	
	
	public List<Especialidad> getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(List<Especialidad> especialidad) {
		this.especialidad = especialidad;
	}
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
	public List<Vehiculo> getVehiculos() {
		return vehiculos;
	}
	public void setVehiculos(List<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}
	
}
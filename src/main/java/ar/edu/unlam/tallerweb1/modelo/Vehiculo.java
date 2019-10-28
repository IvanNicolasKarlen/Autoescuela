package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Vehiculo {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String patente;
	private String modelo;
	@ManyToOne
	private TipoDeVehiculo tipoDeVehiculo;
	@ManyToOne
	private EstadoDeVehiculo estadoDeVehiculo;
	@OneToMany
	private List<InstructorVehiculoEspecialidad> ives;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}

	public TipoDeVehiculo getTipoDeVehiculo() {
		return tipoDeVehiculo;
	}
	public void setTipoDeVehiculo(TipoDeVehiculo tipoDeVehiculo) {
		this.tipoDeVehiculo = tipoDeVehiculo;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public EstadoDeVehiculo getEstadoDeVehiculo() {
		return estadoDeVehiculo;
	}
	public void setEstadoDeVehiculo(EstadoDeVehiculo estadoDeVehiculo) {
		this.estadoDeVehiculo = estadoDeVehiculo;
	}
	public List<InstructorVehiculoEspecialidad> getIves() {
		return ives;
	}
	public void setIves(List<InstructorVehiculoEspecialidad> ives) {
		this.ives = ives;
	}

	
	
	
	
	
	
}

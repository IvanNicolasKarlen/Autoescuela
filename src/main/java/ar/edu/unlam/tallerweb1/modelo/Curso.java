package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Curso {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer precio;
	private Integer cantClasesPracticas;
	private Integer cantClasesTeoricas;
	private String descripcion;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public Integer getCantClasesPracticas() {
		return cantClasesPracticas;
	}
	public void setCantClasesPracticas(Integer cantClasesPracticas) {
		this.cantClasesPracticas = cantClasesPracticas;
	}
	public Integer getCantClasesTeoricas() {
		return cantClasesTeoricas;
	}
	public void setCantClasesTeoricas(Integer cantClasesTeoricas) {
		this.cantClasesTeoricas = cantClasesTeoricas;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
}

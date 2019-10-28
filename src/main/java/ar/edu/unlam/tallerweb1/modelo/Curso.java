package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Curso {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer precio;
	private Integer cantClasesPracticas;
	private String titulo;
	private String descripcion;

	@ManyToOne
	private Especialidad especialidad;

	@ManyToOne
	private EstadoDelCurso estadoDelCurso;
	
	@OneToMany
	private List<Inscripcion> inscripciones;

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

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public EstadoDelCurso getEstadoDelCurso() {
		return estadoDelCurso;
	}

	public void setEstadoDelCurso(EstadoDelCurso estadoDelCurso) {
		this.estadoDelCurso = estadoDelCurso;
	}

	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}
	
	
	
	
}

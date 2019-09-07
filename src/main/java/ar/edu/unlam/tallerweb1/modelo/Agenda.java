package ar.edu.unlam.tallerweb1.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Agenda {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private Date fecha;
	private Integer hora;
	
	@OneToMany
	private List<Vehiculo> vehiculo;
	
	@OneToMany
	private List<Instructor> instructor;
	
	@OneToMany
	private List<Alumno> alumno;
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public List<Vehiculo> getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(List<Vehiculo> vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<Instructor> getInstructor() {
		return instructor;
	}

	public void setInstructor(List<Instructor> instructor) {
		this.instructor = instructor;
	}

	public List<Alumno> getAlumno() {
		return alumno;
	}

	public void setAlumno(List<Alumno> alumno) {
		this.alumno = alumno;
	}
	
	
	
	
	
	
	

}

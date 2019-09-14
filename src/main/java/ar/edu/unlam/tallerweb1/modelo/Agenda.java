package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Agenda {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String fecha;
	private Integer hora;
	
	@ManyToOne
	private InstructorVehiculoEspecialidad instructorVehiculoEspecialidad ;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String string) {
		this.fecha = string;
	}

	public Integer getHora() {
		return hora;
	}

	public void setHora(Integer hora) {
		this.hora = hora;
	}

	public InstructorVehiculoEspecialidad getInstructorVehiculoEspecialidad() {
		return instructorVehiculoEspecialidad;
	}

	public void setInstructorVehiculoEspecialidad(InstructorVehiculoEspecialidad instructorVehiculoEspecialidad) {
		this.instructorVehiculoEspecialidad = instructorVehiculoEspecialidad;
	}

	
	

	
	
	
	
	
	
	

}
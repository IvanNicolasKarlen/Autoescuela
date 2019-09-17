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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Agenda {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String fecha;
	private Integer hora;
	
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	private InstructorVehiculoEspecialidad instructorVehiculoEspecialidad ;
	
	
	@ManyToOne
	private Alumno alumno;


	
	public Agenda() {

	}
	public Agenda(String fecha, Integer hora, InstructorVehiculoEspecialidad instructorVehiculoEspecialidad) {
		this.fecha = fecha;
		this.hora = hora;
		this.instructorVehiculoEspecialidad = instructorVehiculoEspecialidad;
	}
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

	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}



}
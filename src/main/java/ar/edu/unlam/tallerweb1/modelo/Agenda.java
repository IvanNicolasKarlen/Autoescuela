package ar.edu.unlam.tallerweb1.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
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
public class Agenda implements Comparable{

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String fecha;
	private Integer hora;
	private Boolean clasePagada;
	@ManyToOne
	@Cascade(CascadeType.SAVE_UPDATE)
	private InstructorVehiculoEspecialidad instructorVehiculoEspecialidad ;
	@ManyToOne
	private Inscripcion inscripcion;

	@ManyToOne
	private EstadoDeAgenda estadoDeAgenda;
	
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
	
	public Inscripcion getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(Inscripcion inscripcion) {
		this.inscripcion = inscripcion;
	}
	
	
	

	public EstadoDeAgenda getEstadoDeAgenda() {
		return estadoDeAgenda;
	}

	public void setEstadoDeAgenda(EstadoDeAgenda estadoDeAgenda) {
		this.estadoDeAgenda = estadoDeAgenda;
	}

	@Override
	public int compareTo(Object o) {
		Agenda a = (Agenda)o;
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 LocalDate Objeto = LocalDate.parse(a.getFecha(), formatter);
		 LocalDate This = LocalDate.parse(this.getFecha(), formatter);	
		return( This.compareTo( Objeto ) );
	}

	public Boolean getClasePagada() {
		return clasePagada;
	}

	public void setClasePagada(Boolean clasePagada) {
		this.clasePagada = clasePagada;
	}
	
}

package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Especialidad {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String tipo;
	
	
	
	@OneToOne
	private Instructor instructor;



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



	public Instructor getInstructor() {
		return instructor;
	}



	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}



	
	
	
	
}

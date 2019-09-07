package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Usuario {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String password;
	private Integer dni;
	/*Estos no van*/
	private String nombre;
	private String email;
	private String apellido;
	
	
	
	@OneToOne
	private Especialidad especialidad;
	
	@OneToOne
	private Alumno alumno;
	
	@OneToOne
	private Instructor intructor;
	
	@OneToOne
	private Organizador organizador;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	public Especialidad getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Instructor getIntructor() {
		return intructor;
	}
	public void setIntructor(Instructor intructor) {
		this.intructor = intructor;
	}
	public Organizador getOrganizador() {
		return organizador;
	}
	public void setOrganizador(Organizador organizador) {
		this.organizador = organizador;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	
}

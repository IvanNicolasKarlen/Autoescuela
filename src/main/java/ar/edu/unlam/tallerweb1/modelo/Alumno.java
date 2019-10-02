package ar.edu.unlam.tallerweb1.modelo;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Alumno {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Usuario usuario; 
	@OneToMany
<<<<<<< HEAD
	private List<Agenda> agendas=new ArrayList();

=======
	private List<Inscripcion> inscripciones;
>>>>>>> c828800e903ac4695ebc3a817ba48743f310825c
	
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
	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}
	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}

	
}
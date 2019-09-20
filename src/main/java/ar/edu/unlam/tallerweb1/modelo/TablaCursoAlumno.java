package ar.edu.unlam.tallerweb1.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TablaCursoAlumno {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Alumno alumno;
	
	@ManyToOne
	private Curso curso;
	
	@ManyToOne
	private EstadoDelCurso estadoDelCurso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public EstadoDelCurso getEstadoDelCurso() {
		return estadoDelCurso;
	}

	public void setEstadoDelCurso(EstadoDelCurso estadoDelCurso) {
		this.estadoDelCurso = estadoDelCurso;
	}
	
	
}

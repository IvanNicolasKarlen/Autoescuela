package ar.edu.unlam.tallerweb1.modelo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class EstadoDelCurso {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String estadoDelCurso;
	@OneToMany
	private List<TablaCursoAlumno> tablasCursoAlumnos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEstadoDelCurso() {
		return estadoDelCurso;
	}
	public void setEstadoDelCurso(String estadoDelCurso) {
		this.estadoDelCurso = estadoDelCurso;
	}
	public List<TablaCursoAlumno> getTablasCursoAlumnos() {
		return tablasCursoAlumnos;
	}
	public void setTablasCursoAlumnos(List<TablaCursoAlumno> tablasCursoAlumnos) {
		this.tablasCursoAlumnos = tablasCursoAlumnos;
	}
	
}

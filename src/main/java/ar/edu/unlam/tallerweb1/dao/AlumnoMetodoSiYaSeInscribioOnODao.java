package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

public interface AlumnoMetodoSiYaSeInscribioOnODao {

	List<TablaCursoAlumno> consultarSiYaSeInscribioAUnCurso(Long alumno, EstadoDelCurso estado, Especialidad esp);

}

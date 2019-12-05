package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

public interface AlumnoDao {

	/****************** INSTRUCTOR ************************/
	List<Alumno> buscarAlumnos(String nombre,String apellido);
	
	/***************************************************/
	
	Alumno buscarAlumno(Long idAlumno);

	Usuario buscarUsuario(Long idAlumno);
	
/*************MOCK**********/
	

	void setSessionFactory(SessionFactory sessionFactory);
	

}

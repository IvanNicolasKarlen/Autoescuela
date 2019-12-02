package ar.edu.unlam.tallerweb1.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del DAO de Usuarios.
public interface UsuarioDao {
	
	/************************************** INSTRUCTOR **************************************/
	Usuario consultarUsuario (Usuario usuario);
	Long insertarUsuario (Usuario usuario);
	List<Usuario> traerAlumnos(Long idInstructor);
	Usuario traerUsuarioPorId(Long user);
	
	/****************************************ORGANIZADOR***********************************************/
	List<Usuario> traerUsuarios(String nombre, String apellido, String nombreUsuario, Integer dni, String traer);
	Usuario traerUsuarioPorNombreUsuario(String nombreUser);
	void setSessionFactory(SessionFactory sessionFactory);
	

	
}
package ar.edu.unlam.tallerweb1.servicios;

import java.util.Date;
import java.util.List;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Interface que define los metodos del Servicio de Usuarios.
public interface ServicioUsuario {

	/************************************************************/
	Usuario consultarUsuario(Usuario usuario);
	Long insertarUsuario(Usuario usuario);
	List<Usuario> traerUsuarios(String nombre, String apellido, String nombreUsuario, Integer dni, String traer);
	Usuario traerUsuarioPorNombreUsuario(String nombreUser);
	
	/***********************************************************************/
	List <Usuario> traerAlumnos(Long idInstructor);
	
	
	/********************************INSTRUCTOR***************************************/
	Usuario traerUsuarioPorId(Long id);
}

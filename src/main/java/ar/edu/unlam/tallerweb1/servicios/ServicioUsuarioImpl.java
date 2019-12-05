package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioLogin")
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {

	@Inject
	private UsuarioDao servicioUsuarioDao;

	/************************************* ORGANIZADOR *******************************/
	@Override
	public List<Usuario> traerAlumnos(Long idInstructor) {
		return servicioUsuarioDao.traerAlumnos(idInstructor);
	}
	
	@Override
	public Usuario consultarUsuario(Usuario usuario) {
		return servicioUsuarioDao.consultarUsuario(usuario);
	}
	@Override
	public Long insertarUsuario(Usuario usuario){
		return servicioUsuarioDao.insertarUsuario(usuario);
	}
	

	@Override
	public List<Usuario> traerUsuarios(String nombre, String apellido, String nombreUsuario, Integer dni,
			String traer) {
		return servicioUsuarioDao.traerUsuarios(nombre,apellido,nombreUsuario,dni,traer);
	}

	@Override
	public Usuario traerUsuarioPorNombreUsuario(String nombreUser) {
		return servicioUsuarioDao.traerUsuarioPorNombreUsuario(nombreUser);
	}
	/********************************INSTRUCTOR*****************************************/

	@Override
	public Usuario traerUsuarioPorId(Long id) {
		return servicioUsuarioDao.traerUsuarioPorId(id);
	}
}
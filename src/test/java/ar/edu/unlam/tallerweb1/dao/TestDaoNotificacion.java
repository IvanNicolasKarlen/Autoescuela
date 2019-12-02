package ar.edu.unlam.tallerweb1.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Notificacion;

import ar.edu.unlam.tallerweb1.modelo.Usuario;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDaoNotificacion extends SpringTest{
	@Test
    @Transactional
    @Rollback(false)
    @Before
    /*Aca guardo al usuario que en el siguiente test, voy a crearle las notificaciones*/
    public void A_pruebaInsertarDatos(){
		Usuario userA = new Usuario();
		userA.setNombreDeUsuario("nombrUser");
		userA.setRol("Alumno");
		Alumno a = new Alumno();
		userA.setAlumno(a);
		a.setUsuario(userA);
		assertThat(getSession().save(userA)).isNotNull();
    }
	@Test
    @Transactional
    @Rollback(false)
    /*Aca busco al usuario creado anteriormente y seteo 3 notificaciones.
     * La idea del test es verificar tanto que se hayan guardado correctamente y que funcione el metodo traerNotificaciones*/
	public void B_guardarNotificaciones(){
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		usuarioDao.setSessionFactory(getSession().getSessionFactory());
		Usuario userAlumno = usuarioDao.traerUsuarioPorId((long) 1);
		Notificacion noti1 = new Notificacion();
		Notificacion noti2 = new Notificacion();
		Notificacion noti3 = new Notificacion();
		List<Notificacion> notificacionesGuardar = new ArrayList<Notificacion>();
		notificacionesGuardar.add(noti1);notificacionesGuardar.add(noti2);notificacionesGuardar.add(noti3);
		NotificacionDao notificacionDao = new NotificacionDaoImpl();
		notificacionDao.setSessionFactory(getSession().getSessionFactory());
		Long id;
		Integer cuenta=0;
		for(Notificacion n:notificacionesGuardar){
			n.setAlumno(userAlumno.getAlumno());
			id = notificacionDao.crearNotificacion(n);
			if(id!=null){
				cuenta++;
			}
		}
		assertThat(notificacionDao.traerTodasLasNotificaciones(userAlumno).size()).isEqualTo(3);
	}
	@Test
    @Transactional
    /*Aca me traigo todas las notificaciones del alumno y cambio el leida por true.
     * Despues llamo al metodo modificar notificacion (que hace un update)
     * Y despues verifico q el metodo traerNotificacionesLeidas FUNCIONE*/
	public void C_modificarYTraerNotificacionesLeidas(){
		NotificacionDao notificacionDao = new NotificacionDaoImpl();
		notificacionDao.setSessionFactory(getSession().getSessionFactory());
		UsuarioDao usuarioDao = new UsuarioDaoImpl();
		usuarioDao.setSessionFactory(getSession().getSessionFactory());
		Usuario userAlumno = usuarioDao.traerUsuarioPorId((long) 1);
		List<Notificacion> notificaciones = notificacionDao.traerTodasLasNotificaciones(userAlumno);
		for(Notificacion not:notificaciones){
			not.setLeida(true);
			notificacionDao.modificarNotificacion(not);
		};
		assertThat(notificacionDao.traerNotificacionesLeidas(userAlumno).size()).isEqualTo(3);	
	} 
}

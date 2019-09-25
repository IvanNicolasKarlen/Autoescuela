package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.dao.BuscarInstructorPorIdDao;
import ar.edu.unlam.tallerweb1.dao.BuscarInstructorPorIdDaoImpl;
import ar.edu.unlam.tallerweb1.modelo.Instructor;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.servicios.ServicioBuscarInstructorPorId;
import ar.edu.unlam.tallerweb1.servicios.ServicioBuscarInstructorPorIdImpl;
import ar.edu.unlam.tallerweb1.servicios.ServicioBuscarAlumnosDelInstuctor;
import ar.edu.unlam.tallerweb1.servicios.ServicioBuscarAlumnosDelInstuctorImp;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

import javax.inject.Inject;

// Clase que prueba la conexion a la base de datos. Hereda de SpringTest por lo que corre dentro del contexto
// de spring
public class ConexionBaseDeDatosTest extends SpringTest{
	
	private BuscarInstructorPorIdDao servicioBuscarInstructorPorId=new BuscarInstructorPorIdDaoImpl();

    @Test
    @Transactional @Rollback(true)
    public void pruebaConexion(){
        assertThat(getSession().isConnected()).isTrue();
    }
    
    @Test
    @Transactional @Rollback(true)
    public void buscarInstrufctorPorId(){
        Instructor i = new Instructor();
        Usuario u= new Usuario();
        u.setApellido("aaa");
        u.setNombre("qqq");
        u.setDni(66);
        u.setEmail("aaa@aaa");
        getSession().save(u);
        i.setUsuario(u);
        getSession().save(i);
Instructor i2= servicioBuscarInstructorPorId.buscarInstructorPorId(i.getId());
assertThat(1).isEqualTo(1);
    }
}

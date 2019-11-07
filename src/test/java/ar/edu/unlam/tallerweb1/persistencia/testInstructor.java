package ar.edu.unlam.tallerweb1.persistencia;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Instructor;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;
public class testInstructor extends SpringTest {
	
	

	// Clase que prueba la conexion a la base de datos. Hereda de SpringTest por lo que corre dentro del contexto
	// de spring
	
		
		/************************************* INSTRUCTOR **************************************/
		@Test
	    @Transactional @Rollback(true)
	    public void pruebaConexion(){
	        assertThat(getSession().isConnected()).isTrue();
	    }
	    
	    @Test
	    @Transactional @Rollback(true)
	    public void pruebaGuardarInstructor(){
	    	Instructor i = new Instructor();
	    	getSession().save(i);
	    	Instructor i2 = getSession().get(Instructor.class, i.getId());
	    	assertThat(i.getId()).isEqualTo(i2.getId());
	    }
	    
	    @Test
	    @Transactional @Rollback(true)
	    public void pruebaEliminarInstructor(){
	    	Instructor i = new Instructor();
	    	i.setId((long) 1);
	    	getSession().save(i);
	    	getSession().delete(i);
	    	Instructor i2 = getSession().get(Instructor.class, i.getId());
	    	assertThat(i2).isNull();
	    }
	    
	    
	    /*************************************************************************************/
	}



package ar.edu.unlam.tallerweb1.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;

public class TestDaoAgenda extends SpringTest {

	
	
@Test
@Transactional
@Rollback(false)
@Before
	public void saveBD(){
	/*Este test solo realiza save a la base de datos, en los test de abajo voy a 
	obtener todos estos datos que estoy guardando aca*/
		
							/*Test 1*/
	
	Agenda ag1 = new Agenda(); Agenda ag2 = new Agenda(); Agenda ag3 = new Agenda();
	
	Alumno a1 = new Alumno();
	
	Curso c1 = new Curso();
	
	EstadoInscripcion ei1 = new EstadoInscripcion();
	
	EstadoDeAgenda ea1 = new EstadoDeAgenda();
	
	Inscripcion i1 = new Inscripcion();
	
	
	//Set Alumno
	a1.setId((long) 1);
	getSession().save(a1);
	
	//Set Curso
	c1.setId((long) 1);
	getSession().save(c1);
	
	//Set EstadoInscripcion
	ei1.setEstado("Cursando");
	getSession().save(ei1);
	
	//Set Estado de Agenda
	ea1.setEstado("Ocupada");
	getSession().save(ea1);
	
	//Set Inscripcion
	i1.setAlumno(a1);
	i1.setEstadoInscripcion(ei1);
	i1.setCurso(c1);
	getSession().save(i1);
	
	//Set Agenda
	ag1.setInscripcion(i1);
	ag1.setEstadoDeAgenda(ea1);
	ag1.setFecha("28-12-2019");
	
	getSession().save(ag1);

	ag2.setInscripcion(i1);
	ag2.setEstadoDeAgenda(ea1);		//Creo 3 agendas
	ag2.setFecha("29-12-2019");
	
	getSession().save(ag2);
	
	ag3.setInscripcion(i1);
	ag3.setEstadoDeAgenda(ea1);
	ag3.setFecha("30-12-2019");
	
	getSession().save(ag3);
	
	
							/*Test 2*/
	
	  
	 //Especialidad
	 Especialidad especialidad = new Especialidad();
	 especialidad.setTipo("Auto");
	 
	 //Estado de vehiculo
	 EstadoDeVehiculo estadoDeVehiculo= new EstadoDeVehiculo();
	 estadoDeVehiculo.setEstadoActual("Funcionando");
	 
	 //Vehiculo
	 Vehiculo vehiculo = new Vehiculo();
	 vehiculo.setEstadoDeVehiculo(estadoDeVehiculo);
	 
	//IVE
	 InstructorVehiculoEspecialidad ive = new InstructorVehiculoEspecialidad();
	
	 //Seteo vehiculo en ive
	 ive.setVehiculo(vehiculo);
	

	 //Seteo especialidad en Ive
	 ive.setEspecialidad(especialidad);

	 
	 //Alumno
	 Alumno alumno = new Alumno();
	 
	
	 //Curso
	 Curso curso = new Curso();
	 curso.setTitulo("Curso de auto");
	 curso.setEspecialidad(especialidad);
	 
	 
	//Inscripcion
		Inscripcion inscripcion = new Inscripcion();
		inscripcion.setCurso(curso);
		inscripcion.setAlumno(alumno);
		
		//Agendas
		Agenda ag4 = new Agenda();
		
		ag4.setFecha("25-12-2019");
		ag4.setInscripcion(null);
		ag4.setInstructorVehiculoEspecialidad(ive);	// Si tendria que traer					
	
		Agenda ag5 = new Agenda();
		Agenda ag6 = new Agenda();
		
		
	//Save
	 getSession().save(ag4);
	 getSession().save(ag5);
	 getSession().save(ag6);
	 getSession().save(curso);
	 getSession().save(inscripcion);
	 getSession().save(alumno);
	 getSession().save(especialidad);
	 getSession().save(ive);
	 getSession().save(vehiculo);
	 getSession().save(estadoDeVehiculo);
	 
	
		
	}
	
	
	
	
	
	
	//Traigo todo el listado de clases que estoy anotado, verifico si la cantidad que me trae la lista es la que yo quiero que me traiga
	@Test
    @Transactional
    @Rollback(false)
	public void traerListadoDeClasesQueEstoyAnotado(){
		
		//Seteo el sessionFactory
		AgendaDao agendaDao = new AgendaDaoImp();
		agendaDao.setSessionFactory(getSession().getSessionFactory());
		
		EstadoInscripcionDao estadoInscripcionDao = new EstadoInscripcionDaoImpl();
		estadoInscripcionDao.setSessionFactory(getSession().getSessionFactory());
		
		AlumnoDao alumnoDao = new AlumnoDaoImpl();
		alumnoDao.setSessionFactory(getSession().getSessionFactory());
		
		CursoDao cursoDao = new CursoDaoImpl();
		cursoDao.setSessionFactory(getSession().getSessionFactory());
		
		
		
		//Obtengo datos de la BD
		EstadoInscripcion estadoObtenido = estadoInscripcionDao.buscarEstadoCursando();
		
		Curso cursoObtenido = cursoDao.buscarCursoPorId((long) 1);
		
		Alumno alumnoObtenido = alumnoDao.buscarAlumno((long) 1); 
	
		
		TreeSet<Agenda> devuelveServicio = agendaDao.traerTodasLasClasesQueSeEncuentraAnotado(cursoObtenido.getId(), estadoObtenido, alumnoObtenido.getId());
		
		assertThat(devuelveServicio.size()).isEqualTo(3);
	}
	
	

	//AgendaDaoImpl linea 237
	//public List<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas)
	
	@Test
    @Transactional
    @Rollback(false)
	public void traerAgendasParaReemplazarOtraAgenda(){
		
		/*En este test pruebo que me traiga la cantidad de agendas que yo quiero que me devuelva*/
	

		//Seteo del session factory
		AgendaDao agendaDao = new AgendaDaoImp();
		agendaDao.setSessionFactory(getSession().getSessionFactory());
		
		CursoDao cursoDao = new CursoDaoImpl();
		cursoDao.setSessionFactory(getSession().getSessionFactory());
		
		
		
		
		//Obtengo de la BD
		 Curso cursoObtenido = cursoDao.buscarCursoPorId((long) 2);
	
		//Parametro 2
		List<Long> idAgendas = new ArrayList<>();
					
		
	List<Agenda> agendas = agendaDao.traerAgendasParaReemplazarOtra(cursoObtenido, idAgendas);	
	
	assertThat(agendas.size()).isEqualTo(1); 
	 
}
	
	
}
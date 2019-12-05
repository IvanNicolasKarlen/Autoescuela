package ar.edu.unlam.tallerweb1.servicio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.ViewModel.CursosViewModel;
import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.dao.AgendaDao;
import ar.edu.unlam.tallerweb1.dao.AgendaDaoImp;
import ar.edu.unlam.tallerweb1.dao.AlumnoDao;
import ar.edu.unlam.tallerweb1.dao.EstadoInscripcionDao;
import ar.edu.unlam.tallerweb1.dao.EstadoInscripcionDaoImpl;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeAgenda;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.EstadoInscripcion;
import ar.edu.unlam.tallerweb1.modelo.Inscripcion;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;
import ar.edu.unlam.tallerweb1.servicios.ServicioAgenda;
import ar.edu.unlam.tallerweb1.servicios.ServicioAgendaImp;


//Se indica que los test que hereden de esta clase corran con el runner de junit para spring.
@RunWith(SpringJUnit4ClassRunner.class)
//Se indica
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
//Clase base para los test que se pretende que se corran dentro del contexto de spring



public class TestServicioAgenda extends SpringTest {
	
	//ServicioAgendaImp linea 352
	//public TreeSet<Agenda> traerTodasLasClasesQueSeEncuentraAnotado(CursosViewModel cursosViewModel, Long idAlumno)
	
	@Test
	@Transactional
    public void testQueVerificaLaCantidadDeClasesQueEstoyAnotado()
    {
	        //Trae las clases que yo como alumno tengo asignadas
		
		ServicioAgenda servicioAgenda = new ServicioAgendaImp();
		
		Alumno alumnoMock = mock(Alumno.class);
		
		//Dao
		AlumnoDao alumnoDaoMock = mock(AlumnoDao.class);
		EstadoInscripcionDao estadoInscripcionDaoMock = mock (EstadoInscripcionDao.class);
		AgendaDao agendaDaoMock = mock(AgendaDao.class);
		
		
		
		//Seteo daos al servicioAgenda		
		servicioAgenda.setAlumnoDao(alumnoDaoMock);
		servicioAgenda.setEstadoInscripcionDao(estadoInscripcionDaoMock);
		servicioAgenda.setAgendaDao(agendaDaoMock);

				
		EstadoInscripcion estado = new EstadoInscripcion(); 
		 
		//Lista que me va a devolver el when
		 TreeSet<Agenda> listaInscripcion = new TreeSet<>();
		 
		 //Creo Agendas
		 Agenda ag1 = new Agenda();
		 Agenda ag2 = new Agenda();
		 Agenda ag3 = new Agenda();
		 
		 ag1.setId((long) 1);
		 ag1.setFecha("20-12-2019");
		 ag2.setId((long) 1);

		 ag2.setFecha("23-12-2019");
		 ag3.setId((long) 1);

		 ag3.setFecha("25-12-2019");
		 
		 //Agrego a TreeSet
		 listaInscripcion.add(ag1);
		 listaInscripcion.add(ag2);
		 listaInscripcion.add(ag3);
		 
		 Curso cursoMock = mock(Curso.class);
		 
		 //Trucheo curso y alumno
		 when(cursoMock.getId()).thenReturn((long) 1);
		 when(alumnoMock.getId()).thenReturn((long) 1);
			
			
		 when(agendaDaoMock.traerTodasLasClasesQueSeEncuentraAnotado(cursoMock.getId(), estado, alumnoMock.getId())).thenReturn(listaInscripcion);

		
		 
		 //Pruebo treeset
		 TreeSet<Agenda> devuelveServicio = agendaDaoMock.traerTodasLasClasesQueSeEncuentraAnotado(cursoMock.getId(), estado, alumnoMock.getId());
		
		 assertThat(devuelveServicio.size()).isEqualTo(3);
		 
    
    }
	
	

	//linea 448 servicioAgendaImp 
	//public List<Agenda> traerAgendasParaReemplazarOtra(Curso curso, List<Long> idAgendas, Long idAlumno) 
	
	@Test
	@Transactional
    public void ValidaQueElMetodoTraerAgendasNuevasFuncione()
    {
		ServicioAgenda servicioAgenda = new ServicioAgendaImp();
		
		
		//Dao
		EstadoInscripcionDao servicioEstadoInscripcionMock = mock(EstadoInscripcionDaoImpl.class);
		AgendaDao agendaDaoMock = mock (AgendaDaoImp.class);
		
		servicioAgenda.setAgendaDao(agendaDaoMock);
		servicioAgenda.setEstadoInscripcionDao(servicioEstadoInscripcionMock);
			
		Alumno alumnoMock = mock(Alumno.class);

		when(alumnoMock.getId()).thenReturn((long) 1);
		
		Especialidad especialidadAuto = new Especialidad();
		 especialidadAuto.setTipo("Auto");
		
		Curso curso = new Curso();
		curso.setEspecialidad(especialidadAuto);
		
		
		EstadoDeAgenda estadoDeAgenda = new EstadoDeAgenda();
		estadoDeAgenda.setEstado("Ocupada");				//Si este estado = Ocupada, devuelve verde el test
		
		 EstadoInscripcion estadoCursando = new EstadoInscripcion();
		 estadoCursando.setEstado("Cursando");
		 

		 when(servicioEstadoInscripcionMock.buscarEstadoCursando()).thenReturn(estadoCursando);
		
		//Instancio una inscripcion para setearle el alumno y poder setearlo a ag1
		Inscripcion inscripcion = new Inscripcion();
		inscripcion.setId((long) 1);
		inscripcion.setAlumno(alumnoMock);
		inscripcion.setEstadoInscripcion(estadoCursando);
		inscripcion.setCurso(curso);
		
		 LocalDate hoy = LocalDate.now();
		 
		 //Clase que esta anotado
		 List<Agenda> listaClases = new ArrayList<Agenda>();
		 
		 Agenda ag1 = new Agenda();
		 ag1.setFecha("02-01-2020");
		 ag1.setHora(1900);						//Anotado en 1 clase con estado Ocupada, no la cuenta
		 ag1.setInscripcion(inscripcion);
		 ag1.setEstadoDeAgenda(estadoDeAgenda);
		 
		 listaClases.add(ag1);
		 
		 when(agendaDaoMock.traerTodasLasClasesQueEstaAnotado(alumnoMock.getId(), estadoCursando)).thenReturn(listaClases);
			
		/*Solo en 1 clase esta anotado*/
		 List<Agenda> listadoDeClases = agendaDaoMock.traerTodasLasClasesQueEstaAnotado(alumnoMock.getId(), estadoCursando);
		 
		 //Creo la lista que va a contener las clases que ya estoy anotado para que no me traiga estas clases en el metodo siguiente
		 List<Long> listadoDeClasesQueYaEstoyAnotado = new ArrayList<>();
		 Long idAgendaQueYaEstoyAnotado = (long) 1;
		 listadoDeClasesQueYaEstoyAnotado.add(idAgendaQueYaEstoyAnotado);
		 
		 
		 							//PORCION DE CODIGO DE LAS CLASES NUEVAS
		 //IVE
		 InstructorVehiculoEspecialidad ive = new InstructorVehiculoEspecialidad();
		 
		 Especialidad especialidad = new Especialidad();
		 especialidad.setTipo("Auto");
		 
		 //Seteo especialidad en Ive
		 ive.setEspecialidad(especialidad);
		 
		 EstadoDeVehiculo estadoDeVehiculo2= new EstadoDeVehiculo();
		 estadoDeVehiculo2.setEstadoActual("Funcionando");
		 
		 Vehiculo vehiculo = new Vehiculo();
		 vehiculo.setEstadoDeVehiculo(estadoDeVehiculo2);
		 
		 //Seteo vehiculo en ive
		 ive.setVehiculo(vehiculo);
		
		 
		 //Lista nueva de Clases
		 List<Agenda> clasesNuevas = new ArrayList<>();
		 
		 Agenda ag3 = new Agenda();
		 ag3.setFecha("02-01-2020");
		 ag3.setHora(1900);
		 ag3.setInscripcion(null);
		 ag3.setInstructorVehiculoEspecialidad(ive);
		 																//Las dos clases que devuelve el list final
		 Agenda ag5 = new Agenda();
		 ag5.setFecha("03-01-2020");
		 ag5.setHora(1700);
		 ag5.setInscripcion(null);
		 ag5.setInstructorVehiculoEspecialidad(ive);
		  
		 clasesNuevas.add(ag3);
		 clasesNuevas.add(ag5);
		 
		 when(agendaDaoMock.traerAgendasParaReemplazarOtra(inscripcion.getCurso(), listadoDeClasesQueYaEstoyAnotado)).thenReturn(clasesNuevas);
		 
		 //Contiene 2 clases
		 List<Agenda> agendasNuevas = agendaDaoMock.traerAgendasParaReemplazarOtra(curso, listadoDeClasesQueYaEstoyAnotado);	
		 	
		 
		 List<Agenda> listadoDeClasesOcupadas = new ArrayList<Agenda>();
	
		 //Elimino las clases que tienen el estado Ocupada
		 for(Agenda clase: listadoDeClases)
		 {
			 if(clase.getEstadoDeAgenda().getEstado().equals("Ocupada"))
			 {
				 listadoDeClasesOcupadas.add(clase);
			 }
		 }
		 
		 
		 //Es para eliminar las clases que aparecen como Ocupadas
		 if(!listadoDeClasesOcupadas.isEmpty())
		 {
	 
			 for(Agenda agenda: listadoDeClasesOcupadas)
			 {
			 agendasNuevas.removeIf((Agenda a)->
			  a.getFecha().equals(agenda.getFecha())
			  && a.getHora().equals(agenda.getHora()));
			 }
		 }else
			 {
				 
			 agendasNuevas= agendaDaoMock.traerAgendasParaReemplazarOtra(curso, listadoDeClasesQueYaEstoyAnotado);
				 
			 }
		
		 
		 //Agenda enviada desde el controlador para no traer la misma que quiere modificar
		 List<Long> idAgendasEnviadasDesdeElControlador = new ArrayList<>();
		 Long idAgendaSeleccionada = (long)3;
		 idAgendasEnviadasDesdeElControlador.add(idAgendaSeleccionada);
		
		 
			 //Guardo las agendas mayores a hoy
			for(Agenda a: agendasNuevas)
			{
	
				 //validar que las fechas sean mayores a la fecha de hoy

				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				 LocalDate localDate = LocalDate.parse(a.getFecha(), formatter);
				 
				 if(localDate.isAfter(hoy))
		        	{
		        		listaClases.add(a);
		        	}
			}
			
		 
			 assertThat(listaClases.size()).isEqualTo(2);
		
		
    }
	
	
	
	
	

}

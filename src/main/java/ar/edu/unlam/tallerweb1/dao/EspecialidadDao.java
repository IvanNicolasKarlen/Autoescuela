package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.TipoDeVehiculo;

public interface EspecialidadDao {
	
/*************MOCK**********/
	void setSessionFactory(SessionFactory sessionFactory);
	

	
	/*************************Organizador***************************************/
	List<Especialidad> traerListaDeEspecialidades();
	Especialidad traerEspecialidadPorId(Long id);
	Especialidad traerEspecialidadPorNombre(String tipoEsp);
	Long guardarEspecialidad(Especialidad especialidad);
	List<Especialidad> traerEspecialidadesQueUnInstructorNoTenga(Long idInstructor);

	
	
	/************************** Alumno *************************************/
	Especialidad consultarEspecialidadCursoElegido(Curso cursoElegido);
}

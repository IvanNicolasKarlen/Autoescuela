package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AgendaDao;
import ar.edu.unlam.tallerweb1.dao.AlumnoIDao;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;
@Service("servicioAlumnoI")
@Transactional
public class ServicioAlumnoImpl implements ServicioAlumnoI {

	@Inject
	private AlumnoIDao  alumnoDao;

	@Override
	public List<Alumno> buscarAlumnos(String nombre,String apellido) {

			return alumnoDao.buscarAlumnos(nombre,apellido);
	
}}

package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoDao;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Usuario;

@Service("ServicioAlumno")
@Transactional
public class ServicioAlumnoImpl implements ServicioAlumno {

	@Inject
	private AlumnoDao alumnoDao;

	@Override
	public Alumno buscarAlumno(Long idAlumno) {
		Usuario usuario =	alumnoDao.buscarUsuario(idAlumno);
		
		return alumnoDao.buscarAlumno( usuario.getAlumno().getId());
	}

	@Override
	public List<Alumno> buscarAlumnos(String nombre,String apellido) {

			return alumnoDao.buscarAlumnos(nombre,apellido);
	}

	@Override
	public Usuario buscarUsuario(Long idAlumno) {
		// TODO Auto-generated method stub
		return alumnoDao.buscarUsuario( idAlumno);
	}
	
	
}
package ar.edu.unlam.tallerweb1.dao;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;
@Repository("organizadorAgregarCursoDao")
public class OrganizadorAgregarCursoDaoImpl implements OrganizadorAgregarCursoDao {
	@Inject
    private SessionFactory sessionFactory;
	@Override
	public Boolean agregarCurso(Curso curso) {
		final Session sesion = sessionFactory.getCurrentSession();
		Long id = (Long)sesion.save(curso);
		if(id!=null){
			return true;
		}else{
			return false;
		}
	}

}

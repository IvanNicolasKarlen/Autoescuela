package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.modelo.EstadoDelCurso;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;


@Repository("CursoMetodoSiYaSeInscribioOnODao")
public class AlumnoMetodoSiYaSeInscribioOnODaoImpl implements AlumnoMetodoSiYaSeInscribioOnODao {

	// Como todo dao maneja acciones de persistencia, normalmente estará inyectado el session factory de hibernate
	// el mismo está difinido en el archivo hibernateContext.xml
	@Inject
    private SessionFactory sessionFactory;

	
	
	@Override
	public List<TablaCursoAlumno> consultarSiYaSeInscribioAUnCurso(Long idAlumno, EstadoDelCurso estado, Especialidad esp) {
		final Session session = sessionFactory.getCurrentSession();
	List <TablaCursoAlumno> l =  session.createCriteria(TablaCursoAlumno.class)
				.createAlias("curso.especialidad", "ce")
				.add(Restrictions.eq("ce.id", esp.getId()))
				.add(Restrictions.eq("alumno.id", idAlumno))
				.add(Restrictions.eq("estadoDelCurso.id", estado.getId()))
				.list();
	return l;
	}
	
	
	}



package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Asistencia;

@Repository
public class AsistenciaDaoImpl implements AsistenciaDao {
	@Inject
	private SessionFactory sessionFactory;
	@Override
	public List<Asistencia> traerListaDeAsistencia() {
		Session session = sessionFactory.getCurrentSession();
		return (List<Asistencia>) session.createCriteria(Asistencia.class)
									.list();
	}

	@Override
	public Asistencia traerAsistenciaPorNombre(String estado) {
		Session session = sessionFactory.getCurrentSession();
		return (Asistencia) session.createCriteria(Asistencia.class)
							.add(Restrictions.eq("estado", estado)).uniqueResult();
	}

	@Override
	public Asistencia traerAsistenciaPorId(Long id) {
		Session session = sessionFactory.getCurrentSession();
		return (Asistencia) session.get(Asistencia.class, id);
	}

}

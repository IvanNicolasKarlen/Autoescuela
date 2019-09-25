package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Instructor;
@Repository
//@Transactional
public class BuscarInstructorPorIdDaoImpl implements BuscarInstructorPorIdDao {
	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public Instructor buscarInstructorPorId(Long idUsuario) {
	
		final Session session = sessionFactory.getCurrentSession();
		
		
		       return session.get(Instructor.class, idUsuario);
}
}
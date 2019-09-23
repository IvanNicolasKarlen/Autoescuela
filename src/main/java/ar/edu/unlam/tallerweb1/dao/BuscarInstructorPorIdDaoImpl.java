package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Instructor;
@Repository("BuscarInstructorPorIdDao")
public class BuscarInstructorPorIdDaoImpl implements BuscarInstructorPorIdDao {
	@Inject
    private SessionFactory sessionFactory;
	
	@Override
	public Instructor buscarInstructorPorId(Long idUsuario) {
	
		
		final Session session = sessionFactory.getCurrentSession();
		Long id=(long) 1;
        return (Instructor) session.createCriteria(Instructor.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    

}
}
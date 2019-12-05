package ar.edu.unlam.tallerweb1.dao;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Organizador;

@Service
@Transactional
public class OrganizadorDaoImpl implements OrganizadorDao {
	@Inject
    private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<Organizador> traerTodosLosOrganizadores() {
		final Session session = sessionFactory.getCurrentSession();
		return (List<Organizador>)session.createCriteria(Organizador.class).list();
	}

}

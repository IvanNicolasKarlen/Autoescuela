package ar.edu.unlam.tallerweb1.dao;
import java.util.List;
import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ar.edu.unlam.tallerweb1.modelo.Agenda;


@Repository("listadoFechaDao")
public class ListadoFechaDaoImp implements ListadoFechaDao {
	
	@Inject
    private SessionFactory sessionFactory;
	
	List <Agenda> listaFecha;
	
	

	@Override
	public List<Agenda> listaFecha() {
		final Session session = sessionFactory.getCurrentSession();
		listaFecha = session.createCriteria(Agenda.class)
					.list();
		return (listaFecha);
	}

}

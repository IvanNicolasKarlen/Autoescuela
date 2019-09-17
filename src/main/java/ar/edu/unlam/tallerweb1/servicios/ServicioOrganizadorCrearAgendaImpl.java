package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;


import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.OrganizadorCrearAgendaDao;

@Service
@Transactional
public class ServicioOrganizadorCrearAgendaImpl implements ServicioOrganizadorCrearAgenda {
	@Inject
	OrganizadorCrearAgendaDao organizadorCrearAgendaDao;
		public String crearAgenda(LocalDate desde, LocalDate hasta){
			return organizadorCrearAgendaDao.crearAgenda(desde,hasta);
		}
}

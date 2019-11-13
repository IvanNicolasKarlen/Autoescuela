package ar.edu.unlam.tallerweb1.dao;


import java.util.List;
import java.util.TreeSet;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;

@Repository("EstadoDao")
public class EstadoDaoImpl implements EstadoDao {
	@Inject
    private SessionFactory sessionFactory;


}
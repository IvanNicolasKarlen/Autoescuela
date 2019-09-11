package ar.edu.unlam.tallerweb1.servicios;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.AlumnoMetodoQueGuardaFechasDao;
import ar.edu.unlam.tallerweb1.dao.UsuarioDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;
import ar.edu.unlam.tallerweb1.modelo.Alumno;
import ar.edu.unlam.tallerweb1.modelo.Curso;
import ar.edu.unlam.tallerweb1.modelo.TablaCursoAlumno;

@Service("ServicioCursoMetodoQueGuardaFechas")
@Transactional
public class ServicioAlumnoMetodoQueGuardaFechasImpl implements ServicioAlumnoMetodoQueGuardaFechas {

	@Inject
	private AlumnoMetodoQueGuardaFechasDao ServicioCursoMetodoQueGuardaFechasDao;
	
	
	@Override
	public void metodoQueGuardaFechas(Agenda agenda) {

		
		LocalDate desde = LocalDate.now();
        LocalDate hasta = LocalDate.of(2200,12,31);
        for (LocalDate date = desde; date.isBefore(hasta); date = date.plusDays(1))
        {
        	
        	agenda.setFecha(date.toString());
        	
        	ServicioCursoMetodoQueGuardaFechasDao.guardarFechasEnLaAgenda(agenda);
        	
        	
        }
		
	}

	
	

}

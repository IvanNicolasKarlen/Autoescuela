package ar.edu.unlam.tallerweb1.servicios;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.IveDao;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;

@Service
@Transactional
public class ServicioIVEImpl implements ServicioIVE {
	@Inject
	private IveDao iveDao;
	@Override
	public Long guardarIve(InstructorVehiculoEspecialidad ive) {
		return iveDao.guardarIve(ive);
	}

	
}

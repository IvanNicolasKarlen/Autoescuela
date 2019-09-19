package ar.edu.unlam.tallerweb1.servicios;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.OrganizadorCrearAgendaDao;
import ar.edu.unlam.tallerweb1.dao.VehiculoDao;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;
@Service
@Transactional
public class ServicioVehiculoImpl implements ServicioVehiculo {
	@Inject
	private VehiculoDao vehiculoDao;
	@Override
	public List<Vehiculo> obtenerVehiculoPorEspecialidad(Especialidad especialidad) {
		return vehiculoDao.obtenerVehiculoPorEspecialidad(especialidad);
	}
	@Override
	public Vehiculo buscarVehiculo(Vehiculo vehiculo){
		return vehiculoDao.buscarVehiculo(vehiculo);
	}
	@Override
	public Long guardarVehiculo(Vehiculo vehiculo) {
		return vehiculoDao.guardarVehiculo(vehiculo);
	}
	@Override
	public Vehiculo buscarVehiculoPorId(Long id) {
		return vehiculoDao.buscarVehiculoPorId(id);
	}

}

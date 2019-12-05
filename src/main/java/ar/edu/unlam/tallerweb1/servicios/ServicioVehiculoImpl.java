package ar.edu.unlam.tallerweb1.servicios;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.dao.IveDao;
import ar.edu.unlam.tallerweb1.dao.VehiculoDao;
import ar.edu.unlam.tallerweb1.modelo.Especialidad;
import ar.edu.unlam.tallerweb1.modelo.EstadoDeVehiculo;
import ar.edu.unlam.tallerweb1.modelo.InstructorVehiculoEspecialidad;
import ar.edu.unlam.tallerweb1.modelo.Vehiculo;
@Service
@Transactional
public class ServicioVehiculoImpl implements ServicioVehiculo {
	@Inject
	private VehiculoDao vehiculoDao;
	@Inject
	private IveDao iveDao;
	
	/********************************INSTRUCTOR**************************/
	public List<EstadoDeVehiculo> traerVehiculos(Long idAgenda){
		return vehiculoDao.traerVehiculos(idAgenda);
}
	
	@Override
	public void updateVehiculo(Vehiculo idEstadoDeVehiculo) {
		vehiculoDao.updateVehiculo(idEstadoDeVehiculo);
		
	}
	/********************************ORGANIZADOR**************************/
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
	@Override
	public List<Vehiculo> obtenerVehiculosSinInstructorPorEspecialidad(Especialidad esp) {
		List <InstructorVehiculoEspecialidad> listaIve = iveDao.traerListaIvePorEspecialidad(esp);
		List <Vehiculo> listaV = this.obtenerVehiculoPorEspecialidad(esp);
		List <Vehiculo> vehiculoSinInst = new ArrayList<Vehiculo>();
		for(Vehiculo v:listaV){
			for(InstructorVehiculoEspecialidad ive:listaIve){
				if(!(v.equals(ive.getVehiculo()))){
					vehiculoSinInst.add(v);
				}
			}
		}
		if(listaIve.isEmpty()){
			return listaV;
		}else{
			return vehiculoSinInst;
		}
		
		
	}


}
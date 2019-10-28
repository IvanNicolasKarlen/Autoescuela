package ar.edu.unlam.tallerweb1.servicios;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service("ServicioAlumnoValidaFechaElegida")
public class ServicioValidarFechaElegidaImpl implements ServicioValidarFechaElegida {
	@Override
	public Boolean validarFechas(Date fechaDesde, Date fechaHasta) {
		Boolean result=false;
		if(fechaHasta.before(fechaDesde))
		{
			
			result=true;

		}else
		{
			result=false;

		}
		return result;
	}
}

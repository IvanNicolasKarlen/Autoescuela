package ar.edu.unlam.tallerweb1.servicios;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service("ServicioAlumnoValidaFechaElegida")
public class ServicioAdminValidaFechaElegidaImpl implements ServicioAdminValidaFechaElegida {
	@Override
	public Boolean validarFechas(Date fechaDesde, Date fechaHasta) {
		Boolean result=false;
		if(fechaHasta.before(fechaDesde))
		{
			
			return result=true;

		}else
		{
			return result=false;

		}
	}
}

package ar.edu.unlam.tallerweb1.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unlam.ViewModel.TurnosViewModel;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorConvierteFecha;
import ar.edu.unlam.tallerweb1.servicios.ServicioOrganizadorValidaFechaElegida;

@Controller
public class ControladorOrganizador {
	
	@Inject
	private ServicioOrganizadorConvierteFecha servicioOrganizadorConvierteFecha;
	@Inject
	private ServicioOrganizadorValidaFechaElegida servicioOrganizadorValidaFechaElegida;
	
	@RequestMapping(path="/DesdeHasta")
	public ModelAndView elegirFechaDesdeHasta(){
		ModelMap modelo = new ModelMap();
		// Obtenemos fecha actual
		LocalDate fechaActual = LocalDate.now();
				 
		// Convertimos la fecha a String con formato yyy-mm-dd
		// para luego pasarlo al input date
		// para indicar que inhabilite los dias anteriores a la fecha actual
		String formatoFecha = fechaActual.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		modelo.put("formatoFecha", formatoFecha);
		
		return new ModelAndView("DesdeHasta",modelo);
	}
	
	@RequestMapping(path="/fechaElegida")
	public ModelAndView validarHorariosDeLaFechaElegida(
//			TurnosViewModel esta clase esta en el paquete ViewModel que agregamos 
//			nos permite recibir en el Controlador todos los datos desde el
//			formulario que esta en la Vista
			@ModelAttribute("curso") TurnosViewModel fecha){

		ModelMap modelo = new ModelMap();
		
		// Servicio para convertir fecha String a Date
		Date fechaDesde = servicioOrganizadorConvierteFecha.convertirFecha(fecha.getDesde());
		Date fechaHasta = servicioOrganizadorConvierteFecha.convertirFecha(fecha.getHasta());
		
		Boolean resultado=servicioOrganizadorValidaFechaElegida.validarFechas(fechaDesde, fechaHasta);
		
		// Si la fechas Desde es mayor a la fecha Hasta, 
		// redirijo al path /mensaje enviando un aviso por url 
		if(resultado == true)
		{
			modelo.put("mensaje", "La fecha Desde no puede ser mayor a la fecha Hasta");
			return new ModelAndView("DesdeHasta", modelo);
		
		}

		modelo.put("desde",fechaDesde);
		modelo.put("hasta", fechaHasta);
		

		return new ModelAndView("horas",modelo);
	}
	
	
	
	

}

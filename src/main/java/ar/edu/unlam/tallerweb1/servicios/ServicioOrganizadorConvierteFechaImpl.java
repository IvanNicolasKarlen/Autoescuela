package ar.edu.unlam.tallerweb1.servicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service("ServicioOrganizadorConvierteFecha")
public class ServicioOrganizadorConvierteFechaImpl implements ServicioOrganizadorConvierteFecha{

	@Override
	public Date convertirFecha(String desde) {
		
//		Objeto SimpleDateFormat construido con el formato de fecha que nos venga en el String
		        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		        Date fechaDate = null;
//		        Por diversas causas, como un mal formato de fecha respecto del String,
//		        se pueden dar excepciones, por lo que las controlamos con 
//		        try-catch para ParseException
		        
		        try {
		        	//aplico funcion para convertir la fecha
		            fechaDate = formato.parse(desde);
		        } 
		        catch (ParseException ex) 
		        {
		            System.out.println(ex);
		        }
		        return fechaDate;
		    
	}

}
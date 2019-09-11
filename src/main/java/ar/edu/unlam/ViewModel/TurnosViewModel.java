package ar.edu.unlam.ViewModel;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

//LINK USO DE FECHAS https://www.baeldung.com/spring-date-parameters
public class TurnosViewModel {

//	@Temporal(TemporalType.DATE)
	//@DateTimeFormat(pattern = "yyyy/MM/dd")
//	private Date desde;
//	
	
	//private String desde;
	
	 @Temporal(TemporalType.DATE)
	    @DateTimeFormat(iso = ISO.DATE)
		private Date desde;
	
	
	 @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
	private Date hasta;


	public Date getDesde() {
		return desde;
	}


	public void setDesde(Date desde) {
		this.desde = desde;
	}


	public Date getHasta() {
		return hasta;
	}


	public void setHasta(Date hasta) {
		this.hasta = hasta;
	}
	
	
	
	
	
	
	

}

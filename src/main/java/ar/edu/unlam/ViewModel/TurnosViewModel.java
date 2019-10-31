package ar.edu.unlam.ViewModel;


//LINK USO DE FECHAS https://www.baeldung.com/spring-date-parameters
public class TurnosViewModel {

//	Declaro los datos a recibir en el controlador 
	private String desde;

	private String hasta;

	public String getDesde() {
		return desde;
	}

	public void setDesde(String desde) {
		this.desde = desde;
	}

	public String getHasta() {
		return hasta;
	}

	public void setHasta(String hasta) {
		this.hasta = hasta;
	}



}


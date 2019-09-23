package ar.edu.unlam.tallerweb1.servicios;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.dao.ListadoFechaDao;
import ar.edu.unlam.tallerweb1.modelo.Agenda;


@Service("servicioListarFecha")
@Transactional
public class ServicioListarFechaImp implements ServicioListarFecha {

	
	@Inject
	private ListadoFechaDao servicioListadoFechaDao;
	
	@Override
	public List<Agenda> listaFecha() {
		return servicioListadoFechaDao.listaFecha();
	}

}

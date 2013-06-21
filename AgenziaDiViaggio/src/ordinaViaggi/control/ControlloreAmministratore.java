package ordinaViaggi.control;

import ordinaViaggi.entity.Prenotazioni;
import ordinaViaggi.exception.ControllerException;
import ordinaViaggi.exception.MapDAOException;

import java.util.List;

/**
 * @author Gambella Riccardo
 * Boundary Controllore Cliente.
 */


public class ControlloreAmministratore extends Controllore
{
	private static ControlloreAmministratore istance = null;   
	
	private ControlloreAmministratore()
	{
		super();
	}
	
	public static ControlloreAmministratore getIstance(){
		if(istance == null)
			istance = new ControlloreAmministratore();
		return istance;
	}
	
	public void estrazioneLista(List<String> selectedList, List<String> mapList) throws ControllerException {
		// TODO Auto-generated method stub
		Prenotazioni prenotazioni = Prenotazioni.getIstance();
		try {
			prenotazioni.estrazioneMappa(selectedList, mapList);
		} catch (MapDAOException e) {
			// TODO Auto-generated catch block
			throw new ControllerException("Errore in estrazione lista!!");
		}
	}

	public List<String> getTicket(List<String> listaOfferta, String key) throws ControllerException {
		// TODO Auto-generated method stub
		Prenotazioni prenotazioni = Prenotazioni.getIstance();
		try {
			return prenotazioni.getTicket(listaOfferta, key);
		} catch (MapDAOException e) {
			// TODO Auto-generated catch block
			throw new ControllerException("Errore in get ticket!!");
		}
		
	}
}









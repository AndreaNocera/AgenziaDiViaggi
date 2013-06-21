

package ordinaViaggi.control;

import ordinaViaggi.entity.Prenotazioni;
import ordinaViaggi.exception.ControllerException;
import ordinaViaggi.exception.MapDAOException;

import java.util.List;


/**
 * @author Gambella Riccardo
 * Boundary Controllore Cliente.
 */
public class ControlloreCliente extends Controllore
{
	private static ControlloreCliente istance = null;   
	
	//Costruttore
	private ControlloreCliente() 
	{
		super();
	}
	
	public static ControlloreCliente getIstance(){
		if(istance == null)
			istance = new ControlloreCliente();
		return istance;
	}
	
	public boolean verificaDatiTraveler(List<String> dati){
		for(String dato : dati){
			if(dato == null || dato.equals(""))
				return false;
		}
		return true;
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

	public String getInfo(List<String> listaCatalogo, String ora) throws ControllerException {
		// TODO Auto-generated method stub
		Prenotazioni prenotazioni = Prenotazioni.getIstance();
		try {
			return prenotazioni.getInfo(listaCatalogo, ora);
		} catch (MapDAOException e) {
			// TODO Auto-generated catch block
			throw new ControllerException("Errore in getInfo!!");
		}
	}

	public void inserimentoBiglietto(List<String> listaCatalogo, String id,
			List<String> dati) throws ControllerException {
		// TODO Auto-generated method stub
		Prenotazioni prenotazioni = Prenotazioni.getIstance();
		try {
			prenotazioni.inserimentoBiglietto(listaCatalogo, id, dati);
		} catch (MapDAOException e) {
			// TODO Auto-generated catch block
			throw new ControllerException();
		}
	}
}









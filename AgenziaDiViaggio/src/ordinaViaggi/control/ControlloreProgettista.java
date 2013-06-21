package ordinaViaggi.control;

import ordinaViaggi.entity.Offerta;
import ordinaViaggi.exception.ControllerException;
import ordinaViaggi.exception.MapDAOException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gambella Riccardo
 * Controllore Progettista.
 */
public class ControlloreProgettista extends Controllore
{
	private static ControlloreProgettista istance = null;   

	public ControlloreProgettista()
	{
		super();
	}
	
	public static ControlloreProgettista getIstance(){
		if(istance == null)
			istance = new ControlloreProgettista();
		return istance;
	}

	public void estrazioneLista(List<String> selectedList, List<String> mapList) throws ControllerException {
		// TODO Auto-generated method stub
		Offerta offerta = Offerta.getIstance();
		try {
			offerta.estrazioneMappa(selectedList, mapList);
		} catch (MapDAOException e) {
			// TODO Auto-generated catch block
			throw new ControllerException("Errore in estrazione lista!!");
		}
	}

	

	public void inserimentoInOfferta(List<String> listaCatalogo) throws ControllerException, IOException {
		// TODO Auto-generated method stub

		String data = null;
		String ora = null;
		String posti = null;

		BufferedReader input = new BufferedReader(
				new InputStreamReader(System.in));
		System.out.print("Inserire la data.\r\n");
		data = input.readLine();
		System.out.print("Inserire l'ora.\r\n");
		ora = input.readLine();
		System.out.println("Inserire i posti disponibili iniziali.");
		posti = input.readLine();
		
		/* Mockup. Data per inserite le chiavi e le info dei subElements */
		listaCatalogo.add(data);
		listaCatalogo.add(ora);
		
		/* Info dei subElements */
		List<String> subElementsInfo = new ArrayList<String>();
		//Nei primi 7 mette niente
		for(int i=0;i<6;i++)
			subElementsInfo.add("");
		//Nel SubElement riferito a posti mette il valore inserito
		subElementsInfo.add(posti);
		
		Offerta offerta = Offerta.getIstance();
		try {
			offerta.inserimentoInOfferta(listaCatalogo, subElementsInfo);
			System.out.println("Stampa mappa dopo inserimento in offerta.");
			offerta.printOfferta();
		} catch (MapDAOException e) {
			// TODO Auto-generated catch block
			throw new ControllerException("Errore in inserimento offerta!!");
		}
		
	}

	public void rimozioneInOfferta(List<String> listaCatalogo) throws IOException, ControllerException {
		// TODO Auto-generated method stub
		String data = null;
		String ora = null;
		
		BufferedReader input = new BufferedReader(
				new InputStreamReader(System.in));
		System.out.print("Inserire la data.\r\n");
		data = input.readLine();
		System.out.print("Inserire l'ora.\r\n");
		ora = input.readLine();
		Offerta offerta = Offerta.getIstance();
		try {
			offerta.rimozioneInOfferta(listaCatalogo, data, ora);
			System.out.println("Stampa mappa dopo inserimento in offerta.");
			offerta.printOfferta();
		} catch (MapDAOException e) {
			// TODO Auto-generated catch block
			throw new ControllerException("Errore in rimozione offerta!!");
		}
		
	}

	public void printOfferta() throws ControllerException {
		// TODO Auto-generated method stub
		Offerta offerta = Offerta.getIstance();
		try {
			offerta.printOfferta();
		} catch (MapDAOException e) {
			// TODO Auto-generated catch block
			throw new ControllerException("Errore in print offerta!!");
		}
	}
}









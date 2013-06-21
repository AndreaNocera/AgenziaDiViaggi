/**
 * @author Gambella Riccardo
 */

package ordinaViaggi.entity;

import ordinaViaggi.exception.MapDAOException;

import java.util.ArrayList;
import java.util.List;

public class Prenotazioni extends ManipolatoreMappe{
	
	private static Prenotazioni prenotazioni;
	private Prenotazioni(){
	}
	
	public static Prenotazioni getIstance(){
		if(prenotazioni == null)
			prenotazioni = new Prenotazioni();
		return prenotazioni;
	}
	
	/**Inserimento di un biglietto.
	 * 
	 * @param listaOfferta
	 * @param idPrenotazione
	 * @param dati
	 * @throws MapDAOException
	 */
	public void inserimentoBiglietto(List<String> listaOfferta,
			String idPrenotazione, List<String> dati) throws MapDAOException{
		
		Map map = readMap();
		
		String nome = dati.remove(0);
		String cognome = dati.remove(0);
		listaOfferta.add(idPrenotazione);
		for(String key : listaOfferta)
			System.out.println(key + " ");
		System.out.println("Nome: " + nome);
		System.out.println("Cognome: " + cognome);
		
		
		/* Devo arrivare in fondo alla mappa, creare una nuova mappa con chiave id DellaPrenotazione
		 * e un SubElement che contiene il biglietto, che è formato da una lista di travelers.
		 * listaOfferta contiene l'offerta specifica rispetto a cui voglio prenotare
		 */
		
		
		//Crea il biglietto per un traveler solo per prova.
		Traveler traveler = new Traveler(nome, cognome);
		List<Traveler> listTravelers = new ArrayList<Traveler>();
		listTravelers.add(traveler);
		Biglietto biglietto = new Biglietto(listTravelers);
		
		//Crea il subElement di tipo SubElementBiglietto da inserire
		SubElement subElement = new SubElementBiglietto(new Map(), biglietto);
		// Crea la lista di SubElements di tipo SubElementCatalogo
		List<SubElement> listaSubElements = new ArrayList<SubElement>();
		/*for(String info : subElementsInfo){
			SubElement subElement = new SubElementOfferta(new Map(),info);
			listaSubElements.add(subElement);
		}*/
		for(int i=0;i<7;i++)
			listaSubElements.add(new SubElementBiglietto(new Map(), ""));
		listaSubElements.add(subElement);
		
		inserimentoInMapRecursive(map,listaOfferta,listaSubElements);
		
		
		// Al termine dell'inserimento salva la mappa (NB Metodo del Controllore)
		saveMap(map);
	}
	
	/**Stampa delle prenotazioni
	 * 
	 * @throws MapDAOException
	 */
	public void printPrenotazioni() throws MapDAOException{
		Map map = readMap();
		printMapRecursive(map, 0, 9);
	}
}

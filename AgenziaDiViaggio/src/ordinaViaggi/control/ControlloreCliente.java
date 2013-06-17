package ordinaViaggi.control;

import java.util.ArrayList;
import java.util.List;

import ordinaViaggi.boundaries.BoundaryCliente;
import ordinaViaggi.entity.Biglietto;
import ordinaViaggi.entity.Map;
import ordinaViaggi.entity.SubElement;
import ordinaViaggi.entity.SubElementBiglietto;
import ordinaViaggi.entity.Traveler;
import ordinaViaggi.exception.MapDAOException;

/**
 * 
 * @author Gambella Riccardo
 * Boundary Controllore Cliente.
 */
public class ControlloreCliente extends Controllore
{
	//Temporanea per il test
	private static Controllore k;
	private static ControlloreCliente h;   
	private static BoundaryCliente c;

	public ControlloreCliente(String dummy)
	{
		//System.out.println("Entra con CambiaUtente!");
	}
	
	//Costruttore
	private ControlloreCliente() 
	{
		//System.out.println("Qui entra all'inizio!");
		super(); //Chiama subito il Controllore, cos√¨ crea subito la MappaProdotti
		
		//Per prima cosa il Costruttore crea un oggetto Vector e chiama "inizializzazionePerVersioneMemoriaCentrale()"		
	}
	
	/* Avvia la Boundary del Cliente.
	 * 
	 */
	public static void gestioneCliente()
	{  
		
		h=new ControlloreCliente(); 	
		c= new BoundaryCliente(h);   
		
	}
	
	public static boolean verificaDatiTraveler(List<String> dati){
		for(String dato : dati){
			if(dato == null || dato.equals(""))
				return false;
		}
		return true;
	}
	
	/* Inserimento di un biglietto nel catalogo. Sono necessari mappa iniziale,
	 * ambiente, mezzo, cittaPartenza e cittaArrivo inseriti in una lista.
	 * 
	 */
	public static void inserimentoBiglietto(List<String> listaOfferta,
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
		 * e un SubElement che contiene il biglietto, che Ë formato da una lista di travelers.
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
	
	/* Metodo di stampa dell'Offerta. 
	 * Si basa sul wrapper ricorsivo di printMapRecursive.
	 */
	public static void printPrenotazioni() throws MapDAOException{
		Map map = readMap();
		printMapRecursive(map, 0, 9);
	}
	
}









package ordinaViaggi.control;

import java.util.ArrayList;
import java.util.List;

import ordinaViaggi.boundaries.BoundaryPromotore;
import ordinaViaggi.entity.Map;
import ordinaViaggi.entity.SubElement;
import ordinaViaggi.entity.SubElementCatalogo;
import ordinaViaggi.exception.MapDAOException;


/**
 * @author Gambella Riccardo
 * Controllore Promotore.
 */

public class ControllorePromotore extends Controllore
{
	//Temporanea per il test
	private static Controllore k;
	private static ControllorePromotore h;   
	private static BoundaryPromotore c;

	public ControllorePromotore(String dummy)
	{
		//System.out.println("Entra con CambiaUtente!");
	}
	
	//Costruttore
	private ControllorePromotore() 
	{
		//System.out.println("Qui entra all'inizio!");
		super(); //Chiama subito il Controllore, così crea subito la MappaProdotti
		
		//Per prima cosa il Costruttore crea un oggetto Vector e chiama "inizializzazionePerVersioneMemoriaCentrale()"		
	}
	
	/* Avvia la Boundary del Promotore.
	 * 
	 */
	public static void gestionePromotore()
	{                             
		h=new ControllorePromotore(); 	
		c= new BoundaryPromotore(h);    
		
	}
	
	
	/* Inserimento di un elemento nel catalogo. Sono necessari mappa iniziale,
	 * ambiente, mezzo, cittaPartenza, cittaArrivo e via.
	 * 
	 */
	public static void inserimentoInCatalogo(List<String> listaCatalogo, List<String> subElementsInfo)
			throws MapDAOException{
		if(listaCatalogo.size() != subElementsInfo.size())
			throw new MapDAOException("Errore in Inserimento Catalogo");
		Map map = readMap();
		// Crea la lista di SubElements di tipo SubElementCatalogo
		List<SubElement> listaSubElements = new ArrayList<SubElement>();
		for(String info : subElementsInfo){
			SubElement subElement = new SubElementCatalogo(new Map(),info);
			listaSubElements.add(subElement);
		}
			
		inserimentoInMapRecursive(map,listaCatalogo,listaSubElements);
		
		// Al termine dell'inserimento salva la mappa (NB Metodo del Controllore)
		saveMap(map);
	}
	
	/* Metodo di rimozione di un elemento nel catalogo
	 * Sono necessari mappa iniziale, ambiente, mezzo, cittaPartenza, cittaArrivo e via.
	 */
	
	public static void rimozioneInCatalogo(List<String> listaCatalogo) throws MapDAOException{
		Map map = readMap();
		rimozioneInMapRecursive(map,listaCatalogo,"Catalogo");
		// Al termine della rimozione salva la mappa (NB Metodo del Controllore)
		saveMap(map);
	}
	
	/* Metodo di stampa del catalogo. 
	 * Si basa sul wrapper ricorsivo di printMapRecursive.
	 */
	public static void printCatalogo() throws MapDAOException{
		Map map = readMap();
		printMapRecursive(map, 0, 5);
	}
	
}


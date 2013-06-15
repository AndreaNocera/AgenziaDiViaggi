package ordinaViaggi.control;

import java.util.ArrayList;
import java.util.List;

import ordinaViaggi.boundaries.BoundaryProgettista;
import ordinaViaggi.entity.Map;
import ordinaViaggi.entity.SubElement;
import ordinaViaggi.entity.SubElementOfferta;
import ordinaViaggi.exception.MapDAOException;

/**
 * @author Gambella Riccardo
 * Controllore Progettista.
 */
public class ControlloreProgettista extends Controllore
{
	//Temporanea per il test
	private static Controllore k;
	private static ControlloreProgettista h;   
	private static BoundaryProgettista c;

	public ControlloreProgettista(String dummy)
	{
		//System.out.println("Entra con CambiaUtente!");
	}
	
	//Costruttore
	private ControlloreProgettista() 
	{
		super(); 	
	}
	
	/* Avvia la Boundary del Progettista.
	 * 
	 */
	public static void gestioneProgettista()
	{  
		
		h=new ControlloreProgettista(); 	
		c= new BoundaryProgettista(h);   
		
	}
	
	/* Inserimento di un elemento nel catalogo. Sono necessari mappa iniziale,
	 * ambiente, mezzo, cittaPartenza e cittaArrivo inseriti in una lista.
	 * 
	 */
	public static void inserimentoInOfferta(List<String> listaCatalogo, List<String> subElementsInfo)
			throws MapDAOException{
		if(listaCatalogo.size() != subElementsInfo.size())
			throw new MapDAOException("Errore in Inserimento Offerta");
		Map map = readMap();
		
		// Crea la lista di SubElements di tipo SubElementCatalogo
		List<SubElement> listaSubElements = new ArrayList<SubElement>();
		for(String info : subElementsInfo){
			SubElement subElement = new SubElementOfferta(new Map(),info);
			listaSubElements.add(subElement);
		}
		
		inserimentoInMapRecursive(map,listaCatalogo,listaSubElements);
		// Al termine dell'inserimento salva la mappa (NB Metodo del Controllore)
		saveMap(map);
	}
	
	/* Metodo di rimozione di un elemento nell'offerta
	 * Sono necessari mappa iniziale, ambiente, mezzo, cittaPartenza, cittaArrivo, via,
	 * data, ora.
	 */
	
	public static void rimozioneInOfferta(List<String> listaCatalogo,
			String data, String ora) throws MapDAOException{
		Map map = readMap();
		listaCatalogo.add(data);
		listaCatalogo.add(ora);
		rimozioneInMapRecursive(map,listaCatalogo,"Offerta");
		// Al termine della rimozione salva la mappa (NB Metodo del Controllore)
		saveMap(map);
	}
	
	/* Metodo di stampa dell'Offerta. 
	 * Si basa sul wrapper ricorsivo di printMapRecursive.
	 */
	public static void printOfferta() throws MapDAOException{
		Map map = readMap();
		printMapRecursive(map, 0, 8);
	}
	
}









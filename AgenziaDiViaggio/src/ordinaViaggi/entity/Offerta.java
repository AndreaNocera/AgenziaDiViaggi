/**
 * @author Gambella Riccardo
 */


package ordinaViaggi.entity;

import ordinaViaggi.exception.MapDAOException;

import java.util.ArrayList;
import java.util.List;

public class Offerta extends ManipolatoreMappe{
	
	private static Offerta offerta;
	private Offerta(){
	}
	
	public static Offerta getIstance(){
		if(offerta == null)
			offerta = new Offerta();
		return offerta;
	}
	
	/** Inserimento di un elemento nell'offerta.
	 * 
	 * @param listaCatalogo
	 * @param subElementsInfo
	 * @throws MapDAOException
	 */
	public void inserimentoInOfferta(List<String> listaCatalogo, List<String> subElementsInfo)
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
	
	/** Rimozione di un elemento dall'offerta
	 * 
	 * @param listaCatalogo
	 * @param data
	 * @param ora
	 * @throws MapDAOException
	 */
	
	public void rimozioneInOfferta(List<String> listaCatalogo,
			String data, String ora) throws MapDAOException{
		Map map = readMap();
		listaCatalogo.add(data);
		listaCatalogo.add(ora);
		rimozioneInMapRecursive(map,listaCatalogo,"Offerta");
		// Al termine della rimozione salva la mappa (NB Metodo del Controllore)
		saveMap(map);
	}
	
	/** Stampa dell'offerta.
	 * 
	 * @throws MapDAOException
	 */
	public void printOfferta() throws MapDAOException{
		Map map = readMap();
		printMapRecursive(map, 0, 8);
	}
}

package ordinaViaggi.entity;

import ordinaViaggi.exception.MapDAOException;

import java.util.ArrayList;
import java.util.List;

public class Catalogo extends ManipolatoreMappe{
	
	private static Catalogo catalogo;
	private Catalogo(){
	}
	
	public static Catalogo getIstance(){
		if(catalogo == null)
			catalogo = new Catalogo();
		return catalogo;
	}
	
	
	/**Inserimento di un elemento nel catalogo
	 * 
	 * @param listaCatalogo
	 * @param subElementsInfo
	 * @throws MapDAOException
	 */
	public void inserimentoInCatalogo(List<String> listaCatalogo, List<String> subElementsInfo)
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
	
	/**Rimozione di un elemento dal catalogo
	 * 
	 * @param listaCatalogo
	 * @throws MapDAOException
	 */
	
	public void rimozioneInCatalogo(List<String> listaCatalogo) throws MapDAOException{
		Map map = readMap();
		rimozioneInMapRecursive(map,listaCatalogo,"Catalogo");
		// Al termine della rimozione salva la mappa (NB Metodo del Controllore)
		saveMap(map);
	}
	
	/**Stampa del catalogo
	 * 
	 * @throws MapDAOException
	 */
	public void printCatalogo() throws MapDAOException{
		Map map = readMap();
		printMapRecursive(map, 0, 5);
	}
	
}

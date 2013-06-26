/**
 * 
 */
package ordinaViaggi.entity;

import ordinaViaggi.exception.CatalogoException;
import ordinaViaggi.exception.DAOException;
import ordinaViaggi.exception.MapDAOException;

import java.util.List;

import ordinaViaggi.dao.DAOCatalogo;
import ordinaViaggi.dao.DAOTratta;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Gambella
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Catalogo {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private static Catalogo catalogo;
	private List<Tratta> tratte;
	private MapCatalogo map;
	private Catalogo(){
		/*
		 * Fetch del catalogo dal database.
		 */
		DAOCatalogo daoCatalogo = DAOCatalogo.getIstance();
		//map = (MapCatalogo)createMap();
		try {
			tratte = daoCatalogo.getCatalogo();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void inserimentoInCatalogo(Tratta tratta) throws CatalogoException{
		tratte.add(tratta);
		DAOTratta daoTratta = DAOTratta.getIstance();
		try {
			daoTratta.insert(tratta);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new CatalogoException();
		}
	}
	
	/**Rimozione di un elemento dal catalogo
	 * 
	 * @param listaCatalogo
	 * @throws MapDAOException
	 */
	
	public void rimozioneInCatalogo(Tratta tratta) throws CatalogoException{
		tratte.remove(tratta);
	}
	
	/**Stampa del catalogo
	 * 
	 * @throws MapDAOException
	 */
	public void printCatalogo() throws CatalogoException{
		for(Tratta tratta : tratte)
			tratta.printTratta();
	}
	
	/**
	 *  Metodo di creazione della mappa associata al catalogo.
	 */
	private Map createMap(){
		Map map = new MapCatalogo();
		return map;
	}
}
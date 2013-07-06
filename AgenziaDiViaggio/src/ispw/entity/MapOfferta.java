/**
 * 
 */
package ispw.entity;

import ispw.exception.DataException;
import ispw.exception.MapException;

import java.util.TreeMap;

/**
 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
 * 
 * @author Gambella Riccardo
 * @generated 
 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class MapOfferta extends TreeMap<Integer, Offerta> {

	/**
	 * <!-- begin-UML-doc --> <!-- end-UML-doc -->
	 * 
	 * @generated 
	 *            "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = -3072123529185663177L;

	/**
	 * Inserisce la chiave istanziando un Offerta.
	 * @throws DataException 
	 */
	public void insertRecord(Integer key) throws DataException {
		// TODO Auto-generated method stub
		if (!containsKey(key)) {
			super.put(key, new Offerta());
		}
	}

	/**
	 * Inserisce un offerta.
	 */
	public void insertRecord(Integer key, Offerta offerta) {
		// TODO Auto-generated method stub
		if (!containsKey(key)) {
			super.put(key, offerta);
		}
	}

	/**
	 * Rimuove un offerta.
	 */

	public void removeRecord(Integer key) throws MapException {
		// TODO Auto-generated method stub
		if (!containsKey(key)) {
			throw new MapException("Errore in rimozione. Chiave non presente.");
		}
		super.remove(key);

	}

}
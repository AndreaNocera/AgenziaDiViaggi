/**
 * 
 */
package ispw.entity;

import ispw.exception.DataException;
import ispw.exception.MapException;

import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
	private static final long serialVersionUID = 3957741829514129408L;
	private final Lock lock;
	
	public MapOfferta(){
		lock = new ReentrantLock();
	}
	
	/**
	 * Inserisce la chiave istanziando un Offerta.
	 * @throws DataException 
	 */
	public void insertRecord(Integer key) throws DataException {
		// TODO Auto-generated method stub
		if (!containsKey(key)) {
			lock.lock();
			super.put(key, new Offerta());
			lock.unlock();
		}
	}

	/**
	 * Inserisce un offerta.
	 */
	public void insertRecord(Integer key, Offerta offerta) {
		// TODO Auto-generated method stub
		if (!containsKey(key)) {
			lock.lock();
			super.put(key, offerta);
			lock.unlock();
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
		lock.lock();
		super.remove(key);
		lock.unlock();
	}

}
/**
 * 
 */
package ispw.entity;

import ispw.exception.MapException;

import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Gambella Riccardo
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class MapCatalogo<T extends ElementoCatalogo> extends TreeMap<String, T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6279381576443659194L;
	private final Lock lock;
	
	public MapCatalogo(){
		lock = new ReentrantLock();
	}
	
	/**
	 * Inserisce la chiave istanziando un ElementoCatalogo.
	 */
	@SuppressWarnings("unchecked")
	public void insertRecord(String key) {
		// TODO Auto-generated method stub
		if(!containsKey(key)){
			lock.lock();
			super.put(key, (T) new ElementoIntermedio());
			lock.unlock();
		}
	}
	
	public void removeRecord(String key) throws MapException {
		// TODO Auto-generated method stub
		if(!containsKey(key))		{	
			throw new MapException("Errore in rimozione. Chiave non presente.");
		}
		
		lock.lock();
		super.remove(key);
		lock.unlock();
	}
	
	@SuppressWarnings("unchecked")
	public void insertElementoIntermedio(String key, ElementoIntermedio elemento){
		// TODO Auto-generated method stub
			if(!containsKey(key)){
				lock.lock();
				super.put(key,(T) elemento);
				lock.unlock();
			}
	}
	@SuppressWarnings("unchecked")
	public void insertElementoFinale(String key, ElementoFinale elemento){
		// TODO Auto-generated method stub
			if(!containsKey(key)){
				lock.lock();
				super.put(key,(T) elemento);
				lock.unlock();
			}
	}
	
	public ElementoFinale getElementoFinale(String key){
		ElementoFinale elemento = null;
		
		lock.lock();
		elemento = (ElementoFinale)super.get(key);
		lock.unlock();
		
		return elemento;
	}
	public ElementoIntermedio getElementoIntermedio(String key){
		ElementoIntermedio elemento = null;
		
		lock.lock();
		elemento = (ElementoIntermedio)super.get(key);
		lock.unlock();
		
		return elemento;
	}
	public boolean verificaEsistenza (String key){
		if(containsKey(key))
			return true;
		return false;
	}
}
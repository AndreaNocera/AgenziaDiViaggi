/**
 * 
 */
package ispw.entity;

import ispw.exception.MapException;

import java.util.TreeMap;

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
	private static final long serialVersionUID = -6165364327193703741L;
	public MapCatalogo(){
		
	}
	
	/**
	 * Inserisce la chiave istanziando un ElementoCatalogo.
	 */
	public void insertRecord(String key) {
		// TODO Auto-generated method stub
		
		//Lock
		if(!containsKey(key))
		{	
			super.put(key, (T) new ElementoIntermedio());
		}
	}
	
	public void removeRecord(String key) throws MapException {
		// TODO Auto-generated method stub
		if(!containsKey(key))
		{	
			throw new MapException("Errore in rimozione. Chiave non presente.");
		}
		super.remove(key);
		
	}
	
	public void insertElementoIntermedio(String key, ElementoIntermedio elemento){
		// TODO Auto-generated method stub
			if(!containsKey(key))
			{	
				super.put(key,(T) elemento);
			}
	}
	public void insertElementoFinale(String key, ElementoFinale elemento){
		// TODO Auto-generated method stub
			if(!containsKey(key))
			{	
				super.put(key,(T) elemento);
			}
	}
	
	public ElementoFinale getElementoFinale(String key){
		return (ElementoFinale)super.get(key);
	}
	public ElementoIntermedio getElementoIntermedio(String key){
		return (ElementoIntermedio)super.get(key);
	}
	public boolean verificaEsistenza (String key){
		if(containsKey(key))
			return true;
		return false;
	}
}
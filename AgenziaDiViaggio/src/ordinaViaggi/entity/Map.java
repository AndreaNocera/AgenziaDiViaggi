/**
 * 
 */
package ordinaViaggi.entity;

import ordinaViaggi.exception.MapException;

import java.util.Collection;
import java.util.TreeMap;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Gambella Riccardo
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract class Map extends TreeMap<Integer, Elemento>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3765994717735043055L;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	
	

	public Map(){
		super();
	}
	
	public Elemento getElemento(Integer key) throws MapException{
		if(!containsKey(key))
			throw new MapException("Errore nel getElemento. Chiave non presente");
		return super.get(key);
	}

	public void setSubElement(Integer key, Elemento elemento) throws MapException {
		if(!containsKey(key))
			throw new MapException("Errore nel setElemento. Chiave non presente");
		super.put(key, elemento);
	}
	/**
	 * Inserisce il record con chiave key.
	 * Va ridefinito nelle sottoclassi.
	 * @param key
	 */
	public abstract void insertRecord(Integer key);
	 /**
	  * Se si chiama insertRecord e la chiave è già inserita non inserisce il nuovo SubElement.
	  * Si deve chiamre setSubElement sulla chiave, invece.
	  * @param key
	  * @param subElement
	  */
	public void insertRecord(Integer key, Elemento elemento){
		if(!containsKey(key))
		{	
			super.put(key, elemento);  //put(Terra, subElement)
		}
	}
	

	public void removeRecord(Integer key) throws MapException{
		if(!containsKey(key))
			throw new MapException("Errore nella rimozione. Chiave non esistente.");
		super.remove(key);
	}
	
	public void printMap(){
		if(this == null)
			return;
		Collection<Integer> collection = this.keySet();
        for(Integer I : collection){
        	System.out.println(I);
        }
	}
}
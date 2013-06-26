/**
 * 
 */
package ordinaViaggi.entity;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Gambella Riccardo
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class MapCatalogo extends Map {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7922896680836125231L;

	/**
	 * Inserisce la chiave istanziando un SubElementCatalogo.
	 */
	@Override
	public void insertRecord(Integer key) {
		// TODO Auto-generated method stub
		if(!containsKey(key))
		{	
			super.put(key, new ElementoCatalogo());
		}
	}
}
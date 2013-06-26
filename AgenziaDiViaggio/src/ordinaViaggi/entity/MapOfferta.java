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
public class MapOfferta extends Map {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7313454247945221246L;

	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	/**
	 * Inserisce la chiave istanziando un SubElementOfferta.
	 */
	@Override
	public void insertRecord(Integer key) {
		// TODO Auto-generated method stub
		if(!containsKey(key))
		{	
			super.put(key, new ElementoOfferta()); 
		}
	}
}
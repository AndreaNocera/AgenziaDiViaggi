/**
 * 
 */
package ordinaViaggi.entity;

import ordinaViaggi.dao.DAOCitta;
import ordinaViaggi.exception.DAOException;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Gambella Riccardo
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Citta extends ElementoIntermedio{
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Citta(){
		super();
	}
	public Citta(Integer id, String valore){
		super(id,valore);
	}	
	public void save() throws DAOException{
		DAOCitta daoCitta = DAOCitta.getIstance();
		daoCitta.insert(this);
	}
	public static Citta getObjectByValue(String citta) throws DAOException{
		DAOCitta daoCitta = DAOCitta.getIstance();
		return daoCitta.getObjectByValue(citta);
	}
}
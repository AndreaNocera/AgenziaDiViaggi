/**
 * 
 */
package ispw.entity;

import ispw.dao.DAOVia;
import ispw.exception.DAOException;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Gambella Riccardo
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Via extends ElementoFinale{
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Via(){
		super();
	}
	public Via(Integer id, String valore){
		super(id,valore);
	}	
	public void save() throws DAOException{
		DAOVia daoVia = DAOVia.getInstance();
		daoVia.insert(this);
	}
	public static Via getObjectByValue(String via) throws DAOException{
		DAOVia daoVia = DAOVia.getInstance();
		return daoVia.getObjectByValue(via);
	}
}
/**
 * 
 */
package ordinaViaggi.entity;

import ordinaViaggi.dao.DAOVia;
import ordinaViaggi.exception.DAOException;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Gambella
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
		DAOVia daoVia = DAOVia.getIstance();
		daoVia.insert(this);
	}
}
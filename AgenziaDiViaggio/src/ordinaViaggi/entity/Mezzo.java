/**
 * 
 */
package ordinaViaggi.entity;

import ordinaViaggi.dao.DAOMezzo;
import ordinaViaggi.exception.DAOException;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Gambella
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Mezzo extends ElementoIntermedio{
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Mezzo(){
		super();
	}
	public Mezzo(Integer id, String valore){
		super(id,valore);
	}	
	public void save() throws DAOException{
		DAOMezzo daoMezzo = DAOMezzo.getIstance();
		daoMezzo.insert(this);
	}
	
}
/**
 * 
 */
package ispw.entity;

import ispw.dao.DAOAmbiente;
import ispw.exception.DAOException;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author Gambella Riccardo
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class Ambiente extends ElementoIntermedio{
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public Ambiente(){
		super();
	}
	public Ambiente(Integer id, String valore){
		super(id,valore);
	}	
	public void save() throws DAOException{
		DAOAmbiente daoAmbiente = DAOAmbiente.getInstance();
		daoAmbiente.insert(this);
	}
	public void print() {
		// TODO Auto-generated method stub
		System.out.println(this.getId() + " " + this.getValore());
	}
	public static Ambiente getObjectByValue(String ambiente) throws DAOException{
		DAOAmbiente daoAmbiente = DAOAmbiente.getInstance();
		return daoAmbiente.getObjectByValue(ambiente);
	}
}
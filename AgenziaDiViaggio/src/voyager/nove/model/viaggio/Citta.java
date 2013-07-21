package voyager.nove.model.viaggio;

import voyager.nove.exception.DAOException;
import voyager.nove.persistence.dao.DAOCitta;

public class Citta extends ElementoIntermedio{

	public Citta(){
		super();
	}
	public Citta(Integer id, String valore){
		super(id,valore);
	}	
	public void save() throws DAOException{
		DAOCitta daoCitta = DAOCitta.getInstance();
		daoCitta.insert(this);
	}
	public static Citta getObjectByValue(String citta) throws DAOException{
		DAOCitta daoCitta = DAOCitta.getInstance();
		return daoCitta.getObjectByValue(citta);
	}
}
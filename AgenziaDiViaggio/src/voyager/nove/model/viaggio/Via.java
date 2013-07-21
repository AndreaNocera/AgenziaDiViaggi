package voyager.nove.model.viaggio;

import voyager.nove.exception.DAOException;
import voyager.nove.persistence.dao.DAOVia;

public class Via extends ElementoFinale{
	
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
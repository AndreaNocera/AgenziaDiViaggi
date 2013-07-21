package voyager.nove.model.viaggio;

import voyager.nove.exception.DAOException;
import voyager.nove.persistence.dao.DAOMezzo;

public class Mezzo extends ElementoIntermedio{

	public Mezzo(){
		super();
	}
	public Mezzo(Integer id, String valore){
		super(id,valore);
	}	
	public void save() throws DAOException{
		DAOMezzo daoMezzo = DAOMezzo.getInstance();
		daoMezzo.insert(this);
	}
	public static Mezzo getObjectByValue(String mezzo) throws DAOException{
		DAOMezzo daoMezzo = DAOMezzo.getInstance();
		return daoMezzo.getObjectByValue(mezzo);
	}
	
}
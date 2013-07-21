package voyager.nove.model.viaggio;

import voyager.nove.exception.DAOException;
import voyager.nove.persistence.dao.DAOAmbiente;

public class Ambiente extends ElementoIntermedio{

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
		System.out.println(this.getId() + " " + this.getValore());
	}
	
	public static Ambiente getObjectByValue(String ambiente) throws DAOException{
		DAOAmbiente daoAmbiente = DAOAmbiente.getInstance();
		return daoAmbiente.getObjectByValue(ambiente);
	}
	
}
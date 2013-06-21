package ordinaViaggi.entity;

/**
 * @author Gambella Riccardo
 * Entità Map.
 */


import ordinaViaggi.exception.MapDAOException;

import java.io.Serializable;
import java.util.*;


public class Map extends TreeMap <String,SubElement> implements Serializable{

	private static final long serialVersionUID = 1L;
	public Map(){
		super();
	}
	
	/*
	 *  Save() e get() utilizzano il DAO relativo alla gestione della persistenza sul file.
	 */
	public void save() throws MapDAOException{
		MapDAO mapDAO = MapDAOPersistentFile.getIstance();
		mapDAO.save(this);
	}
	
	public Map get() throws MapDAOException{
		MapDAO mapDAO = MapDAOPersistentFile.getIstance();
		return mapDAO.get();
	}
	
	public void insertRecord(String key, SubElement subElement){
		if(!containsKey(key))
		{	
			super.put(key, subElement);  //put(Terra, subElement)
		}
	}
	public void printMap(){
		if(this == null)
			return;
		Collection<String> collection = this.keySet();
        Iterator<String> iterator = collection.iterator();
        while(iterator.hasNext()){
        	String key = iterator.next();
        	System.out.println(key);
        }
	}
}

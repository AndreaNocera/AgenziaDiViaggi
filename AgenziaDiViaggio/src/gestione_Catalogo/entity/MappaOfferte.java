package gestione_Catalogo.entity;

import java.util.TreeMap;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class MappaOfferte extends TreeMap<Integer,Offerta>{

	private static final long serialVersionUID = 1L;

	public MappaOfferte(){
		super();
	}

	public void aggiungiOfferta(Integer k, Offerta o){
		if(!containsKey(k))
			super.put(k, o);
	}
}

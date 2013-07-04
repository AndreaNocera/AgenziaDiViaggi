package gestione_Catalogo.entity;

import java.util.TreeMap;

/**
 * @authors 
 * Remo Sperlongano
 * Ivan Torre
 */
public class MappaOfferte extends TreeMap<Data,Offerta>{

	private static final long serialVersionUID = 1L;

	public MappaOfferte(MappaOfferteComparator moc){
		super(moc);
	}

	public void aggiungiOfferta(Data k, Offerta o){
		if(!containsKey(k))
			super.put(k, o);
	}
}
